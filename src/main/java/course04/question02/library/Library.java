package course04.question02.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import course04.question02.book.Book;
import course04.question02.user.Manager;
import course04.question02.user.User;

/** 모든 사용자와 도서를 보관하며 대출 거래를 일관되게 처리한다. */
public class Library implements LoanService, BookCatalog {

    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public boolean registerUser(User user) {
        if (user == null) {
            System.out.println("등록할 사용자 정보가 없습니다.");
            return false;
        }
        if (findUserById(user.getUserId()) != null) {
            System.out.printf("사용자 아이디 '%s'은(는) 이미 등록되어 있습니다.%n", user.getUserId());
            return false;
        }

        users.add(user);
        System.out.printf("새로운 %s '%s'을(를) 등록합니다.%n", user.getUserType(), user.getName());
        return true;
    }

    @Override
    public boolean addBook(Manager manager, Book book) {
        if (!isRegistered(manager)) {
            System.out.println("등록된 관리자만 책을 추가할 수 있습니다.");
            return false;
        }
        if (book == null) {
            System.out.println("추가할 책 정보가 없습니다.");
            return false;
        }
        if (findBookByIsbn(book.getIsbn()) != null) {
            System.out.printf("ISBN '%s'인 책은 이미 등록되어 있습니다.%n", book.getIsbn());
            return false;
        }

        books.add(book);
        System.out.printf("관리자 '%s'가 책을 추가합니다: '%s', '%s'%n",
                manager.getName(), book.getTitle(), book.getAuthor());
        return true;
    }

    @Override
    public boolean removeBook(Manager manager, Book book) {
        if (!isRegistered(manager)) {
            System.out.println("등록된 관리자만 책을 삭제할 수 있습니다.");
            return false;
        }
        if (!books.contains(book)) {
            System.out.println("등록되지 않은 책은 삭제할 수 없습니다.");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.printf("'%s'은(는) 대출 중이므로 삭제할 수 없습니다.%n", book.getTitle());
            return false;
        }

        books.remove(book);
        System.out.printf(
                "관리자 '%s'가 책을 삭제합니다: '%s'%n", manager.getName(), book.getTitle());
        return true;
    }

    @Override
    public boolean borrowBook(User user, Book book) {
        if (!isRegistered(user)) {
            System.out.println("등록된 사용자만 책을 대출할 수 있습니다.");
            return false;
        }
        if (!books.contains(book)) {
            System.out.println("등록되지 않은 책은 대출할 수 없습니다.");
            return false;
        }
        if (!book.canBeBorrowedBy(user)) {
            System.out.printf("'%s'은(는) 학생만 대출할 수 있는 교과서입니다.%n", book.getTitle());
            return false;
        }
        if (!book.isAvailable()) {
            System.out.printf("'%s'은(는) 대출 중입니다.%n", book.getTitle());
            return false;
        }

        book.markAsBorrowed();
        user.addBorrowedBook(book);
        System.out.printf(
                "%s '%s'이(가) '%s'을(를) 대출합니다.%n",
                user.getUserType(), user.getName(), book.getTitle());
        return true;
    }

    @Override
    public boolean returnBook(User user, Book book) {
        if (!isRegistered(user)) {
            System.out.println("등록된 사용자만 책을 반납할 수 있습니다.");
            return false;
        }
        if (!books.contains(book)) {
            System.out.printf(
                    "등록되지 않은 책 '%s'은(는) 반납할 수 없습니다.%n",
                    book == null ? "알 수 없음" : book.getTitle());
            return false;
        }
        if (!user.hasBorrowed(book)) {
            System.out.printf("'%s'이(가) 대출한 책이 아니므로 반납할 수 없습니다.%n", user.getName());
            return false;
        }

        book.markAsReturned();
        user.removeBorrowedBook(book);
        System.out.printf("%s '%s'이(가) '%s'을(를) 반납합니다.%n",
                user.getUserType(), user.getName(), book.getTitle());
        return true;
    }

    public void printBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                foundBooks.add(book);
            }
        }

        if (foundBooks.isEmpty()) {
            System.out.printf("저자 '%s'의 책을 찾을 수 없습니다.%n", author);
            return;
        }

        System.out.printf("저자 '%s'의 책 목록:%n", author);
        for (Book book : foundBooks) {
            System.out.printf("- %s, %s%n",
                    book.getTitle(), book.isAvailable() ? "대출 가능" : "대출 불가");
        }
    }

    public void printAllBooks() {
        System.out.println("[도서관의 모든 책]");
        if (books.isEmpty()) {
            System.out.println("등록된 책이 없습니다.");
            return;
        }

        for (Book book : books) {
            book.printInformation();
        }
    }

    public void printAllUsers() {
        System.out.println("[도서관의 모든 회원]");
        if (users.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }

        for (User user : users) {
            user.printInformation();
        }
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    private boolean isRegistered(User user) {
        return user != null && users.contains(user);
    }

    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
