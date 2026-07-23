package course04.question07;

import java.util.List;

public class PlantManagementSystem {
    private static final int DEFAULT_HUMIDITY = 50;

    private final PlantPriorityQueue<Plant> plants;
    private int todayHumidity;

    public PlantManagementSystem() {
        this(DEFAULT_HUMIDITY);
    }

    public PlantManagementSystem(int todayHumidity) {
        validateHumidity(todayHumidity);
        this.plants = new PlantPriorityQueue<>();
        this.todayHumidity = todayHumidity;

        System.out.println("식물 관리 시스템이 생성되었습니다.");
        System.out.printf("오늘의 습도: %d%%%n", todayHumidity);
    }

    public void addPlant(Plant plant) {
        if (plant == null) {
            throw new IllegalArgumentException("등록할 식물 정보가 없습니다.");
        }
        if (plants.contains(plant)) {
            throw new IllegalArgumentException(
                    "'" + plant.getName() + "'은(는) 이미 관리 대상 목록에 있습니다.");
        }

        plants.offer(plant);
        System.out.printf("'%s'이(가) 관리 대상 목록에 추가되었습니다.%n", plant.getName());
    }

    public Plant showNextPlant() {
        Plant plant = plants.peek();
        if (plant == null) {
            throw new IllegalStateException("관리할 식물이 없습니다.");
        }

        System.out.printf("우선 관리 대상: %s, 필요한 물의 양: %d%n",
                plant.getName(), plant.getRequiredWaterAmount());
        return plant;
    }

    public Plant waterNextPlant() {
        Plant plant = plants.poll();
        if (plant == null) {
            throw new IllegalStateException("관리할 식물이 없습니다.");
        }

        supplyWater(plant);
        return plant;
    }

    public void waterPlant(Plant plant) {
        if (plant == null || !plants.remove(plant)) {
            String plantName = plant == null ? "해당 식물" : plant.getName();
            throw new IllegalStateException(
                    "'" + plantName + "'은(는) 관리 대상 목록에 없어 물을 공급할 수 없습니다.");
        }

        supplyWater(plant);
    }

    public void printRemainingPlants() {
        System.out.println("(남아있는 식물)");

        List<Plant> remainingPlants = plants.getElementsInPriorityOrder();
        if (remainingPlants.isEmpty()) {
            System.out.println("남아있는 관리 대상 식물이 없습니다.");
            return;
        }

        for (Plant plant : remainingPlants) {
            System.out.println(plant);
        }
    }

    public List<Plant> getPlantsInPriorityOrder() {
        return plants.getElementsInPriorityOrder();
    }

    public int getTodayHumidity() {
        return todayHumidity;
    }

    public void setTodayHumidity(int todayHumidity) {
        validateHumidity(todayHumidity);
        this.todayHumidity = todayHumidity;
        System.out.printf("오늘의 습도가 %d%%로 변경되었습니다.%n", todayHumidity);
    }

    public int size() {
        return plants.size();
    }

    public void clear() {
        plants.clear();
        System.out.println("관리 대상 식물 목록을 모두 비웠습니다.");
    }

    // 오늘 습도가 적정 습도보다 낮으면 기본 필요량의 1.5배를 공급한다.
    private void supplyWater(Plant plant) {
        double suppliedWaterAmount = plant.getRequiredWaterAmount();

        if (todayHumidity < plant.getOptimalHumidity()) {
            suppliedWaterAmount *= 1.5;
            System.out.printf(
                    "오늘 습도가 %s의 적정 습도보다 낮아 물을 1.5배 공급합니다.%n",
                    plant.getName());
        }

        plant.water();
        System.out.printf(
                "%s에 %s만큼의 물을 공급했습니다. 마지막 물 공급 일자 업데이트: %s%n",
                plant.getName(),
                formatWaterAmount(suppliedWaterAmount),
                plant.getFormattedLastWateredAt());
    }

    private static String formatWaterAmount(double waterAmount) {
        if (waterAmount == Math.floor(waterAmount)) {
            return String.format("%.0f", waterAmount);
        }

        return String.format("%.1f", waterAmount);
    }

    private static void validateHumidity(int humidity) {
        if (humidity < 0 || humidity > 100) {
            throw new IllegalArgumentException("습도는 0부터 100 사이여야 합니다.");
        }
    }
}
