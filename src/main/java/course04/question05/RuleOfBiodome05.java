package course04.question05;

public class RuleOfBiodome05 {

    public static void main(String[] args) {
        PlantHashMap<String, String> plants = new PlantHashMap<>();

        plants.put("장미", "장미는 관상용으로 많이 재배되는 화초 중 하나이다.");
        plants.put("해바라기", "해바라기는 태양을 따라 움직이는 것으로 알려져 있다.");
        plants.put("민들레", "민들레는 약용으로도 사용되는 풀이다.");
        plants.put("라벤더", "라벤더는 향기가 좋아 향료와 허브로 사용된다.");
        plants.put("선인장", "선인장은 건조한 환경에 적응해 줄기에 물을 저장한다.");
        plants.put("대나무", "대나무는 성장 속도가 매우 빠른 식물이다.");
        plants.put("소나무", "소나무는 사계절 잎이 푸른 상록수이다.");
        plants.put("단풍나무", "단풍나무는 가을에 잎이 붉게 물든다.");
        plants.put("연꽃", "연꽃은 물 위에서 꽃을 피우는 수생 식물이다.");
        plants.put("바오밥", "바오밥은 굵은 줄기에 많은 물을 저장할 수 있다.");

        System.out.println("\n[식물 특징 검색]");
        plants.get("장미");
        plants.get("해바라기");

        System.out.println("\n[식물 삭제]");
        plants.remove("민들레");
        plants.get("민들레");

        System.out.println("\n[식물 이름으로 인덱스 출력]");
        System.out.printf("'장미' 인덱스: %d%n", plants.getIndex("장미"));
        System.out.printf("'해바라기' 인덱스: %d%n", plants.getIndex("해바라기"));

        demonstrateChaining();
    }

    private static void demonstrateChaining() {
        System.out.println("\n[보너스: 체이닝 확인]");

        String str1 = "0-42L";
        String str2 = "0-43-";
        PlantHashMap<String, String> collisionMap = new PlantHashMap<>();

        // 두 문자열은 hashCode와 배열 인덱스가 같지만 서로 다른 키이다.
        System.out.printf(
                "'%s'와 '%s'의 hashCode가 같은가: %s%n",
                str1, str2, str1.hashCode() == str2.hashCode());
        System.out.printf("두 키의 인덱스: %d%n", collisionMap.getIndex(str1));

        collisionMap.put(str1, "첫 번째 충돌 데이터");
        collisionMap.put(str2, "두 번째 충돌 데이터");

        collisionMap.get(str1);
        collisionMap.get(str2);

        // 체인의 첫 항목을 삭제해도 뒤에 연결된 항목은 계속 조회되어야 한다.
        collisionMap.remove(str1);
        collisionMap.get(str1);
        collisionMap.get(str2);
    }
}
