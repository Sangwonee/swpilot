package course03.question07;

import java.util.Arrays;
import java.util.List;

public class ManagerSong extends Song {
    private static final List<String> AVAILABLE_GENRES = Arrays.asList("재즈", "팝", "클래식", "힙합");

    private final String genre;

    public ManagerSong(String title, String duration, String artist, String genre) {
        super(title, duration, artist);
        if (!AVAILABLE_GENRES.contains(genre)) {
            throw new IllegalArgumentException("장르는 재즈, 팝, 클래식, 힙합 중 하나여야 합니다.");
        }

        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + genre;
    }
}
