package course03.question04;

public class WindEnergy extends EnergySource {
    private int windSpeed;

    public WindEnergy(int windSpeed) {
        super("풍력");
        this.windSpeed = windSpeed;
    }

    @Override
    public void produceEnergy() {
        int producedAmount = windSpeed * 5;
        addEnergy(producedAmount);
        System.out.println(getSourceName() + " 에너지를 " + producedAmount + " 생산했습니다.");
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }
}
