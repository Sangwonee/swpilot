package course02.question10;

import java.util.Stack;

public class RoadToBiodome10_1 {

    private static final int MIN_PLANT_NUMBER = 1;
    private static final int MAX_PLANT_NUMBER = 100;
    private static final String INVALID_INPUT_MESSAGE = "입력 값에 잘못된 값이 포함되어 있습니다. 다시 한번 확인해주세요.";

    public static void main(String[] args) {
        try {
            int[][] relationships = new int[MAX_PLANT_NUMBER + 1][MAX_PLANT_NUMBER + 1];
            boolean[] existingPlants = new boolean[MAX_PLANT_NUMBER + 1];

            parseRelationships(args, relationships, existingPlants);
            int groupCount = countRelatedGroups(relationships, existingPlants);

            System.out.println(groupCount);
        } catch (IllegalArgumentException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // 명령행의 식물 쌍을 검사해 양방향 연관관계 행렬에 저장한다.
    private static void parseRelationships(
            String[] args,
            int[][] relationships,
            boolean[] existingPlants) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }

        String input = String.join(" ", args).trim();
        if (input.length() == 0) {
            throw new IllegalArgumentException();
        }

        String[] relationshipValues = input.split("\\s+");

        for (int i = 0; i < relationshipValues.length; i++) {
            String[] plantNumbers = relationshipValues[i].split(",", -1);
            if (plantNumbers.length != 2) {
                throw new IllegalArgumentException();
            }

            int firstPlant = parsePlantNumber(plantNumbers[0]);
            int secondPlant = parsePlantNumber(plantNumbers[1]);

            relationships[firstPlant][secondPlant] = 1;
            relationships[secondPlant][firstPlant] = 1;
            existingPlants[firstPlant] = true;
            existingPlants[secondPlant] = true;
        }
    }

    // 식물 번호를 정수로 변환하고 허용 범위인 1부터 100인지 확인한다.
    private static int parsePlantNumber(String value) {
        if (value.length() == 0) {
            throw new IllegalArgumentException();
        }

        int plantNumber = Integer.parseInt(value);
        if (plantNumber < MIN_PLANT_NUMBER || plantNumber > MAX_PLANT_NUMBER) {
            throw new IllegalArgumentException();
        }

        return plantNumber;
    }

    // 아직 방문하지 않은 식물에서 Stack DFS를 시작한 횟수로 그룹 수를 센다.
    public static int countRelatedGroups(int[][] relationships, boolean[] existingPlants) {
        boolean[] visited = new boolean[MAX_PLANT_NUMBER + 1];
        int groupCount = 0;

        for (int plant = MIN_PLANT_NUMBER; plant <= MAX_PLANT_NUMBER; plant++) {
            if (!existingPlants[plant] || visited[plant]) {
                continue;
            }

            depthFirstSearchWithStack(plant, relationships, visited);
            groupCount++;
        }

        return groupCount;
    }

    // Stack에서 식물을 꺼내며 연결된 모든 미방문 식물을 반복적으로 탐색한다.
    private static void depthFirstSearchWithStack(
            int startPlant,
            int[][] relationships,
            boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startPlant);
        visited[startPlant] = true;

        while (!stack.isEmpty()) {
            int plant = stack.pop();

            // 큰 번호부터 넣어 작은 번호가 Stack에서 먼저 나오도록 한다.
            for (int relatedPlant = MAX_PLANT_NUMBER;
                    relatedPlant >= MIN_PLANT_NUMBER;
                    relatedPlant--) {
                if (relationships[plant][relatedPlant] == 1 && !visited[relatedPlant]) {
                    visited[relatedPlant] = true;
                    stack.push(relatedPlant);
                }
            }
        }
    }
}
