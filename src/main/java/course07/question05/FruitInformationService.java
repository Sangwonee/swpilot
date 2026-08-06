package course07.question05;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class FruitInformationService {
    private static final int MAX_REQUEST_COUNT = 5;

    private final Map<String, Fruit> fruits = new HashMap<>();
    private int totalRequestCount;

    FruitInformationService() {
        fruits.put("apple", new Fruit("사과", 3000, false, false));
        fruits.put("banana", new Fruit("바나나", 7000, true, false));
        fruits.put("orange", new Fruit("오렌지", 5000, false, false));
        fruits.put("grape", new Fruit("포도", 6000, false, true));
    }

    // 요청 수 확인과 과일 상태 변경을 한 번에 처리해 동시 요청에도 횟수가 꼬이지 않게 한다.
    synchronized String answerQuestion(String fruitName) {
        if (totalRequestCount >= MAX_REQUEST_COUNT) {
            return "농장의 안내 업무가 종료되었습니다.";
        }
        totalRequestCount++;

        String key = fruitName.toLowerCase(Locale.ROOT);
        Fruit fruit = fruits.get(key);
        if (fruit == null) {
            return "농장에 없는 과일입니다.";
        }

        if (fruit.getRequestCount() > 0) {
            fruit.increaseRequestCount();
            return fruit.getName() + "는 이미 안내한 과일입니다.";
        }

        fruit.increaseRequestCount();
        if (fruit.isFarmChanged()) {
            return fruit.getName() + "는 다른 농장으로 이동 중이라 수확할 수 없습니다.";
        }
        if (fruit.isReserved()) {
            return fruit.getName() + "는 전량 예약 되어 판매할 수 없습니다.";
        }
        return fruit.getName() + "의 가격은 " + fruit.getPrice() + "원 입니다.";
    }

    synchronized void resetRequestCounts() {
        totalRequestCount = 0;   
        for (Fruit fruit : fruits.values()) {
            fruit.resetRequestCount();
        }
    }
}
