package course05.question02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResearchJournal {
    private static final int WRITTEN_AT_LENGTH = 12;

    private final String fileName;
    private final String content;

    public ResearchJournal(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public String getFormattedWrittenAt() throws ParseException {
        String dateText = extractDateText();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        inputFormat.setLenient(false);

        Date writtenAt = inputFormat.parse(dateText);
        return outputFormat.format(writtenAt);
    }

    public void printContent() {
        System.out.println(content);
    }

    public void printInformation() throws ParseException {
        System.out.println(getFormattedWrittenAt());
        printContent();
    }

    // 파일명의 첫 구분자 앞에서 최대 12자리를 꺼내 SimpleDateFormat이 검증하게 한다.
    private String extractDateText() {
        int separatorIndex = fileName.indexOf('_');
        int dateTextEnd = separatorIndex < 0
                ? Math.min(fileName.length(), WRITTEN_AT_LENGTH)
                : Math.min(separatorIndex, WRITTEN_AT_LENGTH);
        return fileName.substring(0, dateTextEnd);
    }
}
