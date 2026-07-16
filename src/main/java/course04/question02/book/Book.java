package course04.question02.book;

import course04.question02.user.User;

/** 제목, 저자, ISBN과 대출 가능 상태를 캡슐화한 일반 도서이다. */
public class Book {

    private final String title;
    private final String author;
    private final String isbn;
    private boolean available = true;

    public Book(String title, String author, String isbn) {
        this.title = requireText(title, "제목");
        this.author = requireText(author, "저자");
        this.isbn = requireText(isbn, "ISBN");
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getBookType() {
        return "일반 도서";
    }

    /** 도서 종류가 자신의 대출 정책을 결정하므로 대출 서비스는 종류를 구분하지 않는다. */
    public boolean canBeBorrowedBy(User user) {
        return true;
    }

    public void markAsBorrowed() {
        if (!available) {
            throw new IllegalStateException("이미 대출 중인 책입니다.");
        }
        available = false;
    }

    public void markAsReturned() {
        if (available) {
            throw new IllegalStateException("이미 반납된 책입니다.");
        }
        available = true;
    }

    public void printInformation() {
        System.out.printf(
                "제목: %s, 저자: %s, ISBN: %s, 유형: %s, 상태: %s%n",
                title,
                author,
                isbn,
                getBookType(),
                available ? "대출 가능" : "대출 불가");
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + "은(는) 비어 있을 수 없습니다.");
        }
        return value.trim();
    }
}
