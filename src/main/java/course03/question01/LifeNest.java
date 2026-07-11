package course03.question01;

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

    // 저장소에 있는 모든 동식물의 정보를 저장 순서대로 출력한다.
    public void displayAllOrganisms() {
        for (int i = 0; i < organismList.size(); i++) {
            organismList.get(i).displayInfo();
        }
    }

    // 이름이 일치하는 동식물을 찾아 종과 주요 서식지를 출력한다.
    public void searchOrganismByName(String name) {
        for (int i = 0; i < organismList.size(); i++) {
            Organism organism = organismList.get(i);
            if (organism.getName().equals(name)) {
                System.out.println(organism.getName() + getTopicParticle(organism.getName())
                        + " " + organism.getSpecies() + "이며 "
                        + organism.getHabitat() + "에 서식합니다.");
                return;
            }
        }

        System.out.println("검색 결과가 없습니다.");
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

    // 이름의 마지막 한글 글자에 받침이 있으면 '은', 없으면 '는'을 반환한다.
    private String getTopicParticle(String name) {
        char lastCharacter = name.charAt(name.length() - 1);
        if (lastCharacter >= '가' && lastCharacter <= '힣'
                && (lastCharacter - '가') % 28 == 0) {
            return "는";
        }
        return "은";
    }
}
