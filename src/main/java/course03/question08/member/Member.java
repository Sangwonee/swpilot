package course03.question08.member;

import course03.question08.Session;

import java.time.LocalDateTime;

public class Member {
    private final String name;
    private final LocalDateTime joinDate;
    private final int skillLevel;

    public Member(String name, LocalDateTime joinDate) {
        this(name, joinDate, 0);
    }

    public Member(String name, LocalDateTime joinDate, int skillLevel) {
        if (name == null || name.trim().isEmpty() || joinDate == null || skillLevel < 0) {
            throw new IllegalArgumentException("회원 정보를 다시 확인해주세요.");
        }

        this.name = name;
        this.joinDate = joinDate;
        this.skillLevel = skillLevel;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public String getRoleName() {
        return "멤버";
    }

    public void joinSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("참가할 연습 세션이 없습니다.");
        }

        session.addMember(this);
    }

    // 운영진과 일반 멤버가 공통으로 사용하는 세션 개설 로직이다.
    protected Session openSession(LocalDateTime sessionDate, String sessionLocation) {
        Session session = new Session(sessionDate, sessionLocation, this);
        System.out.println(name + "이 " + Session.formatDate(sessionDate) + ", "
                + sessionLocation + "에 연습 세션을 오픈했습니다.");
        return session;
    }
}
