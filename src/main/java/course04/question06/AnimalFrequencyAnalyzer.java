package course04.question06;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AnimalFrequencyAnalyzer {
    private final Map<String, Integer> animalCountMap;

    public AnimalFrequencyAnalyzer() {
        animalCountMap = new LinkedHashMap<>();
    }

    // 이전 분석 결과를 비운 뒤 동물별 관찰 횟수를 다시 계산한다.
    public void analyzeAnimals(String[] animals) {
        if (animals == null || animals.length == 0) {
            throw new IllegalArgumentException("분석할 동물 데이터가 없습니다.");
        }

        animalCountMap.clear();

        for (String animal : animals) {
            if (animal == null || animal.isBlank()) {
                throw new IllegalArgumentException("동물 이름은 비어 있을 수 없습니다.");
            }

            String animalName = animal.trim();
            int currentCount = animalCountMap.getOrDefault(animalName, 0);
            animalCountMap.put(animalName, currentCount + 1);
        }
    }

    // 가장 큰 관찰 횟수를 찾고, 그 횟수와 같은 모든 동물을 반환한다.
    public Set<String> getMostFrequentAnimals() {
        Set<String> mostFrequentAnimals = new LinkedHashSet<>();
        int maxFrequency = 0;

        for (Map.Entry<String, Integer> entry : animalCountMap.entrySet()) {
            int frequency = entry.getValue();

            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                mostFrequentAnimals.clear();
                mostFrequentAnimals.add(entry.getKey());
            } else if (frequency == maxFrequency) {
                mostFrequentAnimals.add(entry.getKey());
            }
        }

        return mostFrequentAnimals;
    }

    public Set<String> getObservedAnimals() {
        return new LinkedHashSet<>(animalCountMap.keySet());
    }

    // 관찰 횟수를 내림차순으로 정렬하고 같은 횟수의 동물을 하나의 Set으로 묶는다.
    public Map<Integer, Set<String>> groupAnimalsByFrequency() {
        Map<Integer, Set<String>> frequencyGroups =
                new TreeMap<>(Collections.reverseOrder());

        for (Map.Entry<String, Integer> entry : animalCountMap.entrySet()) {
            int frequency = entry.getValue();
            Set<String> animals = frequencyGroups.get(frequency);

            if (animals == null) {
                animals = new LinkedHashSet<>();
                frequencyGroups.put(frequency, animals);
            }

            animals.add(entry.getKey());
        }

        return frequencyGroups;
    }

    public Map<String, Integer> getAnimalCountMap() {
        return Collections.unmodifiableMap(animalCountMap);
    }
}
