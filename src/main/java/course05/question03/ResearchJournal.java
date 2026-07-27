package course05.question03;

public class ResearchJournal {
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

    public void printContent() {
        System.out.println(content);
    }
}
