package course03.question03;

import java.util.ArrayList;

public class LifeNest {

    private ArrayList<Organism> organismList;
    
    public LifeNest() {
        this.organismList = new ArrayList<>();
    }

    public void addOrganism(Organism organism) {
        organismList.add(organism);
        System.out.println("[LifeNest] " + organism.getName() + "이(가) 추가되었습니다.");
    }

    public void removeOrganism(Organism organism) {
        organismList.remove(organism);
        System.out.println("[LifeNest] " + organism.getName() + "이(가) 삭제되었습니다.");
    }

    public void displayAll() {
        System.out.println("\n전체 동식물 목록 출력:");
        for (Organism organism : organismList) {
            organism.displayInfo();
        }
    }
}