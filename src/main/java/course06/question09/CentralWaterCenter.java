package course06.question09;

import java.util.concurrent.locks.ReentrantLock;

public class CentralWaterCenter {
    private static final int INITIAL_WATER = 10_000;
    private static final CentralWaterCenter INSTANCE =
            new CentralWaterCenter();

    private final ReentrantLock allocationLock;
    private volatile int remainingWater;

    private CentralWaterCenter() {
        remainingWater = INITIAL_WATER;
        allocationLock = new ReentrantLock(true);
    }

    public static CentralWaterCenter getInstance() {
        return INSTANCE;
    }

    // 기본 모드에서는 한 번에 하나의 요청만 공유 물양을 변경하게 한다.
    public synchronized int allocateWater(
            String cityName,
            int requestedWater) {
        return allocateWaterInternal(cityName, requestedWater);
    }

    // 보너스 모드에서는 synchronized 대신 명시적인 잠금으로 같은 작업을 보호한다.
    public int allocateWaterWithLock(
            String cityName,
            int requestedWater) {
        allocationLock.lock();
        try {
            return allocateWaterInternal(cityName, requestedWater);
        } finally {
            allocationLock.unlock();
        }
    }

    public int getRemainingWater() {
        return remainingWater;
    }

    private int allocateWaterInternal(
            String cityName,
            int requestedWater) {
        if (requestedWater < 0) {
            throw new IllegalArgumentException(
                    "요청할 물의 양은 마이너스일 수 없습니다.");
        }
        if (requestedWater > remainingWater) {
            throw new IllegalStateException(String.format(
                    "%s에 할당할 물이 부족합니다. 요청량: %d, 남은 물양: %d",
                    cityName,
                    requestedWater,
                    remainingWater));
        }

        remainingWater -= requestedWater;
        System.out.printf(
                "%s에 %d만큼의 물을 할당하였습니다. 남은 물의 양: %d%n",
                cityName,
                requestedWater,
                remainingWater);
        return remainingWater;
    }
}
