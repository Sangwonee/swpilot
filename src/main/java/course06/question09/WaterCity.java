package course06.question09;

public class WaterCity {
    private final String name;
    private int water;

    public WaterCity(String name) {
        this.name = name;
        this.water = 0;
    }

    public String getName() {
        return name;
    }

    public synchronized void addWater(int allocatedWater) {
        water += allocatedWater;
    }

    public synchronized void printCurrentWater() {
        System.out.printf("%s의 현재 물양: %d%n", name, water);
    }
}
