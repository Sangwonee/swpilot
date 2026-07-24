package course05.question01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResearchJournalManager {
    private static final String TEXT_EXTENSION = ".txt";
    private static final String PROVIDED_FILE_PREFIX = "5-1-";
    private static final int WRITTEN_AT_LENGTH = 12;
    private static final DateTimeFormatter FILE_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMddHHmm");

    private final List<Path> journalDirectories;

    public ResearchJournalManager() {
        journalDirectories = List.of(
                Path.of("."),
                Path.of("src/main/resources/course05/question01"));
    }

    public ResearchJournal openJournal(String fileName) {
        if (!isValidFileName(fileName)) {
            System.out.println("올바른 .txt 연구일지 파일 이름을 입력해주세요.");
            return null;
        }

        FileInputStream inputStream = null;

        try {
            File journalFile = findJournalFile(fileName);
            inputStream = new FileInputStream(journalFile);
            byte[] contentBytes = inputStream.readAllBytes();
            String content = new String(contentBytes, StandardCharsets.UTF_8);
            LocalDateTime writtenAt = parseWrittenAt(fileName);

            return new ResearchJournal(removeProvidedFilePrefix(fileName), content, writtenAt);
        } catch (FileNotFoundException e) {
            System.out.println("존재하지 않는 파일입니다. 파일 이름을 다시 확인해주세요.");
        } catch (DateTimeException e) {
            System.out.println("파일 이름의 작성일자 형식이 올바르지 않습니다.");
        } catch (IOException e) {
            System.out.println("연구일지 파일을 읽는 중 오류가 발생했습니다.");
        } finally {
            closeInputStream(inputStream);
        }

        return null;
    }

    public void printJournal(String fileName) {
        ResearchJournal journal = openJournal(fileName);
        if (journal != null) {
            journal.printContent();
        }
    }

    // 검색 폴더의 파일명에서 작성일자를 추출해 같은 날짜의 연구일지만 불러온다.
    public List<ResearchJournal> findJournalsByDate(LocalDate writtenDate) {
        if (writtenDate == null) {
            throw new IllegalArgumentException("검색할 작성일자를 입력해주세요.");
        }

        List<ResearchJournal> foundJournals = new ArrayList<>();
        Set<String> loadedPaths = new HashSet<>();

        for (Path directory : journalDirectories) {
            File[] journalFiles = directory.toFile().listFiles(
                    file -> file.isFile() && file.getName().endsWith(TEXT_EXTENSION));

            if (journalFiles == null) {
                continue;
            }

            for (File journalFile : journalFiles) {
                String absolutePath = journalFile.getAbsolutePath();
                if (!loadedPaths.add(absolutePath)) {
                    continue;
                }

                LocalDateTime fileWrittenAt = tryParseWrittenAt(journalFile.getName());
                if (fileWrittenAt == null || !fileWrittenAt.toLocalDate().equals(writtenDate)) {
                    continue;
                }

                ResearchJournal journal = openJournal(journalFile.getName());
                if (journal != null) {
                    foundJournals.add(journal);
                }
            }
        }

        foundJournals.sort((first, second) ->
                first.getWrittenAt().compareTo(second.getWrittenAt()));
        return foundJournals;
    }

    public void printJournalsByDate(LocalDate writtenDate) {
        List<ResearchJournal> journals = findJournalsByDate(writtenDate);

        if (journals.isEmpty()) {
            System.out.printf("%s에 작성된 연구일지가 없습니다.%n", writtenDate);
            return;
        }

        for (ResearchJournal journal : journals) {
            journal.printInformation();
            System.out.println();
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

    private boolean isValidFileName(String fileName) {
        if (fileName == null || fileName.isBlank() || !fileName.endsWith(TEXT_EXTENSION)) {
            return false;
        }

        try {
            Path path = Path.of(fileName);
            return path.getNameCount() == 1;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private LocalDateTime parseWrittenAt(String fileName) {
        String normalizedFileName = removeProvidedFilePrefix(fileName);
        int separatorIndex = normalizedFileName.indexOf('_');
        if (separatorIndex != WRITTEN_AT_LENGTH) {
            throw new DateTimeException("잘못된 연구일지 파일명");
        }

        String dateTimeText = normalizedFileName.substring(0, separatorIndex);
        return LocalDateTime.parse(dateTimeText, FILE_DATE_TIME_FORMATTER);
    }

    private String removeProvidedFilePrefix(String fileName) {
        if (fileName.startsWith(PROVIDED_FILE_PREFIX)) {
            return fileName.substring(PROVIDED_FILE_PREFIX.length());
        }

        return fileName;
    }

    private LocalDateTime tryParseWrittenAt(String fileName) {
        try {
            return parseWrittenAt(fileName);
        } catch (DateTimeException | IndexOutOfBoundsException e) {
            return null;
        }
    }

    private void closeInputStream(FileInputStream inputStream) {
        if (inputStream == null) {
            return;
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            System.out.println("연구일지 파일을 닫는 중 오류가 발생했습니다.");
        }
    }
}
