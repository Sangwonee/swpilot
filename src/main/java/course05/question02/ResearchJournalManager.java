package course05.question02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;

public class ResearchJournalManager {
    private static final String TEXT_EXTENSION = ".txt";
    private static final String PROVIDED_FILE_PREFIX = "5-2-";

    private final List<Path> journalDirectories;

    public ResearchJournalManager() {
        journalDirectories = List.of(
                Path.of("."),
                Path.of("src/main/resources/course05/question02"));
    }

    public void printJournal(String fileName) {
        FileInputStream inputStream = null;

        try {
            validateFileName(fileName);
            File journalFile = findJournalFile(fileName);
            inputStream = new FileInputStream(journalFile);

            byte[] contentBytes = inputStream.readAllBytes();
            String content = new String(contentBytes, StandardCharsets.UTF_8);
            if (content.isBlank()) {
                throw new EmptyFileException("파일에 내용이 존재하지 않습니다.");
            }

            ResearchJournal journal = new ResearchJournal(
                    removeProvidedFilePrefix(fileName), content);
            journal.printInformation();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("존재하지 않는 파일입니다. 파일 이름을 다시 확인해주세요.");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("파일 이름에서 날짜를 확인할 수 없습니다.");
        } catch (EmptyFileException e) {
            e.printStackTrace();
            System.out.println("내용이 없는 연구일지입니다.");
        } catch (SecurityException e) {
            e.printStackTrace();
            System.out.println("보안 또는 권한 문제로 파일에 접근할 수 없습니다.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("연구일지 파일을 읽는 중 오류가 발생했습니다.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("올바른 .txt 연구일지 파일 이름을 입력해주세요.");
        } finally {
            closeInputStream(inputStream);
        }
    }

    private File findJournalFile(String fileName) throws FileNotFoundException {
        for (Path directory : journalDirectories) {
            File candidate = directory.resolve(fileName).normalize().toFile();
            if (candidate.isFile()) {
                return candidate;
            }

            if (!fileName.startsWith(PROVIDED_FILE_PREFIX)) {
                File providedCandidate = directory
                        .resolve(PROVIDED_FILE_PREFIX + fileName)
                        .normalize()
                        .toFile();
                if (providedCandidate.isFile()) {
                    return providedCandidate;
                }
            }
        }

        throw new FileNotFoundException(fileName);
    }

    private void validateFileName(String fileName) {
        if (fileName == null || fileName.isBlank() || !fileName.endsWith(TEXT_EXTENSION)) {
            throw new IllegalArgumentException("잘못된 파일 이름");
        }

        Path path = Path.of(fileName);
        if (path.getNameCount() != 1) {
            throw new IllegalArgumentException("파일 이름만 입력해야 합니다.");
        }
    }

    private String removeProvidedFilePrefix(String fileName) {
        if (fileName.startsWith(PROVIDED_FILE_PREFIX)) {
            return fileName.substring(PROVIDED_FILE_PREFIX.length());
        }

        return fileName;
    }

    private void closeInputStream(FileInputStream inputStream) {
        if (inputStream == null) {
            return;
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("연구일지 파일을 닫는 중 오류가 발생했습니다.");
        }
    }
}
