package course05.question03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ResearchJournalExtractor {
    private static final String TEXT_EXTENSION = ".txt";
    private static final String SUMMARY_FILE_SUFFIX = "_Lumino_ADR.txt";
    private static final String NAME_PREFIX = "Name.";
    private static final String ADDRESS_PREFIX = "ADR.";
    private static final DateTimeFormatter MINUTE_FILE_NAME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    private static final DateTimeFormatter SECOND_FILE_NAME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final Path journalDirectory;
    private final List<ResearchJournal> journals;
    private int savedPlantCount;

    public ResearchJournalExtractor(String directoryPath) throws IOException, NoDataAvailableException {
        journalDirectory = validateDirectory(directoryPath);
        journals = loadJournals();

        if (journals.isEmpty()) {
            throw new NoDataAvailableException("분석할 파일이 없습니다.");
        }
    }

    // 폴더의 연구일지만 이름순으로 읽고, 이전에 생성된 요약 파일은 제외한다.
    private List<ResearchJournal> loadJournals() throws IOException {
        File[] journalFiles = journalDirectory.toFile().listFiles(file -> file.isFile()
                && file.getName().endsWith(TEXT_EXTENSION)
                && !file.getName().endsWith(SUMMARY_FILE_SUFFIX));

        if (journalFiles == null) {
            throw new FileNotFoundException(journalDirectory.toString());
        }

        Arrays.sort(journalFiles, (first, second) ->
                first.getName().compareTo(second.getName()));

        List<ResearchJournal> loadedJournals = new ArrayList<>();
        for (File journalFile : journalFiles) {
            try (FileInputStream inputStream = new FileInputStream(journalFile)) {
                String content = new String(
                        inputStream.readAllBytes(), StandardCharsets.UTF_8);
                loadedJournals.add(new ResearchJournal(
                        journalFile.getName(), content));
            }
        }

        return loadedJournals;
    }

    // 유효한 이름과 주소를 추출하고 중복을 제거한 뒤 같은 폴더에 저장한다.
    public Path savePlantAddresses() throws IOException {
        Set<PlantAddress> plantAddresses = new LinkedHashSet<>();

        for (ResearchJournal journal : journals) {
            PlantAddress plantAddress = extractPlantAddress(journal);
            if (plantAddress != null) {
                plantAddresses.add(plantAddress);
            }
        }

        Path outputFile = createOutputFilePath();
        String outputContent = createOutputContent(plantAddresses);

        try (FileOutputStream outputStream =
                     new FileOutputStream(outputFile.toFile())) {
            outputStream.write(outputContent.getBytes(StandardCharsets.UTF_8));
        }

        savedPlantCount = plantAddresses.size();
        return outputFile;
    }

    public List<ResearchJournal> getJournals() {
        return Collections.unmodifiableList(journals);
    }

    public int getSavedPlantCount() {
        return savedPlantCount;
    }

    private PlantAddress extractPlantAddress(ResearchJournal journal) {
        String plantName = findValue(journal.getContent(), NAME_PREFIX);
        String address = findValue(journal.getContent(), ADDRESS_PREFIX);

        if (plantName == null || address == null) {
            System.out.printf(
                    "%s: 식물명 또는 주소 정보가 누락되었습니다.%n",
                    journal.getFileName());
            return null;
        }

        return new PlantAddress(plantName, address);
    }

    // 정규식 대신 줄바꿈 위치와 접두사를 직접 찾아 항목 값을 꺼낸다.
    private String findValue(String content, String prefix) {
        int lineStart = 0;

        while (lineStart <= content.length()) {
            int lineEnd = content.indexOf('\n', lineStart);
            if (lineEnd < 0) {
                lineEnd = content.length();
            }

            String line = content.substring(lineStart, lineEnd);
            if (line.endsWith("\r")) {
                line = line.substring(0, line.length() - 1);
            }

            String trimmedLine = line.trim();
            if (trimmedLine.startsWith(prefix)) {
                String value = trimmedLine.substring(prefix.length()).trim();
                return value.isEmpty() ? null : value;
            }

            if (lineEnd == content.length()) {
                break;
            }
            lineStart = lineEnd + 1;
        }

        return null;
    }

    private String createOutputContent(Set<PlantAddress> plantAddresses) {
        StringBuilder contentBuilder = new StringBuilder();

        for (PlantAddress plantAddress : plantAddresses) {
            contentBuilder.append(plantAddress.toOutputLine())
                    .append(System.lineSeparator());
        }

        return contentBuilder.toString();
    }

    private Path createOutputFilePath() {
        LocalDateTime now = LocalDateTime.now();
        Path outputFile = journalDirectory.resolve(
                now.format(MINUTE_FILE_NAME_FORMATTER) + SUMMARY_FILE_SUFFIX);

        if (!Files.exists(outputFile)) {
            return outputFile;
        }

        String secondFileName =
                now.format(SECOND_FILE_NAME_FORMATTER) + SUMMARY_FILE_SUFFIX;
        outputFile = journalDirectory.resolve(secondFileName);

        int sequence = 2;
        while (Files.exists(outputFile)) {
            outputFile = journalDirectory.resolve(
                    now.format(SECOND_FILE_NAME_FORMATTER)
                            + "_" + sequence + SUMMARY_FILE_SUFFIX);
            sequence++;
        }

        return outputFile;
    }

    private Path validateDirectory(String directoryPath)
            throws FileNotFoundException {
        if (directoryPath == null || directoryPath.isBlank()) {
            throw new FileNotFoundException("폴더 경로가 비어 있습니다.");
        }

        Path directory;
        try {
            directory = Path.of(directoryPath).normalize();
        } catch (RuntimeException e) {
            throw new FileNotFoundException(directoryPath);
        }

        if (!Files.isDirectory(directory)) {
            throw new FileNotFoundException(directoryPath);
        }

        return directory;
    }
}
