package course06.question05;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunBiodome05 {
    private static final String BONUS_MODE = "bonus";

    public static void main(String[] args) {
        System.out.println("센서 모니터링을 시작합니다...");

        if (isBonusMode(args)) {
            runWithThreadPool();
            return;
        }

        runWithIndividualThreads();
    }

    // 기본 과제에서는 온도와 산소 센서를 각각 독립된 스레드로 실행한다.
    private static void runWithIndividualThreads() {
        List<Sensor> sensors = createBasicSensors();
        sensors.forEach(Thread::start);

        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> sensors.forEach(Thread::interrupt)));
    }

    // 계속 실행되는 센서가 대기열에 남지 않도록 센서 수와 같은 크기의 풀을 사용한다.
    private static void runWithThreadPool() {
        List<Sensor> sensors = createAllSensors();
        ExecutorService sensorThreadPool =
                Executors.newFixedThreadPool(sensors.size());

        sensors.forEach(sensorThreadPool::execute);

        Runtime.getRuntime().addShutdownHook(new Thread(
                sensorThreadPool::shutdownNow));
    }

    private static List<Sensor> createBasicSensors() {
        return List.of(
                new Sensor("온도", "°C", -5.0, 32.5),
                new Sensor("산소 농도", "%", 18.5, 23.5));
    }

    private static List<Sensor> createAllSensors() {
        return List.of(
                new Sensor("온도", "°C", -5.0, 32.5),
                new Sensor("산소 농도", "%", 18.5, 23.5),
                new Sensor("이산화탄소 농도", "ppm", 300.0, 1_000.0),
                new Sensor("습도", "%", 40.0, 70.0),
                new Sensor("오존 농도", "ppm", 0.0, 0.1));
    }

    private static boolean isBonusMode(String[] args) {
        return args.length > 0
                && BONUS_MODE.equalsIgnoreCase(args[0]);
    }
}
