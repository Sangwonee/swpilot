package course03.question03;

public class Plant extends Organism{
    private String floweringTime;
    private String hasFruit;

    public Plant(String name, String species, String habitat, String floweringTime, String hasFruit) {
        super(name, species, habitat);
        this.floweringTime = floweringTime;
        this.hasFruit = hasFruit;
    }

    public String getFloweringTime() {
        return floweringTime;
    }

    public void setFloweringTime(String floweringTime) {
        this.floweringTime = floweringTime;
    }

    public String getHasFruit() {
        return hasFruit;
    }

    public void setHasFruit(String hasFruit) {
        this.hasFruit = hasFruit;
    }
    
    @Override
    public void displayInfo() {
        System.out.println(getName() + ", " + getSpecies() + ", " + getHabitat() + ", " + floweringTime + ", " + hasFruit);
    }
}
