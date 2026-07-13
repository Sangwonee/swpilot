package course03.question05;

public class SolarStone extends AncientArtifact implements EnergyGenerator, Chargeable {
    private int chargeLevel;

    public SolarStone(String name) {
        super(name);
        this.chargeLevel = 0;
        System.out.println(name + " 유물이 생성되었습니다.");
    }

    @Override
    public void describe() {
        System.out.println("\"태양의 돌로 에너지 생성 중! 빛을 받은 시간에 따라 에너지의 양이 달라집니다.\"");
    }

    @Override
    public void generateEnergy() {
        System.out.println("\"태양의 돌이 빛을 받아 에너지를 생성했습니다!\"");
    }

    @Override
    public int readChargeLevel() {
        return chargeLevel;
    }

    @Override
    public void updateChargeLevel(int chargeLevel) {
        if (chargeLevel < 0) {
            throw new IllegalArgumentException("충전 레벨은 0보다 작을 수 없습니다.");
        }
        this.chargeLevel = chargeLevel;
    }
}
