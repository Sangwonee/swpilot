package course04.question08;

import java.time.LocalDate;

public class Book implements Comparable<Book> {
    private final String title;
    private final String author;
    private final LocalDate publicationDate;
    private boolean available;
    private LocalDate recentBorrowedDate;

    public Book(String title, String author, LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public LocalDate getRecentBorrowedDate() {
        return recentBorrowedDate;
    }

    void markAsBorrowed(LocalDate borrowedDate) {
        available = false;
        recentBorrowedDate = borrowedDate;
    }

    void markAsReturned() {
        available = true;
    }

    public void printInformation() {
        System.out.println(this);
    }

    // Comparable은 도서의 기본 정렬 기준을 제목 오름차순으로 정의한다.
    @Override
    public int compareTo(Book other) {
        return title.compareTo(other.title);
    }

    @Override
    public String toString() {
        String availability = available ? "가능" : "불가능";
        String borrowedDate = recentBorrowedDate == null
                ? "N/A"
                : recentBorrowedDate.toString();

        return String.format(
                "제목: \"%s\", 저자: \"%s\", 출판일: \"%s\", "
                        + "대출 가능 여부: \"%s\", 최근 대출 날짜: \"%s\"",
                title, author, publicationDate, availability, borrowedDate);
    }
}
