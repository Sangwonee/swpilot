package course04.question08;

import java.time.LocalDate;

public class RuleOfBiodoem08 {

    public static void main(String[] args) {
        Book pythonMaster = new Book(
                "파이썬 마스터", "한송희", LocalDate.of(2020, 1, 1));
        Book cloudOfJava = new Book(
                "자바의 구름", "제임스밥", LocalDate.of(2018, 5, 5));
        Book energyFlow = new Book(
                "에너지 플로우", "키네틱스", LocalDate.of(2019, 8, 15));
        Book memoryOfMars = new Book(
                "화성에서의 기억", "한송희", LocalDate.of(2021, 3, 3));
        Book secretOfVegetables = new Book(
                "야채의 비밀", "송은정", LocalDate.of(2017, 10, 10));

        System.out.println("(도서 객체 생성)");
        pythonMaster.printInformation();
        cloudOfJava.printInformation();
        energyFlow.printInformation();
        memoryOfMars.printInformation();
        secretOfVegetables.printInformation();

        System.out.println();
        System.out.println("(도서관 시스템 생성 및 등록)");
        Library library = new Library();
        library.addBook(pythonMaster);
        library.addBook(cloudOfJava);
        library.addBook(energyFlow);
        library.addBook(memoryOfMars);
        library.addBook(secretOfVegetables);

        System.out.println();
        System.out.println("(대출1)");
        library.borrowBook("야채의 비밀");

        System.out.println();
        System.out.println("(대출2)");
        library.borrowBook("화성에서의 기억");

        System.out.println();
        System.out.println("(반납)");
        library.returnBook("야채의 비밀");

        System.out.println();
        System.out.println("(제목 기반 정렬 조회)");
        library.printBooksByTitle();

        System.out.println();
        System.out.println("(저자 기반 정렬 조회)");
        library.printBooksByAuthor();

        System.out.println();
        System.out.println("(출판일 기반 정렬 조회)");
        library.printBooksByPublicationDate();

        System.out.println();
        System.out.println("(최신 대출 내역 조회)");
        library.printLatestBorrowHistory();

        System.out.println();
        System.out.println("(보너스: 제목 길이 기반 정렬 조회)");
        library.printBooksByTitleLength();

        demonstrateExceptions(library);
    }

    // 평가 항목인 중복 대출과 존재하지 않는 도서의 조회 및 대출을 확인한다.
    private static void demonstrateExceptions(Library library) {
        System.out.println();
        System.out.println("[예외 상황 확인]");
        library.borrowBook("화성에서의 기억");
        library.borrowBook("존재하지 않는 책");
        library.findBook("존재하지 않는 책");
    }
}
