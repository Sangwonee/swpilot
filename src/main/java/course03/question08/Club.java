package course03.question08;

import course03.question08.member.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Club {
    private final List<Member> members = new ArrayList<>();
    private final List<Session> sessions = new ArrayList<>();

    // 같은 이름의 회원이 있는지 확인한 후 회원을 추가한다.
    public void addMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("추가할 회원 정보가 없습니다.");
        }

        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(member.getName())) {
                throw new IllegalArgumentException(
                        member.getName() + "은 이미 가입한 회원입니다.");
            }
        }

        members.add(member);
        System.out.println(member.getName() + "이 " + member.getRoleName() + "으로 가입되었습니다.");
    }

    public Member removeMember(String name) {
        Member member = findMemberByName(name);
        members.remove(member);
        System.out.println(member.getName() + "이 동호회에서 삭제되었습니다.");
        return member;
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members);
    }

    // 같은 날짜와 장소의 세션이 있는지 확인한 후 세션을 추가한다.
    public void addSession(Session session) {
        if (session == null) {
            throw new IllegalArgumentException("추가할 연습 세션이 없습니다.");
        }

        for (int i = 0; i < sessions.size(); i++) {
            Session savedSession = sessions.get(i);
            if (savedSession.getSessionDate().equals(session.getSessionDate())
                    && savedSession.getSessionLocation().equals(session.getSessionLocation())) {
                throw new IllegalArgumentException("같은 날짜와 장소의 연습 세션이 이미 존재합니다.");
            }
        }

        sessions.add(session);
    }

    // 세션 삭제
    public Session removeSession(LocalDateTime sessionDate, String sessionLocation) {
        Session session = findSessionByDateAndLocation(sessionDate, sessionLocation);
        sessions.remove(session);
        System.out.println(Session.formatDate(sessionDate) + ", " + sessionLocation
                + " 연습 세션이 삭제되었습니다.");
        return session;
    }

    public List<Session> getAllSessions() {
        return new ArrayList<>(sessions);
    }

    // 같은 날짜에 열리는 모든 세션을 검색한다.
    public List<Session> searchSessionsByDate(LocalDateTime sessionDate) {
        if (sessionDate == null) {
            throw new IllegalArgumentException("검색할 날짜를 입력해주세요.");
        }

        List<Session> foundSessions = new ArrayList<>();
        for (int i = 0; i < sessions.size(); i++) {
            Session session = sessions.get(i);
            if (session.getSessionDate().toLocalDate().equals(sessionDate.toLocalDate())) {
                foundSessions.add(session);
            }
        }

        ensureSearchResult(foundSessions);
        printSessions(foundSessions);
        return foundSessions;
    }

    // 진행 상태가 일치하는 모든 세션을 검색한다.
    public List<Session> searchSessionsByStatus(SessionStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("검색할 진행 상태를 입력해주세요.");
        }

        List<Session> foundSessions = new ArrayList<>();
        for (int i = 0; i < sessions.size(); i++) {
            Session session = sessions.get(i);
            if (session.getStatus() == status) {
                foundSessions.add(session);
            }
        }

        ensureSearchResult(foundSessions);
        printSessions(foundSessions);
        return foundSessions;
    }

    // 날짜와 장소가 모두 일치하는 세션 한 개를 검색한다.
    public Session findSessionByDateAndLocation(
            LocalDateTime sessionDate,
            String sessionLocation) {
        if (sessionDate == null
                || sessionLocation == null
                || sessionLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("검색할 날짜와 장소를 입력해주세요.");
        }

        for (int i = 0; i < sessions.size(); i++) {
            Session session = sessions.get(i);
            if (session.getSessionDate().equals(sessionDate)
                    && session.getSessionLocation().equals(sessionLocation)) {
                return session;
            }
        }

        throw new NoSuchElementException("해당하는 연습 세션이 없습니다.");
    }

    public Member findMemberByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("검색할 회원 이름을 입력해주세요.");
        }

        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if (member.getName().equals(name)) {
                return member;
            }
        }

        throw new NoSuchElementException(name + " 회원이 존재하지 않습니다.");
    }

    private static void ensureSearchResult(List<Session> foundSessions) {
        if (foundSessions.isEmpty()) {
            throw new NoSuchElementException("검색 조건에 해당하는 연습 세션이 없습니다.");
        }
    }

    private static void printSessions(List<Session> foundSessions) {
        for (int i = 0; i < foundSessions.size(); i++) {
            System.out.println(foundSessions.get(i).getSessionInfo());
        }
    }
}
