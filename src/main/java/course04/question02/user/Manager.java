package course04.question02.user;

import course04.question02.book.Book;
import course04.question02.library.BookCatalog;

/** 도서 관리 권한을 가진 사용자이다. */
public class Manager extends User {

    public Manager(String userId, String name) {
        super(userId, name);
    }

    public boolean addBook(Book book, BookCatalog catalog) {
        return catalog.addBook(this, book);
    }

    public boolean removeBook(Book book, BookCatalog catalog) {
        return catalog.removeBook(this, book);
    }

    @Override
    public String getUserType() {
        return "관리자";
    }
}
