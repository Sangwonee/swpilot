package course03.question07;

import java.util.Arrays;
import java.util.List;

public class AnimalSong extends Song {
    private static final List<String> AVAILABLE_ANIMALS = Arrays.asList("사슴", "당나귀", "코끼리");
    private final String targetAnimal;

    public AnimalSong(String title, String duration, String artist, String targetAnimal) {
        super(title, duration, artist);
        if (!AVAILABLE_ANIMALS.contains(targetAnimal)) {
            throw new IllegalArgumentException("대상 동물은 사슴, 당나귀, 코끼리 중 하나여야 합니다.");
        }

        this.targetAnimal = targetAnimal;
    }

    public String getTargetAnimal() {
        return targetAnimal;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + targetAnimal;
    }
}
