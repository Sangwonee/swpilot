package course03.question04;

import java.util.ArrayList;
import java.util.List;

public class EnergyManager {
    private final List<EnergySource> energySources;

    public EnergyManager() {
        this.energySources = new ArrayList<>();
    }

    public void addEnergySource(EnergySource energySource) {
        energySources.add(energySource);
    }

    public int getTotalEnergy() {
        int totalEnergy = 0;

        for (EnergySource energySource : energySources) {
            totalEnergy += energySource.getEnergyAmount();
        }

        return totalEnergy;
    }
}
