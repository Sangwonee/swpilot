package course05.question02;

public class BiodomeForever02 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("연구일지 파일 이름을 확장자와 함께 입력해주세요.");
            return;
        }

        ResearchJournalManager journalManager = new ResearchJournalManager();
        journalManager.printJournal(args[0]);
    }
}
