package course03.question03;

public class Bird extends Animal{
    private String WingSpan;

    
    public Bird(String name, String species, String habitat, String digestiveSystem, String food, String wingSpan) {
        super(name, species, habitat, digestiveSystem, food);
        WingSpan = wingSpan;
    }

    public void fly() {
        System.out.println(getName() + "은(는) 날개를 펴고 하늘을 납니다.");
    }

    public String getWingSpan() {
        return WingSpan;
    }

    public void setWingSpan(String wingSpan) {
        WingSpan = wingSpan;
    }

    @Override
    public void displayInfo() {
        System.out.println(getName() + ", " + getSpecies() + ", " + getHabitat() + ", " + getDigestiveSystem() + ", " + getFood() + ", " + WingSpan);
    }
    
}
