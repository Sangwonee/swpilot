package course03.question08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import course03.question08.member.AdminMember;
import course03.question08.member.Member;
import course03.question08.member.NewMember;
import course03.question08.member.RegularMember;
import course03.question08.practice.PracticeController;
import course03.question08.practice.PracticeScheduler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class BiodomeFamily08Test {
    private static final LocalDateTime JOIN_DATE =
            LocalDateTime.of(2130, 9, 1, 0, 0);
    private static final LocalDateTime SESSION_DATE =
            LocalDateTime.of(2130, 9, 12, 0, 0);

    @Test
    void 두_생성자로_회원을_만든다() {
        Member memberWithoutSkill = new NewMember("Amy", JOIN_DATE);
        Member memberWithSkill = new NewMember("Leo", JOIN_DATE, 2);

        assertEquals(0, memberWithoutSkill.getSkillLevel());
        assertEquals(2, memberWithSkill.getSkillLevel());
        assertEquals(JOIN_DATE, memberWithSkill.getJoinDate());
    }

    @Test
    void 회원별로_허용된_인터페이스가_다르다() {
        AdminMember admin = new AdminMember("John", JOIN_DATE);
        RegularMember regular = new RegularMember("Jane", JOIN_DATE);
        NewMember newMember = new NewMember("Amy", JOIN_DATE);

        assertTrue(admin instanceof PracticeScheduler);
        assertTrue(admin instanceof PracticeController);
        assertTrue(regular instanceof PracticeScheduler);
        assertTrue(!(regular instanceof PracticeController));
        assertTrue(!(newMember instanceof PracticeScheduler));
    }

    @Test
    void 회원이_세션에_참가하고_중복_참가는_할_수_없다() {
        AdminMember admin = new AdminMember("John", JOIN_DATE);
        RegularMember member = new RegularMember("Jane", JOIN_DATE);
        Session session = admin.createSession(SESSION_DATE, "도메 스타디움");

        member.joinSession(session);

        assertEquals(1, session.getMembers().size());
        assertThrows(IllegalArgumentException.class, () -> member.joinSession(session));
    }

    @Test
    void 운영진이_세션을_연기하고_취소한다() {
        AdminMember admin = new AdminMember("John", JOIN_DATE);
        Session session = admin.createSession(SESSION_DATE, "도메 스타디움");
        LocalDateTime delayedDate = SESSION_DATE.plusWeeks(1);

        admin.delaySession(session, delayedDate);
        admin.cancelSession(session);

        assertEquals(delayedDate, session.getSessionDate());
        assertEquals(SessionStatus.CANCELED, session.getStatus());
        assertThrows(IllegalStateException.class,
                () -> session.addMember(new NewMember("Amy", JOIN_DATE)));
    }

    @Test
    void 클럽에서_회원과_세션을_관리하고_검색한다() {
        Club club = new Club();
        AdminMember admin = new AdminMember("John", JOIN_DATE);
        Session session = admin.createSession(SESSION_DATE, "도메 스타디움");

        club.addMember(admin);
        club.addSession(session);

        assertEquals(admin, club.findMemberByName("John"));
        assertEquals(session,
                club.findSessionByDateAndLocation(SESSION_DATE, "도메 스타디움"));
        assertEquals(1, club.searchSessionsByDate(SESSION_DATE).size());
        assertEquals(1, club.searchSessionsByStatus(SessionStatus.OPEN).size());
        assertThrows(IllegalArgumentException.class, () -> club.addMember(admin));
        assertThrows(NoSuchElementException.class,
                () -> club.findMemberByName("Unknown"));
    }
}
