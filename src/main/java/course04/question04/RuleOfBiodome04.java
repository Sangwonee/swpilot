package course04.question04;

import java.util.List;

import course04.question04.features.AnimalFeature;
import course04.question04.features.BiologicalFeature;
import course04.question04.features.MicrobeFeature;
import course04.question04.features.PlantFeature;

public class RuleOfBiodome04 {

    public static void main(String[] args) {
        // 각 생물은 자신에게 맞는 특징 타입을 사용하므로 잘못된 특징 입력을 컴파일 단계에서 막는다.
        BiologicalEntity<AnimalFeature> cat =
                new BiologicalEntity<>("고양이", "동물",
                        new AnimalFeature("민첩하고 호기심이 많음", "태생", "대형 맹금류", "쥐", 20));

        BiologicalEntity<AnimalFeature> zebra =
                new BiologicalEntity<>("얼룩말", "동물",
                        new AnimalFeature("무리를 지어 빠르게 달림", "태생", "사자", "풀", 10));

        BiologicalEntity<PlantFeature> rosemary =
                new BiologicalEntity<>("로즈마리", "식물", new PlantFeature("보라색", false, "7월"));
                
        BiologicalEntity<PlantFeature> cherryBlossom =
                new BiologicalEntity<>("벚꽃", "식물", new PlantFeature("분홍색", true, "3월"));

        BiologicalEntity<MicrobeFeature> eColi =
                new BiologicalEntity<>("이콜라이","미생물",
                        new MicrobeFeature("약 산성", true, "호흡 및 발효 대사"));
                        
        BiologicalEntity<MicrobeFeature> bacillus =
                new BiologicalEntity<>("바실러스","미생물",
                        new MicrobeFeature("약 산성", false, "호흡 대사"));

        List<BiologicalEntity<? extends BiologicalFeature>> entities =
                List.of(cat, zebra, rosemary, cherryBlossom, eColi, bacillus);

        for (BiologicalEntity<? extends BiologicalFeature> entity : entities) {
            System.out.println(entity);
        }

        System.out.println();
        BiologicalSystem<BiologicalFeature> biologicalSystem = new BiologicalSystem<>();

        System.out.println();
        // 공통 상위 특징 타입을 사용하는 한 시스템에 서로 다른 제네릭 엔티티를 순서대로 추가한다.
        for (BiologicalEntity<? extends BiologicalFeature> entity : entities) {
            biologicalSystem.add(entity);
        }

        System.out.println();
        biologicalSystem.delete();

        System.out.println();
        biologicalSystem.show();

        System.out.println();
        printEmptyState(biologicalSystem);

        System.out.println("\n[보너스: 이름순 정렬]");
        biologicalSystem.sortByName();

        System.out.println();
        biologicalSystem.clear();
        printEmptyState(biologicalSystem);
    }

    private static void printEmptyState(BiologicalSystem<?> biologicalSystem) {
        if (biologicalSystem.isEmpty()) {
            System.out.println("생물 정보 리스트는 비어있습니다.");
            return;
        }
        System.out.println("생물 정보 리스트가 비어있지 않습니다.");
    }
}
