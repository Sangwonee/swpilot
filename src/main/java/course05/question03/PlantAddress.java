package course05.question03;

import java.util.Objects;

public class PlantAddress {
    private final String plantName;
    private final String address;

    public PlantAddress(String plantName, String address) {
        this.plantName = plantName;
        this.address = address;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getAddress() {
        return address;
    }

    public String toOutputLine() {
        return plantName + " - " + address;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlantAddress other)) {
            return false;
        }

        return plantName.equals(other.plantName)
                && address.equals(other.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plantName, address);
    }
}
