package course03.question01;

public class BiodomeFamily01 {

    public static void main(String[] args) {
        Organism penguin = new Organism("펭귄", "동물", "추위에 강함", "남극");
        Organism koala = new Organism("코알라", "동물", "나무 위에서 생활함", "호주");
        Organism cactus = new Organism("선인장", "식물", "물을 저장함", "사막");
        Organism peppermint = new Organism("페퍼민트", "식물", "향이 강함", "정원");

        LifeNest lifeNest = new LifeNest();
        lifeNest.addOrganism(penguin);
        lifeNest.addOrganism(koala);
        lifeNest.addOrganism(cactus);
        lifeNest.addOrganism(peppermint);

        System.out.println("전체 동식물 목록 출력:");
        lifeNest.displayAllOrganisms();

        lifeNest.removeOrganism(koala);
        lifeNest.removeOrganism(cactus);
        penguin.setHabitat("해변");

        System.out.println();
        System.out.println("전체 동식물 목록 출력:");
        lifeNest.displayAllOrganisms();

        System.out.println();
        lifeNest.searchOrganismByName("펭귄");
    }

}
