package course03.question06;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BiodomeFamily06Test {

    @Test
    void rearrangesTigerBeforeYoungElephantAndDeer() {
        List<Animal> animals = createExampleAnimals();

        List<Animal> safeOrder = BiodomeFamily06.rearrangeSafely(animals);

        int tigerIndex = findTypeIndex(safeOrder, AnimalType.TIGER);
        int elephantIndex = findTypeIndex(safeOrder, AnimalType.ELEPHANT);
        int deerIndex = findTypeIndex(safeOrder, AnimalType.DEER);

        assertTrue(tigerIndex < elephantIndex);
        assertTrue(tigerIndex < deerIndex);
    }

    @Test
    void keepsOriginalListUnchanged() {
        List<Animal> animals = createExampleAnimals();

        BiodomeFamily06.rearrangeSafely(animals);

        assertEquals(AnimalType.ELEPHANT, animals.get(1).getType());
        assertEquals(AnimalType.TIGER, animals.get(2).getType());
    }

    @Test
    void rejectsInvalidAnimalInformation() {
        assertThrows(IllegalArgumentException.class, () -> new Animal("", AnimalType.MONKEY, 4));
        assertThrows(IllegalArgumentException.class, () -> new Animal("제니", AnimalType.MONKEY, -1));
    }

    @Test
    void appliesBonusHostileRelationship() {
        List<Animal> animals = createExampleAnimals();
        animals.add(new Animal("레오", AnimalType.LION, 8));

        List<HostileRelationship> relationships = new ArrayList<>();
        relationships.add(new HostileRelationship(AnimalType.TIGER, AnimalType.DEER));
        relationships.add(new HostileRelationship(AnimalType.LION, AnimalType.MONKEY));

        List<Animal> safeOrder = BiodomeFamily06.rearrangeSafely(animals, relationships);

        int lionIndex = findTypeIndex(safeOrder, AnimalType.LION);
        int monkeyIndex = findTypeIndex(safeOrder, AnimalType.MONKEY);
        assertTrue(lionIndex < monkeyIndex);
    }

    private List<Animal> createExampleAnimals() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("제니", AnimalType.MONKEY, 4));
        animals.add(new Animal("고먀", AnimalType.ELEPHANT, 4));
        animals.add(new Animal("타이", AnimalType.TIGER, 9));
        animals.add(new Animal("로아", AnimalType.RHINOCEROS, 5));
        animals.add(new Animal("바비", AnimalType.DEER, 7));
        return animals;
    }

    private int findTypeIndex(List<Animal> animals, AnimalType type) {
        for (int i = 0; i < animals.size(); i++) {
            if (type == animals.get(i).getType()) {
                return i;
            }
        }
        return -1;
    }
}
