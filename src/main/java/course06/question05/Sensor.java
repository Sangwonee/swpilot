package course06.question05;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Sensor extends Thread {
    private static final long NORMAL_MONITORING_INTERVAL = 5_000L;
    private static final long WARNING_MONITORING_INTERVAL = 1_000L;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 M월 d일 H시 m분 s초");

    private final String measurementType;
    private final String unit;
    private final double minThreshold;
    private final double maxThreshold;
    private final Random random;

    private double currentValue;

    public Sensor(String measurementType, String unit, double minThreshold, double maxThreshold) {
        super(measurementType + "-sensor");
        this.measurementType = measurementType;
        this.unit = unit;
        this.minThreshold = minThreshold;
        this.maxThreshold = maxThreshold;
        this.random = new Random();
    }

    // 센서마다 독립적으로 값을 측정하고 현재 상태에 맞는 간격만큼 대기한다.
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            generateData();
            boolean outsideThreshold = isOutsideThreshold();
            printCurrentData();

            try {
                Thread.sleep(outsideThreshold ? WARNING_MONITORING_INTERVAL : NORMAL_MONITORING_INTERVAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // 하한 미달과 상한 초과가 모두 발생하도록 정상 범위보다 넓은 값을 생성한다.
    private double generateData() {
        double thresholdRange = maxThreshold - minThreshold;
        double generatedMin = minThreshold - thresholdRange * 0.25;
        currentValue = generatedMin + thresholdRange * random.nextDouble() * 1.5;
        return currentValue;
    }

    private boolean isOutsideThreshold() {
        return currentValue < minThreshold || currentValue > maxThreshold;
    }

    private void printCurrentData() {
        String measuredAt = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String warningMessage = createWarningMessage();

        System.out.printf(
                "%s %s: %.1f%s%s%n",
                measuredAt,
                measurementType,
                currentValue,
                unit,
                warningMessage);
    }

    private String createWarningMessage() {
        if (currentValue < minThreshold) {
            return String.format(
                    " [경고: %s 하한 미달]", measurementType);
        }
        if (currentValue > maxThreshold) {
            return String.format(
                    " [경고: %s 상한 초과]", measurementType);
        }
        return "";
    }
}
