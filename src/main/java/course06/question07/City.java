package course06.question07;

public class City {
    private final String name;
    private int energy;

    public City(String name) {
        this.name = name;
        this.energy = 0;
    }

    // 중앙 센터의 유일한 인스턴스에 요청하고 성공한 양만 도시 에너지에 더한다.
    public synchronized int requestEnergy(int requestedEnergy) {
        EnergyManageCenter energyManageCenter = EnergyManageCenter.getInstance();
        int remainingEnergy = energyManageCenter.allocateEnergy(requestedEnergy);

        energy += requestedEnergy;
        return remainingEnergy;
    }

    public String getName() {
        return name;
    }

    public synchronized int getEnergy() {
        return energy;
    }

    public synchronized void printCurrentEnergy() {
        System.out.printf("%s: %d%n", name, energy);
    }

    public void printReplenishmentNotice(int replenishedEnergy) {
        System.out.printf("%s: 중앙 센터에 에너지가 %d 보충되었습니다.%n", name, replenishedEnergy);
    }
}
