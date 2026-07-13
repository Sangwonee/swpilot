package course03.question03;

public class Fish extends Animal{
    private String finsCount;

    public Fish(String name, String species, String habitat, String digestiveSystem, String food, String finsCount) {
        super(name, species, habitat, digestiveSystem, food);
        this.finsCount = finsCount;
    }

    public void swim() {
        System.out.println(getName() + "은(는) 물 속을 헤엄칩니다.");
    }

    public String getFinsCount() {
        return finsCount;
    }

    public void setFinsCount(String finsCount) {
        this.finsCount = finsCount;
    }

    @Override
    public void displayInfo() {
        System.out.println(getName() + ", " + getSpecies() + ", " + getHabitat() + ", " + getDigestiveSystem() + ", " + getFood() + ", " + finsCount);
    }
    
}
