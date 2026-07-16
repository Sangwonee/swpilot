package course04.question02.library;

import course04.question02.book.Book;
import course04.question02.user.Manager;

/** 관리자가 사용하는 도서 등록과 삭제 기능을 추상화한다. */
public interface BookCatalog {

    boolean addBook(Manager manager, Book book);

    boolean removeBook(Manager manager, Book book);
}
