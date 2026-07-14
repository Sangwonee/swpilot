package course03.question08;

import course03.question08.member.Member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy년 M월 d일");

    private LocalDateTime sessionDate;
    private final String sessionLocation;
    private final List<Member> members;
    private final Member organizer;
    private SessionStatus status;

    public Session(LocalDateTime sessionDate, String sessionLocation, Member organizer) {
        if (sessionDate == null
                || sessionLocation == null
                || sessionLocation.trim().isEmpty()
                || organizer == null) {
            throw new IllegalArgumentException("연습 세션 정보를 다시 확인해주세요.");
        }

        this.sessionDate = sessionDate;
        this.sessionLocation = sessionLocation;
        this.members = new ArrayList<>();
        this.organizer = organizer;
        this.status = SessionStatus.OPEN;
    }

    // 개설 상태인지와 중복 참가 여부를 확인한 후 회원을 추가한다.
    public void addMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("참가할 회원 정보가 없습니다.");
        }
        ensureOpenSession();

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(member.getName())) {
                throw new IllegalArgumentException(
                        member.getName() + "은 이미 연습 세션에 참가했습니다.");
            }
        }

        members.add(member);
        System.out.println(member.getName() + "이 연습 세션에 참가합니다.");
    }

    // 개설된 세션의 상태를 취소로 변경한다.
    public void cancelSession() {
        ensureOpenSession();
        status = SessionStatus.CANCELED;
        System.out.println(formatDate(sessionDate) + ", " + sessionLocation
                + " 연습 세션이 취소되었습니다.");
    }

    // 개설된 세션의 날짜만 새로운 날짜로 변경한다.
    public void delaySession(LocalDateTime newSessionDate) {
        ensureOpenSession();
        if (newSessionDate == null || newSessionDate.equals(sessionDate)) {
            throw new IllegalArgumentException("변경할 연습 날짜를 다시 확인해주세요.");
        }

        sessionDate = newSessionDate;
        System.out.println(organizer.getName() + "이 " + formatDate(sessionDate)
                + ", " + sessionLocation + "에 연습 세션을 연기했습니다.");
    }

    public String getSessionInfo() {
        return String.format("%s, %s, %s, %s, %s",
                formatDate(sessionDate),
                sessionLocation,
                getMemberNames(),
                organizer.getName(),
                status.getDisplayName());
    }

    public LocalDateTime getSessionDate() {
        return sessionDate;
    }

    public String getSessionLocation() {
        return sessionLocation;
    }

    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    public Member getOrganizer() {
        return organizer;
    }

    public SessionStatus getStatus() {
        return status;
    }

    private List<String> getMemberNames() {
        List<String> memberNames = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            memberNames.add(members.get(i).getName());
        }

        return memberNames;
    }

    private void ensureOpenSession() {
        if (status == SessionStatus.CANCELED) {
            throw new IllegalStateException("취소된 연습 세션입니다.");
        }
    }

    public static String formatDate(LocalDateTime date) {
        return date.format(DATE_FORMATTER);
    }
}
