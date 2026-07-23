package course04.question07;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Plant implements Comparable<Plant> {
    private static final int DEFAULT_OPTIMAL_HUMIDITY = 50;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final String name;
    private final String type;
    private final int requiredWaterAmount;
    private LocalDateTime lastWateredAt;
    private final int wateringIntervalHours;
    private final int optimalHumidity;

    public Plant(String name, String type, int requiredWaterAmount,
            LocalDateTime lastWateredAt, int wateringIntervalHours) {
        this(name, type, requiredWaterAmount, lastWateredAt, wateringIntervalHours, DEFAULT_OPTIMAL_HUMIDITY);
    }

    public Plant(String name, String type, int requiredWaterAmount,
            LocalDateTime lastWateredAt, int wateringIntervalHours, int optimalHumidity) {
        validate(name, type, requiredWaterAmount, lastWateredAt, wateringIntervalHours, optimalHumidity);

        this.name = name.trim();
        this.type = type.trim();
        this.requiredWaterAmount = requiredWaterAmount;
        this.lastWateredAt = lastWateredAt;
        this.wateringIntervalHours = wateringIntervalHours;
        this.optimalHumidity = optimalHumidity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getRequiredWaterAmount() {
        return requiredWaterAmount;
    }

    public LocalDateTime getLastWateredAt() {
        return lastWateredAt;
    }

    public int getWateringIntervalHours() {
        return wateringIntervalHours;
    }

    public int getOptimalHumidity() {
        return optimalHumidity;
    }

    public LocalDateTime getNextWateringTime() {
        return lastWateredAt.plusHours(wateringIntervalHours);
    }

    public void water() {
        lastWateredAt = LocalDateTime.now();
    }

    public String getFormattedLastWateredAt() {
        return lastWateredAt.format(DATE_TIME_FORMATTER);
    }

    // 다음 물 공급 시각이 빠른 식물이 먼저 오고, 시각이 같으면 이름순으로 정한다.
    @Override
    public int compareTo(Plant other) {
        int result = getNextWateringTime().compareTo(other.getNextWateringTime());

        if (result != 0) {
            return result;
        }

        return name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %d, 마지막 물 공급 일자: %s, 물 공급 주기: %d시간",
                name, requiredWaterAmount, getFormattedLastWateredAt(), wateringIntervalHours);
    }

    private static void validate(String name, String type, int requiredWaterAmount,
            LocalDateTime lastWateredAt, int wateringIntervalHours, int optimalHumidity) {
        if (name == null || name.isBlank() || type == null || type.isBlank()) {
            throw new IllegalArgumentException("식물의 이름과 종류를 입력해주세요.");
        }
        if (requiredWaterAmount <= 0) {
            throw new IllegalArgumentException("필요한 물의 양은 0보다 커야 합니다.");
        }
        if (lastWateredAt == null) {
            throw new IllegalArgumentException("마지막 물 공급 일자를 입력해주세요.");
        }
        if (wateringIntervalHours <= 0) {
            throw new IllegalArgumentException("물 공급 주기는 0시간보다 길어야 합니다.");
        }
        if (optimalHumidity < 0 || optimalHumidity > 100) {
            throw new IllegalArgumentException("적정 습도는 0부터 100 사이여야 합니다.");
        }
    }
}
