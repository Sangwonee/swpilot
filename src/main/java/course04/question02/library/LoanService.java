package course04.question02.library;

import course04.question02.book.Book;
import course04.question02.user.User;

/** 도서 대출과 반납 기능만 노출한다. */
public interface LoanService {

    boolean borrowBook(User user, Book book);

    boolean returnBook(User user, Book book);
}
