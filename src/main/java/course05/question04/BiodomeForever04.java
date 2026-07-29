package course05.question04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BiodomeForever04 {
    private static final int INPUT_DATA_OPTION = 1;
    private static final int VIEW_ALL_OPTION = 2;
    private static final int VIEW_OXYGEN_OPTION = 3;
    private static final int EXIT_OPTION = 4;

    public static void main(String[] args) {
        EnvironmentDataManager dataManager = new EnvironmentDataManager();
        BufferedReader consoleReader =
                new BufferedReader(new InputStreamReader(System.in));

        System.out.println("환경 정보 관리 시스템에 오신 것을 환영합니다.");

        try {
            runProgram(consoleReader, dataManager);
        } catch (IOException e) {
            System.out.println("사용자 입력을 읽는 중 오류가 발생했습니다.");
        }
    }

    // 사용자가 종료를 선택하거나 입력 스트림이 끝날 때까지 메뉴를 반복한다.
    private static void runProgram(
            BufferedReader consoleReader,
            EnvironmentDataManager dataManager) throws IOException {
        while (true) {
            printMenu();
            String selectedValue = readLine(consoleReader, "선택: ");

            if (selectedValue == null) {
                printExitMessage();
                return;
            }

            Integer selectedOption = parseMenuOption(selectedValue);
            if (selectedOption == null) {
                System.out.println("1번부터 4번 중 하나를 선택해주세요.");
                continue;
            }

            switch (selectedOption) {
                case INPUT_DATA_OPTION -> inputEnvironmentData(
                        consoleReader, dataManager);
                case VIEW_ALL_OPTION -> printAllData(dataManager);
                case VIEW_OXYGEN_OPTION -> printOxygenLevels(dataManager);
                case EXIT_OPTION -> {
                    printExitMessage();
                    return;
                }
                default -> System.out.println(
                        "1번부터 4번 중 하나를 선택해주세요.");
            }
        }
    }

    private static void inputEnvironmentData(
            BufferedReader consoleReader,
            EnvironmentDataManager dataManager) throws IOException {
        Double temperature = readNumber(consoleReader, "온도를 입력하세요: ", "온도");
        if (temperature == null) {
            return;
        }

        Double humidity = readNumber(consoleReader, "습도를 입력하세요: ", "습도");
        if (humidity == null) {
            return;
        }

        Double oxygenLevel = readNumber(
                consoleReader, "산소 농도를 입력하세요: ", "산소 농도");
        if (oxygenLevel == null) {
            return;
        }

        String location = readLine(consoleReader, "측정 장소를 입력하세요: ");
        if (location == null || location.isBlank()) {
            System.out.println("측정 장소를 입력해주세요.");
            return;
        }

        EnvironmentData environmentData = new EnvironmentData(
                temperature, humidity, oxygenLevel, location.trim());

        try {
            dataManager.save(environmentData);
            System.out.printf(
                    "데이터가 %s에 저장되었습니다.%n",
                    dataManager.getDataFile().getFileName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("환경 데이터를 파일에 저장하는 중 오류가 발생했습니다.");
        }
    }

    private static void printAllData(EnvironmentDataManager dataManager) {
        try {
            dataManager.printAllData();
        } catch (IOException e) {
            System.out.println("환경 데이터 파일을 불러오는 중 오류가 발생했습니다.");
        }
    }

    private static void printOxygenLevels(
            EnvironmentDataManager dataManager) {
        try {
            dataManager.printOxygenLevels();
        } catch (IOException e) {
            System.out.println("산소 수치 데이터를 불러오는 중 오류가 발생했습니다.");
        }
    }

    private static Double readNumber(
            BufferedReader consoleReader,
            String prompt,
            String fieldName) throws IOException {
        String value = readLine(consoleReader, prompt);
        if (value == null) {
            return null;
        }

        try {
            double number = Double.parseDouble(value.trim());
            if (!Double.isFinite(number)) {
                throw new NumberFormatException();
            }
            return number;
        } catch (NumberFormatException e) {
            System.out.printf(
                    "잘못된 값입니다. %s는 숫자로 입력해주세요.%n",
                    fieldName);
            return null;
        }
    }

    private static Integer parseMenuOption(String selectedValue) {
        try {
            int selectedOption = Integer.parseInt(selectedValue.trim());
            if (selectedOption < INPUT_DATA_OPTION
                    || selectedOption > EXIT_OPTION) {
                return null;
            }
            return selectedOption;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static String readLine(
            BufferedReader consoleReader,
            String prompt) throws IOException {
        System.out.print(prompt);
        return consoleReader.readLine();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. 새로운 환경 데이터 입력");
        System.out.println("2. 모든 환경 데이터 조회");
        System.out.println("3. 날짜별 산소 수치만 조회");
        System.out.println("4. 프로그램 종료");
    }

    private static void printExitMessage() {
        System.out.println("프로그램을 종료합니다. 감사합니다.");
    }
}
