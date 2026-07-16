package course04.question02.user;

/** 일반 도서관 이용자이다. */
public class Member extends User {

    public Member(String userId, String name) {
        super(userId, name);
    }

    @Override
    public String getUserType() {
        return "일반 회원";
    }
}
