package course03.question08;

import course03.question08.member.AdminMember;
import course03.question08.member.NewMember;
import course03.question08.member.RegularMember;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class BiodomeFamily08 {
    public static void main(String[] args) {
        try {
            Club club = new Club();
            LocalDateTime joinDate = LocalDateTime.of(2026, 9, 1, 0, 0);

            AdminMember john = new AdminMember("John", joinDate, 5);
            RegularMember jane = new RegularMember("Jane", joinDate, 3);
            RegularMember doe = new RegularMember("Doe", joinDate);
            NewMember amy = new NewMember("Amy", joinDate, 1);
            NewMember leo = new NewMember("Leo", joinDate);

            club.addMember(john);
            club.addMember(jane);
            club.addMember(doe);
            club.addMember(amy);
            club.addMember(leo);

            System.out.println();

            LocalDateTime originalDate = LocalDateTime.of(2130, 9, 12, 0, 0);
            String location = "도메 스타디움";
            Session session = john.createSession(originalDate, location);
            club.addSession(session);

            System.out.println();

            jane.joinSession(session);
            amy.joinSession(session);

            System.out.println();

            club.searchSessionsByStatus(SessionStatus.OPEN);

            System.out.println();

            Session sessionToDelay = club.findSessionByDateAndLocation(originalDate, location);
            LocalDateTime delayedDate = originalDate.plusWeeks(1);
            john.delaySession(sessionToDelay, delayedDate);

            System.out.println();

            Session sessionToCancel = club.findSessionByDateAndLocation(delayedDate, location);
            john.cancelSession(sessionToCancel);
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
