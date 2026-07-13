package course03.question04;

public abstract class EnergySource {
    private final String sourceName;
    private int energyAmount;

    public EnergySource(String sourceName) {
        this.sourceName = sourceName;
        this.energyAmount = 0;
    }

    public String getSourceName() {
        return sourceName;
    }

    public int getEnergyAmount() {
        return energyAmount;
    }

    protected void addEnergy(int amount) {
        energyAmount += amount;
    }

    public void useEnergy(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("사용할 에너지는 0보다 커야 합니다.");
        }
        if (energyAmount < amount) {
            throw new IllegalStateException(
                    "에너지가 부족해 " + sourceName + " 에너지 " + amount + "을 사용할 수 없습니다.");
        }

        energyAmount -= amount;
        System.out.println(sourceName + " 에너지를 " + amount + " 사용했습니다.");
    }

    public abstract void produceEnergy();
}
