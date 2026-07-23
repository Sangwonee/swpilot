package course04.question08;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Library {
    private final ArrayList<Book> books = new ArrayList<>();

    public Library() {
        System.out.println("도서관 시스템이 생성되었습니다.");
    }

    public boolean addBook(Book book) {
        if (!isValidBook(book)) {
            System.out.println("등록할 도서 정보를 다시 확인해주세요.");
            return false;
        }
        if (findBookByTitle(book.getTitle()) != null) {
            System.out.printf("\"%s\"은(는) 이미 등록된 책입니다.%n", book.getTitle());
            return false;
        }

        books.add(book);
        System.out.printf("\"%s\"이(가) 도서 목록에 추가되었습니다.%n", book.getTitle());
        return true;
    }

    public boolean borrowBook(String title) {
        Book book = findBookByTitle(title);
        if (book == null) {
            printBookNotFound(title);
            return false;
        }
        if (!book.isAvailable()) {
            System.out.printf("\"%s\"은(는) 이미 대출 중입니다.%n", book.getTitle());
            return false;
        }

        LocalDate borrowedDate = LocalDate.now();
        book.markAsBorrowed(borrowedDate);
        System.out.printf(
                "\"%s\"이(가) 대출되었습니다. 최근 대출 날짜 업데이트: %s%n",
                book.getTitle(), borrowedDate);
        return true;
    }

    public boolean returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book == null) {
            printBookNotFound(title);
            return false;
        }
        if (book.isAvailable()) {
            System.out.printf("\"%s\"은(는) 현재 대출 중인 책이 아닙니다.%n", book.getTitle());
            return false;
        }

        book.markAsReturned();
        System.out.printf("\"%s\"이(가) 반납되었습니다.%n", book.getTitle());
        return true;
    }

    public Book findBook(String title) {
        Book book = findBookByTitle(title);
        if (book == null) {
            printBookNotFound(title);
            return null;
        }

        book.printInformation();
        return book;
    }

    public void printBooksByTitle() {
        printSortedBooks(null);
    }

    public void printBooksByAuthor() {
        printSortedBooks(new AuthorComparator());
    }

    public void printBooksByPublicationDate() {
        printSortedBooks(new PublicationDateComparator());
    }

    public void printBooksByTitleLength() {
        printSortedBooks(new TitleLengthComparator());
    }

    public void printLatestBorrowHistory() {
        List<Book> borrowedBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getRecentBorrowedDate() != null) {
                borrowedBooks.add(book);
            }
        }

        borrowedBooks.sort(new RecentBorrowedDateComparator());
        if (borrowedBooks.isEmpty()) {
            System.out.println("대출 내역이 없습니다.");
            return;
        }

        printBooks(borrowedBooks);
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    // 원본 등록 순서는 유지하고 복사한 목록에만 정렬 기준을 적용한다.
    private void printSortedBooks(Comparator<Book> comparator) {
        List<Book> sortedBooks = new ArrayList<>(books);

        if (comparator == null) {
            Collections.sort(sortedBooks);
        } else {
            sortedBooks.sort(comparator);
        }

        printBooks(sortedBooks);
    }

    private void printBooks(List<Book> booksToPrint) {
        if (booksToPrint.isEmpty()) {
            System.out.println("등록된 책이 없습니다.");
            return;
        }

        for (Book book : booksToPrint) {
            book.printInformation();
        }
    }

    private Book findBookByTitle(String title) {
        if (title == null || title.isBlank()) {
            return null;
        }

        for (Book book : books) {
            if (book.getTitle().equals(title.trim())) {
                return book;
            }
        }

        return null;
    }

    private boolean isValidBook(Book book) {
        return book != null
                && book.getTitle() != null
                && !book.getTitle().isBlank()
                && book.getAuthor() != null
                && !book.getAuthor().isBlank()
                && book.getPublicationDate() != null;
    }

    private void printBookNotFound(String title) {
        String searchedTitle = title == null || title.isBlank() ? "입력되지 않은 제목" : title;
        System.out.printf("\"%s\"에 해당하는 책을 찾을 수 없습니다.%n", searchedTitle);
    }
}
