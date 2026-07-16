package course04.question02;

import course04.question02.book.Book;
import course04.question02.book.Magazine;
import course04.question02.book.Textbook;
import course04.question02.library.Library;
import course04.question02.user.Manager;
import course04.question02.user.Member;
import course04.question02.user.Student;

/**
 * 도서관 객체를 조립하고 요구사항의 동작을 시뮬레이션하는 실행 클래스이다.
 */
public class RuleOfBodome02 {

    public static void main(String[] args) {
        Library library = new Library();

        Manager manager = new Manager("U001", "세이코");
        Member mary = new Member("U002", "메리");
        Member manok = new Member("U003", "만옥");

        library.registerUser(manager);
        library.registerUser(mary);
        library.registerUser(manok);

        System.out.println();

        Book javaCloud = new Book("자바의 구름", "제임스밥", "ISBN-001");
        Book pythonMaster = new Book("파이썬 마스터", "한송희", "ISBN-002");
        Book energyFlow = new Book("에너지 플로우", "키네틱스", "ISBN-003");
        Book memoriesOfMars = new Book("화성에서의 기억", "한송희", "ISBN-004");
        Book vegetableSecret = new Book("야채의 비밀", "송은정", "ISBN-005");

        manager.addBook(javaCloud, library);
        manager.addBook(pythonMaster, library);
        manager.addBook(energyFlow, library);
        manager.addBook(memoriesOfMars, library);
        manager.addBook(vegetableSecret, library);

        System.out.println();
        mary.borrowBook(javaCloud, library);

        System.out.println();

        Book dataStructureHill = new Book("자료구조의 언덕", "황수", "ISBN-006");
        Book whenYouGoThere = new Book("그곳에 가면", "한송희", "ISBN-007");
        manager.addBook(dataStructureHill, library);
        manager.addBook(whenYouGoThere, library);

        System.out.println();
        manok.borrowBook(javaCloud, library);
        mary.returnBook(javaCloud, library);
        manager.borrowBook(memoriesOfMars, library);

        System.out.println();
        library.printBooksByAuthor("한송희");

        System.out.println();
        library.printAllBooks();
        System.out.println();
        library.printAllUsers();

        demonstrateExceptionCases(library, manager, mary, memoriesOfMars);
        demonstrateBonusFeatures(library, manager, mary);
    }

    private static void demonstrateExceptionCases(
            Library library, Manager manager, Member member, Book borrowedBook) {
        System.out.println("\n[예외 상황 확인]");

        Book unregisteredBook = new Book("등록되지 않은 책", "알 수 없음", "UNKNOWN");
        member.returnBook(unregisteredBook, library);
        library.printBooksByAuthor("없는 저자");
        manager.removeBook(borrowedBook, library);
    }

    private static void demonstrateBonusFeatures(
            Library library, Manager manager, Member ordinaryMember) {
        System.out.println("\n[보너스 기능 확인]");

        Student student = new Student("U004", "지우");
        library.registerUser(student);

        Textbook textbook = new Textbook("생태학 교과서", "김생태", "ISBN-008");
        Magazine magazine = new Magazine("바이오돔 월간지", "돔 편집부", "ISBN-009");
        manager.addBook(textbook, library);
        manager.addBook(magazine, library);

        ordinaryMember.borrowBook(textbook, library);
        student.borrowBook(textbook, library);
        student.returnBook(textbook, library);
        ordinaryMember.borrowBook(magazine, library);
        ordinaryMember.returnBook(magazine, library);
    }
}
