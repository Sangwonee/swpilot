package course05.question01;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BiodomeForever01 {
    private static final String DATE_SEARCH_OPTION = "--date";

    public static void main(String[] args) {
        ResearchJournalManager journalManager = new ResearchJournalManager();

        if (args.length == 1) {
            journalManager.printJournal(args[0]);
            return;
        }

        if (args.length == 2 && DATE_SEARCH_OPTION.equals(args[0])) {
            printJournalsByDate(journalManager, args[1]);
            return;
        }

        printUsage();
    }

    private static void printJournalsByDate(ResearchJournalManager journalManager,String dateText) {
        try {
            LocalDate writtenDate = LocalDate.parse(dateText);
            journalManager.printJournalsByDate(writtenDate);
        } catch (DateTimeParseException e) {
            System.out.println("작성일자는 yyyy-MM-dd 형식으로 입력해주세요.");
        }
    }

    private static void printUsage() {
        System.out.println("연구일지 파일 이름을 확장자와 함께 입력해주세요.");
        System.out.println("작성일자 검색: --date yyyy-MM-dd");
    }
}
