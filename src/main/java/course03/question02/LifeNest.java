package course03.question02;

import java.util.ArrayList;
import java.util.List;

public class LifeNest {

    private final List<Organism> organismList;

    public LifeNest() {
        organismList = new ArrayList<>();
    }

    // 저장소에 동식물을 추가하고 추가 결과를 출력한다.
    public void addOrganism(Organism organism) {
        organismList.add(organism);
        System.out.println("[LifeNest] " + organism.getName()
                + getSubjectParticle(organism.getName()) + " 추가되었습니다.");
    }

    // 저장소에서 지정한 동식물을 삭제하고 성공한 경우 결과를 출력한다.
    public void removeOrganism(Organism organism) {
        if (organismList.remove(organism)) {
            System.out.println("[LifeNest] " + organism.getName()
                    + getSubjectParticle(organism.getName()) + " 삭제되었습니다.");
        }
    }

    // getter를 사용해 저장소의 모든 동식물 정보를 저장 순서대로 출력한다.
    public void displayAllOrganisms() {
        for (int i = 0; i < organismList.size(); i++) {
            Organism organism = organismList.get(i);
            System.out.println(organism.getName() + ", "
                    + organism.getType() + ", "
                    + organism.getHabitat());
        }
    }

    // 이름의 마지막 한글 글자에 받침이 있으면 '이', 없으면 '가'를 반환한다.
    private String getSubjectParticle(String name) {
        char lastCharacter = name.charAt(name.length() - 1);
        if (lastCharacter >= '가' && lastCharacter <= '힣'
                && (lastCharacter - '가') % 28 == 0) {
            return "가";
        }
        return "이";
    }
}
