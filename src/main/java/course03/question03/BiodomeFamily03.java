package course03.question03;

public class BiodomeFamily03 {

    public static void main(String[] args) {

        LifeNest lifeNest = new LifeNest();

        Organism penguin = new Animal("펭귄", "동물", "남극", "육식", "물고기");
        Organism koala = new Animal("코알라", "동물", "호주", "초식", "유칼립투스");
        Organism cactus = new Plant("선인장", "식물", "사막", "11월", "열매 있음");
        Organism peppermint = new Plant("페퍼민트", "식물", "정원", "7월", "열매 없음");


        lifeNest.addOrganism(penguin);
        lifeNest.addOrganism(koala);
        lifeNest.addOrganism(cactus);
        lifeNest.addOrganism(peppermint);
        
        lifeNest.displayAll();
        
        lifeNest.removeOrganism(koala);
        lifeNest.removeOrganism(cactus);

        System.out.println("\n보너스 과제 실행:");

        Mammal polarBear = new Mammal("북극곰", "포유류", "북극", "육식", "물고기와 물개", "정온동물");
        Bird eagle = new Bird("독수리", "조류", "산악 지대", "육식", "작은 동물", "2미터");
        Fish salmon = new Fish("연어", "어류", "강과 바다", "육식", "작은 물고기", "8개");

        lifeNest.addOrganism(polarBear);
        lifeNest.addOrganism(eagle);
        lifeNest.addOrganism(salmon);

        polarBear.giveBirth();
        eagle.fly();
        salmon.swim();

        lifeNest.displayAll();
    }
}
