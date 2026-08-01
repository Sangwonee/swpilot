package course06.question09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RunBiodome09 {
    private static final int VIEW_WATER_OPTION = 1;
    private static final int ALLOCATE_WATER_OPTION = 2;
    private static final int EXIT_OPTION = 3;
    private static final String BONUS_MODE = "bonus";

    public static void main(String[] args) {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        List<WaterCity> cities = createCities();
        boolean useReentrantLock = isBonusMode(args);

        System.out.println("수자원 관리 시스템에 오신걸 환영합니다.");
        if (useReentrantLock) {
            System.out.println("ReentrantLock 보너스 모드로 실행합니다.");
        }

        try {
            runProgram(reader, cities, useReentrantLock);
        } catch (IOException e) {
            System.out.println("사용자 입력을 읽는 중 오류가 발생했습니다.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("물 분배 완료를 기다리는 중 종료 요청을 받았습니다.");
        }
    }

    // 종료를 선택하거나 입력이 끝날 때까지 조회와 물 할당 메뉴를 반복한다.
    private static void runProgram(
            BufferedReader reader,
            List<WaterCity> cities,
            boolean useReentrantLock)
            throws IOException, InterruptedException {
        while (true) {
            printMenu();
            String selectedValue = readLine(reader, "메뉴를 선택하세요: ");

            if (selectedValue == null) {
                printExitMessage();
                return;
            }

            Integer selectedOption = parseInteger(selectedValue);
            if (selectedOption == null) {
                System.out.println("1번부터 3번 중 하나를 선택해주세요.");
                continue;
            }

            switch (selectedOption) {
                case VIEW_WATER_OPTION -> printAllWater(cities);
                case ALLOCATE_WATER_OPTION -> allocateWaterToCities(
                        reader, cities, useReentrantLock);
                case EXIT_OPTION -> {
                    printExitMessage();
                    return;
                }
                default -> System.out.println(
                        "1번부터 3번 중 하나를 선택해주세요.");
            }
        }
    }

    // 네 요청을 모두 먼저 입력받은 뒤 동시에 시작하고 join으로 완료를 기다린다.
    private static void allocateWaterToCities(
            BufferedReader reader,
            List<WaterCity> cities,
            boolean useReentrantLock)
            throws IOException, InterruptedException {
        List<Integer> requestedAmounts = new ArrayList<>();
        for (WaterCity city : cities) {
            Integer requestedWater = readNonNegativeWater(
                    reader,
                    city.getName() + "에 필요한 물의 양을 입력하세요: ");
            if (requestedWater == null) {
                return;
            }
            requestedAmounts.add(requestedWater);
        }

        List<WaterRequestThread> requestThreads = new ArrayList<>();
        for (int index = 0; index < cities.size(); index++) {
            requestThreads.add(new WaterRequestThread(
                    cities.get(index),
                    requestedAmounts.get(index),
                    useReentrantLock));
        }

        System.out.println("===== 물 분배 시작 =====");
        for (WaterRequestThread requestThread : requestThreads) {
            requestThread.start();
        }

        try {
            for (WaterRequestThread requestThread : requestThreads) {
                requestThread.join();
            }
        } catch (InterruptedException e) {
            for (WaterRequestThread requestThread : requestThreads) {
                requestThread.interrupt();
            }
            throw e;
        }

        System.out.printf(
                "최종 남은 물의 양: %d%n",
                CentralWaterCenter.getInstance().getRemainingWater());
    }

    private static void printAllWater(List<WaterCity> cities) {
        System.out.printf(
                "중앙 수자원 센터의 현재 물양: %d%n",
                CentralWaterCenter.getInstance().getRemainingWater());
        for (WaterCity city : cities) {
            city.printCurrentWater();
        }
    }

    private static Integer readNonNegativeWater(
            BufferedReader reader,
            String prompt) throws IOException {
        while (true) {
            String value = readLine(reader, prompt);
            if (value == null) {
                return null;
            }

            Integer requestedWater = parseInteger(value);
            if (requestedWater == null) {
                System.out.println("물의 양은 정수로 입력해주세요.");
                continue;
            }
            if (requestedWater < 0) {
                System.out.println(
                        "마이너스값이 입력되었습니다. 다시 한번 확인해주세요.");
                continue;
            }
            return requestedWater;
        }
    }

    private static Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static String readLine(
            BufferedReader reader,
            String prompt) throws IOException {
        System.out.print(prompt);
        return reader.readLine();
    }

    private static List<WaterCity> createCities() {
        return List.of(
                new WaterCity("테라노바"),
                new WaterCity("루미나베이"),
                new WaterCity("플로우브릿지"),
                new WaterCity("루미노엣지"));
    }

    private static boolean isBonusMode(String[] args) {
        return args.length > 0
                && BONUS_MODE.equalsIgnoreCase(args[0]);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. 중앙 수자원 센터와 4개 도시 보유 물양 조회하기");
        System.out.println("2. 도시에 물 할당하기");
        System.out.println("3. 프로그램 종료하기");
    }

    private static void printExitMessage() {
        System.out.println("프로그램을 종료합니다.");
    }
}
