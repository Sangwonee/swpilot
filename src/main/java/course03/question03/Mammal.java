package course03.question03;

public class Mammal extends Animal{
    private String warmBlooded;
    
    public Mammal(String name, String species, String habitat, String digestiveSystem, String food, String warmBlooded) {
        super(name, species, habitat, digestiveSystem, food);
        this.warmBlooded = warmBlooded;
    }
    
    public void giveBirth() {
        System.out.println(getName() + "은(는) 새끼를 낳습니다.");
    }

    public String getWarmBlooded() {
        return warmBlooded;
    }

    public void setWarmBlooded(String warmBlooded) {
        this.warmBlooded = warmBlooded;
    }

    @Override
    public void displayInfo() {
        System.out.println(getName() + ", " + getSpecies() + ", " + getHabitat() + ", " + getDigestiveSystem() + ", " + getFood() + ", " + warmBlooded);
    }
}
