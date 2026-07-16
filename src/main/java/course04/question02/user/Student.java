package course04.question02.user;

/** 일반 도서와 교과서를 모두 대출할 수 있는 학생 회원이다. */
public class Student extends Member {

    public Student(String userId, String name) {
        super(userId, name);
    }

    @Override
    public String getUserType() {
        return "학생";
    }
}
