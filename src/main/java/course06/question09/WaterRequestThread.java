package course06.question09;

public class WaterRequestThread extends Thread {
    private final WaterCity city;
    private final int requestedWater;
    private final boolean useReentrantLock;

    public WaterRequestThread(
            WaterCity city,
            int requestedWater,
            boolean useReentrantLock) {
        super(city.getName() + "-water-request");
        this.city = city;
        this.requestedWater = requestedWater;
        this.useReentrantLock = useReentrantLock;
    }

    // 각 도시 스레드는 같은 싱글톤 센터에 접근해 자신의 물을 요청한다.
    @Override
    public void run() {
        CentralWaterCenter center =
                CentralWaterCenter.getInstance();

        try {
            if (useReentrantLock) {
                center.allocateWaterWithLock(
                        city.getName(), requestedWater);
            } else {
                center.allocateWater(
                        city.getName(), requestedWater);
            }
            city.addWater(requestedWater);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
