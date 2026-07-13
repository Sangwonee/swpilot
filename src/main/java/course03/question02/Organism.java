package course03.question02;

public class Organism {

    private String name;
    private String type;
    private String habitat;

    public Organism(String name, String type, String habitat) {
        this.name = name;
        this.type = type;
        this.habitat = habitat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHabitat() {
        return habitat;
    }

    // private 서식지 필드를 변경하고 수행 작업을 출력한다.
    public void setHabitat(String habitat) {
        this.habitat = habitat;
        System.out.println("[LifeNest] " + name + " 서식지가 변경되었습니다.");
    }
}
