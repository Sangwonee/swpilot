package course04.question06;

import java.util.Map;
import java.util.Set;

public class RuleOfBiodome06 {
    private static final int MINIMUM_ANIMAL_COUNT = 20;
    private static final String INVALID_INPUT_MESSAGE = "20마리 이상의 올바른 동물 데이터를 입력해주세요.";

    public static void main(String[] args) {
        try {
            String[] animals = parseAnimals(args);
            AnimalFrequencyAnalyzer analyzer = new AnimalFrequencyAnalyzer();

            analyzer.analyzeAnimals(animals);

            System.out.println("가장 많이 발견된 동물 : "
                    + String.join(", ", analyzer.getMostFrequentAnimals()));
            System.out.println("관찰된 모든 동물 : "
                    + String.join(", ", analyzer.getObservedAnimals()));

            printFrequencyGroups(analyzer.groupAnimalsByFrequency());
        } catch (IllegalArgumentException e) {
            System.out.println(INVALID_INPUT_MESSAGE);
        }
    }

    // 배열 형태 한 문자열과 공백으로 나뉜 여러 인자를 모두 동물 이름 배열로 변환한다.
    private static String[] parseAnimals(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException();
        }

        String input = String.join(" ", args)
                .replace("[", "")
                .replace("]", "")
                .replace("\"", "")
                .replace("'", "")
                .trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String[] animals = input.contains(",")
                ? input.split(",", -1)
                : input.split("\\s+");

        if (animals.length < MINIMUM_ANIMAL_COUNT) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < animals.length; i++) {
            animals[i] = animals[i].trim();
            if (animals[i].isEmpty()) {
                throw new IllegalArgumentException();
            }
        }

        return animals;
    }

    private static void printFrequencyGroups(Map<Integer, Set<String>> frequencyGroups) {
        System.out.println();
        System.out.println("[관찰 빈도별 동물]");

        for (Map.Entry<Integer, Set<String>> entry : frequencyGroups.entrySet()) {
            System.out.printf("%d회: %s%n",
                    entry.getKey(), String.join(", ", entry.getValue()));
        }
    }
}
