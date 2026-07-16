package course04.question02.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import course04.question02.book.Book;
import course04.question02.library.LoanService;

/** 공통 사용자 정보와 대출 목록만 관리한다. */
public abstract class User {

    private final String userId;
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();

    protected User(String userId, String name) {
        this.userId = requireText(userId, "사용자 아이디");
        this.name = requireText(name, "이름");
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    public boolean borrowBook(Book book, LoanService loanService) {
        return loanService.borrowBook(this, book);
    }

    public boolean returnBook(Book book, LoanService loanService) {
        return loanService.returnBook(this, book);
    }

    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }

    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }

    public boolean hasBorrowed(Book book) {
        return borrowedBooks.contains(book);
    }

    public String getUserType() {
        return "사용자";
    }

    public void printInformation() {
        StringBuilder titles = new StringBuilder();

        for (Book book : borrowedBooks) {
            if (titles.length() > 0) {
                titles.append(", ");
            }
            titles.append(book.getTitle());
        }

        if (titles.length() == 0) {
            titles.append("없음");
        }

        System.out.printf(
                "아이디: %s, 이름: %s, 구분: %s, 대출 도서: %s%n",
                userId, name, getUserType(), titles);
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 비어 있을 수 없습니다.");
        }
        return value.trim();
    }
}
