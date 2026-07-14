package course03.question06;

import java.util.List;
import java.util.ArrayList;

public class BiodomeFamily06 {
    private static final int YOUNG_ELEPHANT_MAX_AGE = 5;
    private static final String INVALID_ANIMAL_MESSAGE = "동물 정보를 다시 확인해주세요.";

    public static void main(String[] args) {
        try {
            List<Animal> animals = new ArrayList<>();

            animals.add(new Animal("제니", AnimalType.MONKEY, 4));
            animals.add(new Animal("고먀", AnimalType.ELEPHANT, 4));
            animals.add(new Animal("타이", AnimalType.TIGER, 9));
            animals.add(new Animal("로아", AnimalType.RHINOCEROS, 5));
            animals.add(new Animal("바비", AnimalType.DEER, 7));

            List<Animal> safeOrder = rearrangeSafely(animals);

            System.out.println(animals);
            System.out.println("-> " + safeOrder);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(INVALID_ANIMAL_MESSAGE);
        }
    }
    
    // 아직 배치하지 않은 동물 중 현재 맨 앞에 세워도 안전한 동물을 차례로 선택한다.
    public static List<Animal> rearrangeSafely(List<Animal> animals) {
        return rearrangeSafely(animals, createDefaultRelationships());
    }

    // 전달받은 적대 관계와 나이 조건을 지키도록 동물 순서를 재배열한다.
    public static List<Animal> rearrangeSafely(
            List<Animal> animals,
            List<HostileRelationship> hostileRelationships) {
        validateAnimals(animals);
        validateRelationships(hostileRelationships);

        List<Animal> remainingAnimals = new ArrayList<>(animals);
        List<Animal> safeOrder = new ArrayList<>();

        while (!remainingAnimals.isEmpty()) {
            boolean animalPlaced = false;

            for (int i = 0; i < remainingAnimals.size(); i++) {
                Animal candidate = remainingAnimals.get(i);
                if (!canPlaceNext(candidate, remainingAnimals, hostileRelationships)) {
                    continue;
                }

                safeOrder.add(candidate);
                remainingAnimals.remove(i);
                animalPlaced = true;
                break;
            }

            if (!animalPlaced) {
                throw new IllegalStateException();
            }
        }

        return safeOrder;
    }

    // 적대 관계의 앞 동물과 어린 코끼리보다 앞서야 하는 호랑이가 남았는지 확인한다.
    private static boolean canPlaceNext(
            Animal candidate,
            List<Animal> remainingAnimals,
            List<HostileRelationship> hostileRelationships) {
        if (isYoungElephant(candidate)
                && containsTypeExcept(remainingAnimals, AnimalType.TIGER, candidate)) {
            return false;
        }

        for (int i = 0; i < hostileRelationships.size(); i++) {
            HostileRelationship relationship = hostileRelationships.get(i);
            if (candidate.getType() != relationship.getFollowingType()) {
                continue;
            }

            if (containsTypeExcept(
                    remainingAnimals,
                    relationship.getFrontType(),
                    candidate)) {
                return false;
            }
        }

        return true;
    }

    // 동물이 5살 이하의 코끼리인지 확인한다.
    private static boolean isYoungElephant(Animal animal) {
        return animal.getType() == AnimalType.ELEPHANT
                && animal.getAge() <= YOUNG_ELEPHANT_MAX_AGE;
    }

    // 제외할 동물을 빼고 목록에 지정한 종류의 동물이 남아 있는지 확인한다.
    private static boolean containsTypeExcept(
            List<Animal> animals,
            AnimalType type,
            Animal excludedAnimal) {
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (animal != excludedAnimal && animal.getType() == type) {
                return true;
            }
        }

        return false;
    }

    // 기본 적대 관계인 호랑이와 사슴의 선후 관계를 생성한다.
    private static List<HostileRelationship> createDefaultRelationships() {
        List<HostileRelationship> relationships = new ArrayList<>();
        relationships.add(new HostileRelationship(AnimalType.TIGER, AnimalType.DEER));
        return relationships;
    }

    // 동물 목록이 비어 있거나 잘못된 동물을 포함하는지 검사한다.
    private static void validateAnimals(List<Animal> animals) {
        if (animals == null || animals.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i) == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    // 적대 관계 목록이 null이거나 잘못된 관계를 포함하는지 검사한다.
    private static void validateRelationships(List<HostileRelationship> hostileRelationships) {
        if (hostileRelationships == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < hostileRelationships.size(); i++) {
            if (hostileRelationships.get(i) == null) {
                throw new IllegalArgumentException();
            }
        }
    }
}
