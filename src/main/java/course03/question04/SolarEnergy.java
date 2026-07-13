package course03.question04;

public class SolarEnergy extends EnergySource {
    private int sunlightHours;

    public SolarEnergy(int sunlightHours) {
        super("태양광");
        this.sunlightHours = sunlightHours;
    }

    @Override
    public void produceEnergy() {
        int producedAmount = sunlightHours * 10;
        addEnergy(producedAmount);
        System.out.println(getSourceName() + " 에너지를 " + producedAmount + " 생산했습니다.");
    }

    public int getSunlightHours() {
        return sunlightHours;
    }

    public void setSunlightHours(int sunlightHours) {
        this.sunlightHours = sunlightHours;
    }

}
