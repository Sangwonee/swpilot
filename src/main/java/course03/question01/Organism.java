package course03.question01;

public class Organism {

    private final String name;
    private final String species;
    private final String characteristic;
    private String habitat;

    public Organism(String name, String species, String habitat) {
        this(name, species, "", habitat);
    }

    public Organism(String name, String species, String characteristic, String habitat) {
        this.name = name;
        this.species = species;
        this.characteristic = characteristic;
        this.habitat = habitat;
    }

    // 동식물의 이름, 종, 주요 서식지를 출력한다.
    public void displayInfo() {
        System.out.println(name + ", " + species + ", " + habitat);
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
