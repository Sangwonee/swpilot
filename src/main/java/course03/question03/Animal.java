package course03.question03;

public class Animal extends Organism{
    private String digestiveSystem;
    private String food;

    public Animal(String name, String species, String habitat, String digestiveSystem, String food) {
        super(name, species, habitat);
        this.digestiveSystem = digestiveSystem;
        this.food = food;
    }

    public String getDigestiveSystem() {
        return digestiveSystem;
    }

    public void setDigestiveSystem(String digestiveSystem) {
        this.digestiveSystem = digestiveSystem;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public void displayInfo() {
        System.out.println(getName() + ", " + getSpecies() + ", " + getHabitat() + ", " + digestiveSystem + ", " + food);
    }
}
