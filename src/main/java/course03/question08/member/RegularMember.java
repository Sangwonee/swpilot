package course03.question08.member;

import course03.question08.Session;
import course03.question08.practice.PracticeScheduler;

import java.time.LocalDateTime;

public class RegularMember extends Member implements PracticeScheduler {

    public RegularMember(String name, LocalDateTime joinDate, int skillLevel) {
        super(name, joinDate, skillLevel);
    }

    public RegularMember(String name, LocalDateTime joinDate) {
        super(name, joinDate);
    }

    @Override
    public String getRoleName() {
        return "일반 멤버";
    }

    @Override
    public Session createSession(LocalDateTime sessionDate, String sessionLocation) {
        return openSession(sessionDate, sessionLocation);
    }
}
