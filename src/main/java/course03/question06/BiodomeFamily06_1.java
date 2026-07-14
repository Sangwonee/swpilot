package course03.question06;

import java.util.ArrayList;
import java.util.List;

public class BiodomeFamily06_1 {
    private static final String INVALID_ANIMAL_MESSAGE = "동물 정보를 다시 확인해주세요.";

    public static void main(String[] args) {
        try {
            List<Animal> animals = new ArrayList<>();
            animals.add(new Animal("제니", AnimalType.MONKEY, 4));
            animals.add(new Animal("고먀", AnimalType.ELEPHANT, 4));
            animals.add(new Animal("타이", AnimalType.TIGER, 9));
            animals.add(new Animal("로아", AnimalType.RHINOCEROS, 5));
            animals.add(new Animal("바비", AnimalType.DEER, 7));

            animals.add(new Animal("레오", AnimalType.LION, 8));

            List<HostileRelationship> hostileRelationships = new ArrayList<>();
            hostileRelationships.add(
                    new HostileRelationship(AnimalType.TIGER, AnimalType.DEER));
            hostileRelationships.add(
                    new HostileRelationship(AnimalType.LION, AnimalType.MONKEY));

            List<Animal> safeOrder = BiodomeFamily06.rearrangeSafely(
                    animals,
                    hostileRelationships);

            System.out.println(animals);
            System.out.println("-> " + safeOrder);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(INVALID_ANIMAL_MESSAGE);
        }
    }
}
