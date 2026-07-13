package course03.question05;

public class WaterMirror extends AncientArtifact
        implements EnergyGenerator, WeatherController, Chargeable {
    private int chargeLevel;

    public WaterMirror(String name) {
        super(name);
        this.chargeLevel = 0;
        System.out.println(name + " 유물이 생성되었습니다.");
    }

    @Override
    public void describe() {
        System.out.println("\"물의 거울은 수증기를 모아 에너지를 생성하고 비와 눈을 내리게 합니다.\"");
    }

    @Override
    public void generateEnergy() {
        System.out.println("\"물의 거울을 이용해 수증기로 에너지를 생성했습니다!\"");
    }

    @Override
    public void controlWeather() {
        System.out.println("\"물의 거울로 수증기를 모아 비와 눈을 내리게 했습니다!\"");
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
