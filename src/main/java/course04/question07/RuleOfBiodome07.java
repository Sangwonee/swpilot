package course04.question07;

import java.time.LocalDateTime;

public class RuleOfBiodome07 {

    public static void main(String[] args) {
        Plant lily = new Plant(
                "백합", "화초", 100,
                LocalDateTime.of(2130, 3, 12, 12, 0), 15, 60);
        Plant raspberry = new Plant(
                "나무딸기", "과일나무", 200,
                LocalDateTime.of(2130, 3, 12, 14, 20), 20, 45);
        Plant cactus = new Plant(
                "선인장", "다육식물", 5,
                LocalDateTime.of(2130, 3, 12, 9, 0), 30, 30);
        Plant lilac = new Plant(
                "라일락", "화초", 20,
                LocalDateTime.of(2130, 3, 12, 11, 0), 25, 50);
        Plant bamboo = new Plant(
                "대나무", "목본식물", 15,
                LocalDateTime.of(2130, 3, 11, 19, 0), 50, 40);

        System.out.println("(식물 객체 생성)");
        System.out.println(lily);
        System.out.println(raspberry);
        System.out.println(cactus);
        System.out.println(lilac);
        System.out.println(bamboo);

        System.out.println();
        System.out.println("(식물 관리 시스템 생성 및 등록)");
        PlantManagementSystem managementSystem = new PlantManagementSystem(40);
        managementSystem.addPlant(lily);
        managementSystem.addPlant(raspberry);
        managementSystem.addPlant(cactus);
        managementSystem.addPlant(lilac);
        managementSystem.addPlant(bamboo);

        Plant firstWateredPlant = null;
        for (int i = 1; i <= 3; i++) {
            System.out.println();
            System.out.printf("(우선순위에 따른 식물 출력 및 관리%d)%n", i);
            managementSystem.showNextPlant();
            Plant wateredPlant = managementSystem.waterNextPlant();

            if (firstWateredPlant == null) {
                firstWateredPlant = wateredPlant;
            }
        }

        System.out.println();
        managementSystem.printRemainingPlants();

        demonstrateExceptions(managementSystem, firstWateredPlant);
    }

    // 평가 항목인 잘못된 물의 양과 이미 관리한 식물의 재급수를 확인한다.
    private static void demonstrateExceptions(
            PlantManagementSystem managementSystem,
            Plant alreadyWateredPlant) {
        System.out.println();
        System.out.println("[예외 상황 확인]");

        try {
            new Plant(
                    "잘못된 식물", "화초", -10,
                    LocalDateTime.of(2130, 3, 12, 10, 0), 10, 50);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            managementSystem.waterPlant(alreadyWateredPlant);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
