package course05.question04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EnvironmentDataManager {
    private static final String DEFAULT_FILE_NAME = "environment_data.txt";

    private final Path dataFile;

    public EnvironmentDataManager() {
        this(Path.of(DEFAULT_FILE_NAME));
    }

    public EnvironmentDataManager(Path dataFile) {
        this.dataFile = dataFile;
    }

    // 기존 기록을 보존하기 위해 append 모드로 한 줄씩 저장한다.
    public void save(EnvironmentData environmentData) throws IOException {
        validateEnvironmentData(environmentData);

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(dataFile.toFile(), true))) {
            writer.write(environmentData.toFileLine());
            writer.newLine();
        }
    }

    public void printAllData() throws IOException {
        if (!hasSavedData()) {
            System.out.println("저장된 환경 데이터가 없습니다.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(dataFile.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    // 저장된 각 줄을 환경 정보 객체로 복원해 산소 농도와 측정 위치만 출력한다.
    public void printOxygenLevels() throws IOException {
        if (!hasSavedData()) {
            System.out.println("저장된 환경 데이터가 없습니다.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(dataFile.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    EnvironmentData environmentData =
                            EnvironmentData.fromFileLine(line);
                    System.out.println(environmentData.toOxygenInformation());
                } catch (RuntimeException e) {
                    System.out.println(
                            "형식이 올바르지 않은 환경 데이터는 조회에서 제외했습니다.");
                }
            }
        }
    }

    public Path getDataFile() {
        return dataFile;
    }

    private boolean hasSavedData() throws IOException {
        return Files.exists(dataFile) && Files.size(dataFile) > 0;
    }

    private void validateEnvironmentData(EnvironmentData environmentData) {
        if (environmentData == null) {
            throw new IllegalArgumentException("저장할 환경 데이터가 없습니다.");
        }
        if (environmentData.getMeasuredAt() == null) {
            throw new IllegalArgumentException("측정 날짜와 시간이 없습니다.");
        }
        if (!Double.isFinite(environmentData.getTemperature())
                || !Double.isFinite(environmentData.getHumidity())
                || !Double.isFinite(environmentData.getOxygenLevel())) {
            throw new IllegalArgumentException("환경 정보는 유효한 숫자여야 합니다.");
        }
        if (environmentData.getLocation() == null
                || environmentData.getLocation().isBlank()) {
            throw new IllegalArgumentException("측정 장소를 입력해주세요.");
        }
    }
}
