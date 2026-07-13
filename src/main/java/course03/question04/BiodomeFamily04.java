package course03.question04;

public class BiodomeFamily04 {

    public static void main(String[] args) {
        EnergySource solarEnergy = new SolarEnergy(5);
        EnergySource windEnergy = new WindEnergy(12);
        EnergySource geothermalEnergy = new GeothermalEnergy(4);

        EnergyManager energyManager = new EnergyManager();
        energyManager.addEnergySource(solarEnergy);
        energyManager.addEnergySource(windEnergy);
        energyManager.addEnergySource(geothermalEnergy);

        solarEnergy.produceEnergy();
        windEnergy.produceEnergy();
        geothermalEnergy.produceEnergy();

        System.out.println();

        useEnergy(solarEnergy, 30);
        useEnergy(windEnergy, 30);
        useEnergy(geothermalEnergy, 30);

        System.out.println("\n남은 에너지: " + energyManager.getTotalEnergy());
    }

    private static void useEnergy(EnergySource energySource, int amount) {
        try {
            energySource.useEnergy(amount);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
