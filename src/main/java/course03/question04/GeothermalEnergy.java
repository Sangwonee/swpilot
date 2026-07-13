package course03.question04;

public class GeothermalEnergy extends EnergySource {
    private int groundTemperature;


    public GeothermalEnergy(int groundTemperature) {
        super("지열");
        this.groundTemperature = groundTemperature;
    }

    @Override
    public void produceEnergy() {
        int producedAmount = groundTemperature * 5 + 20;
        addEnergy(producedAmount);
        System.out.println(getSourceName() + " 에너지를 " + producedAmount + " 생성했습니다.");
    }

    public int getGroundTemperature() {
        return groundTemperature;
    }

    public void setGroundTemperature(int groundTemperature) {
        this.groundTemperature = groundTemperature;
    }

}
