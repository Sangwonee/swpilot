package course04.question02.book;

import course04.question02.user.Student;
import course04.question02.user.User;

/** 학생만 대출할 수 있는 교과서이다. */
public class Textbook extends Book {

    public Textbook(String title, String author, String isbn) {
        super(title, author, isbn);
    }

    @Override
    public String getBookType() {
        return "교과서";
    }

    @Override
    public boolean canBeBorrowedBy(User user) {
        return user instanceof Student;
    }
}
