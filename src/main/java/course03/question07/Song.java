package course03.question07;

public class Song {
    private final String title;
    private final String duration;
    private final String artist;

    public Song(String title, String duration, String artist) {
        validateText(title);
        validateText(duration);
        validateText(artist);

        this.title = title;
        this.duration = duration;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    private static void validateText(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("노래 정보를 다시 확인해주세요.");
        }
    }

    @Override
    public String toString() {
        return title + ", " + duration + ", " + artist;
    }

}
