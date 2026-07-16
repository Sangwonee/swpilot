package course04.question01;

import java.util.ArrayList;
import java.util.List;

class Book {
    public String isbn;
    public String title;
    public String author;
    public boolean isBorrowed;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }
}

abstract class User {
    public String userId;
    public String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public void borrowBook(Book book) {
        if(!book.isBorrowed) {
            book.isBorrowed = true;
        }
    }

    public void returnBook(Book book) {
        if(book.isBorrowed) {
            book.isBorrowed = false;
        }
    }

	abstract void addBook(Book book, Library library);
	abstract void removeBook(Book book, Library library); 

}

class Member extends User {
    public Member(String userId, String name) {
        super(userId, name);
    }
		
		public void addBook(Book book, Library library) {
				System.out.println("Member can't add book");
		}
		public void removeBook(Book book, Library library) {
				System.out.println("Member can't remove book");
		}
}

class Manager extends User {
    public Manager(String userId, String name) {
        super(userId, name);
    }

		public void addBook(Book book, Library library) {
				library.addBook(book);
		}

		public void removeBook(Book book, Library library) {
				library.removeBook(book);
		}
}


class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public Book writeBook(String isbn, String title, String author) {
        Book book = new Book(isbn, title, author);
        books.add(book);
        return book;
    }
		public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addMember(Member member) {
        users.add(member);
    }

    public void addManager(Manager manager) {
        users.add(manager);
    }
}

public class BiodomeFamily08_Before {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = library.writeBook("0001", "Book1", "Author1");
        Book book2 = library.writeBook("0002", "Book2", "Author2");

        Member member = new Member("U001", "Kim");
        Manager manager = new Manager("U002", "Song");

        library.addMember(member);
        library.addManager(manager);

        member.borrowBook(book1);
        System.out.println("책을 빌립니다: " + book1.title);
        member.returnBook(book1);
        System.out.println("책을 반납합니다: " + book1.title);
    }
}
