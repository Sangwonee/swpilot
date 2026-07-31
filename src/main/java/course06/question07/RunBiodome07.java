package course06.question07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RunBiodome07 {
    private static final int VIEW_ENERGY_OPTION = 1;
    private static final int ALLOCATE_ENERGY_OPTION = 2;
    private static final int REPLENISH_ENERGY_OPTION = 3;
    private static final int EXIT_OPTION = 4;

    public static void main(String[] args) {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        List<City> cities = createCities();

        System.out.println("에너지 관리 시스템에 오신걸 환영합니다.");

        try {
            runProgram(reader, cities);
        } catch (IOException e) {
            System.out.println("사용자 입력을 읽는 중 오류가 발생했습니다.");
        }
    }

    // 사용자가 종료를 선택하거나 표준 입력이 끝날 때까지 메뉴를 반복한다.
    private static void runProgram(
            BufferedReader reader,
            List<City> cities) throws IOException {
        while (true) {
            printMenu();
            String selectedValue = readLine(reader, "메뉴 선택: ");

            if (selectedValue == null) {
                printExitMessage();
                return;
            }

            Integer selectedOption = parseInteger(selectedValue);
            if (selectedOption == null) {
                System.out.println("1번부터 4번 중 하나를 선택해주세요.");
                continue;
            }

            switch (selectedOption) {
                case VIEW_ENERGY_OPTION -> printAllEnergy(cities);
                case ALLOCATE_ENERGY_OPTION ->
                        allocateEnergy(reader, cities);
                case REPLENISH_ENERGY_OPTION ->
                        replenishEnergy(reader, cities);
                case EXIT_OPTION -> {
                    printExitMessage();
                    return;
                }
                default -> System.out.println(
                        "1번부터 4번 중 하나를 선택해주세요.");
            }
        }
    }

    private static void allocateEnergy(
            BufferedReader reader,
            List<City> cities) throws IOException {
        String cityName = readLine(reader, "도시 이름 입력: ");
        if (cityName == null) {
            return;
        }

        City city = findCity(cities, cityName);
        if (city == null) {
            System.out.println("등록되지 않은 도시입니다.");
            return;
        }

        Integer requestedEnergy =
                readEnergy(reader, "할당할 에너지양 입력: ");
        if (requestedEnergy == null) {
            return;
        }

        try {
            int remainingEnergy = city.requestEnergy(requestedEnergy);
            System.out.printf("중앙 에너지 센터: %d%n", remainingEnergy);
            city.printCurrentEnergy();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void replenishEnergy(
            BufferedReader reader,
            List<City> cities) throws IOException {
        Integer replenishedEnergy =
                readEnergy(reader, "보충할 에너지양 입력: ");
        if (replenishedEnergy == null) {
            return;
        }

        try {
            EnergyManageCenter center =
                    EnergyManageCenter.getInstance();
            int totalEnergy =
                    center.replenishEnergy(replenishedEnergy);

            System.out.printf("중앙 에너지 센터: %d%n", totalEnergy);
            notifyCitiesWithLowEnergy(cities, replenishedEnergy);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // 두 번째로 낮은 에너지양까지 알림 대상을 포함해 동점 도시를 제외하지 않는다.
    private static void notifyCitiesWithLowEnergy(
            List<City> cities,
            int replenishedEnergy) {
        List<Integer> cityEnergies = new ArrayList<>();
        for (City city : cities) {
            cityEnergies.add(city.getEnergy());
        }
        cityEnergies.sort(Comparator.naturalOrder());

        int notificationThreshold =
                cityEnergies.get(Math.min(1, cityEnergies.size() - 1));

        for (City city : cities) {
            if (city.getEnergy() <= notificationThreshold) {
                city.printReplenishmentNotice(replenishedEnergy);
            }
        }
    }

    private static void printAllEnergy(List<City> cities) {
        EnergyManageCenter center =
                EnergyManageCenter.getInstance();

        System.out.println();
        System.out.printf(
                "중앙 에너지 센터: %d%n",
                center.getTotalEnergy());
        for (City city : cities) {
            city.printCurrentEnergy();
        }
    }

    private static City findCity(
            List<City> cities,
            String cityName) {
        String normalizedName = cityName.trim();
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(normalizedName)) {
                return city;
            }
        }
        return null;
    }

    private static Integer readEnergy(
            BufferedReader reader,
            String prompt) throws IOException {
        String value = readLine(reader, prompt);
        if (value == null) {
            return null;
        }

        Integer energy = parseInteger(value);
        if (energy == null) {
            System.out.println("에너지양은 정수로 입력해주세요.");
        }
        return energy;
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

    private static List<City> createCities() {
        return List.of(
                new City("TerraNova"),
                new City("LuminaBay"),
                new City("FlowBridges"));
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. 중앙 에너지 센터와 3개 도시 에너지양 조회하기");
        System.out.println("2. 도시에 에너지 할당하기");
        System.out.println("3. 중앙 에너지 센터에 에너지 보충하기");
        System.out.println("4. 프로그램 종료하기");
    }

    private static void printExitMessage() {
        System.out.println("프로그램을 종료합니다.");
    }
}
