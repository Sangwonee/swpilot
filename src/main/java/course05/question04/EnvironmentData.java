package course05.question04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnvironmentData {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final LocalDateTime measuredAt;
    private final double temperature;
    private final double humidity;
    private final double oxygenLevel;
    private final String location;

    public EnvironmentData(
            double temperature,
            double humidity,
            double oxygenLevel,
            String location) {
        this(LocalDateTime.now(), temperature, humidity, oxygenLevel, location);
    }

    public EnvironmentData(
            LocalDateTime measuredAt,
            double temperature,
            double humidity,
            double oxygenLevel,
            String location) {
        this.measuredAt = measuredAt;
        this.temperature = temperature;
        this.humidity = humidity;
        this.oxygenLevel = oxygenLevel;
        this.location = location;
    }

    public LocalDateTime getMeasuredAt() {
        return measuredAt;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getOxygenLevel() {
        return oxygenLevel;
    }

    public String getLocation() {
        return location;
    }

    public String toFileLine() {
        return String.join(",",
                measuredAt.format(DATE_TIME_FORMATTER),
                Double.toString(temperature),
                Double.toString(humidity),
                Double.toString(oxygenLevel),
                location);
    }

    public String toOxygenInformation() {
        return String.format("%s - %s - %s",
                measuredAt.format(DATE_TIME_FORMATTER),
                oxygenLevel,
                location);
    }

    public static EnvironmentData fromFileLine(String line) {
        String[] values = line.split(",", 5);
        if (values.length != 5) {
            throw new IllegalArgumentException("환경 데이터 형식이 올바르지 않습니다.");
        }

        return new EnvironmentData(
                LocalDateTime.parse(values[0], DATE_TIME_FORMATTER),
                Double.parseDouble(values[1]),
                Double.parseDouble(values[2]),
                Double.parseDouble(values[3]),
                values[4]);
    }
}
