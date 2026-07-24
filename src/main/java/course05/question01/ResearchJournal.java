package course05.question01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResearchJournal {
    private static final DateTimeFormatter WRITTEN_AT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final String fileName;
    private final String content;
    private final LocalDateTime writtenAt;

    public ResearchJournal(String fileName, String content, LocalDateTime writtenAt) {
        this.fileName = fileName;
        this.content = content;
        this.writtenAt = writtenAt;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getWrittenAt() {
        return writtenAt;
    }

    public void printContent() {
        System.out.println(content);
    }

    public void printInformation() {
        System.out.printf("파일명: %s, 작성일자: %s%n",
                fileName, writtenAt.format(WRITTEN_AT_FORMATTER));
        printContent();
    }
}
