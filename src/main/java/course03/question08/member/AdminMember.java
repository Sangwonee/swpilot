package course03.question08.member;

import course03.question08.Session;
import course03.question08.practice.PracticeController;
import course03.question08.practice.PracticeScheduler;

import java.time.LocalDateTime;

public class AdminMember extends Member implements PracticeScheduler, PracticeController {
    public AdminMember(String name, LocalDateTime joinDate) {
        super(name, joinDate);
    }

    public AdminMember(String name, LocalDateTime joinDate, int skillLevel) {
        super(name, joinDate, skillLevel);
    }

    @Override
    public String getRoleName() {
        return "운영진";
    }

    @Override
    public Session createSession(LocalDateTime sessionDate, String sessionLocation) {
        return openSession(sessionDate, sessionLocation);
    }

    @Override
    public void cancelSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("취소할 연습 세션이 없습니다.");
        }

        session.cancelSession();
    }

    @Override
    public void delaySession(Session session, LocalDateTime newSessionDate) {
        if (session == null) {
            throw new IllegalArgumentException("연기할 연습 세션이 없습니다.");
        }

        session.delaySession(newSessionDate);
    }
}
