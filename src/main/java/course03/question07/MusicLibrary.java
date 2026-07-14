package course03.question07;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MusicLibrary {
    private final List<Song> songs = new ArrayList<>();

    // 같은 제목이 있는지 확인한 후 새 노래를 라이브러리에 추가한다.
    public void addSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("추가할 음악이 없습니다.");
        }
        if (containsTitle(song.getTitle())) {
            throw new IllegalArgumentException(
                    "\"" + song.getTitle() + "\" 노래가 이미 존재합니다.");
        }

        songs.add(song);
        System.out.println("새로운 노래 \"" + song + "\" 추가되었습니다.");
    }

    // 제목이 정확히 일치하는 노래를 찾아 삭제한다.
    public Song removeSong(String title) {
        List<Song> foundSongs = searchByTitle(title);
        Song removedSong = foundSongs.get(0);
        songs.remove(removedSong);
        System.out.println("노래 \"" + removedSong + "\" 삭제되었습니다.");
        return removedSong;
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(songs);
    }

    public List<AnimalSong> getAnimalSongs() {
        List<AnimalSong> animalSongs = new ArrayList<>();

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song instanceof AnimalSong) {
                animalSongs.add((AnimalSong) song);
            }
        }

        return animalSongs;
    }

    public List<ManagerSong> getManagerSongs() {
        List<ManagerSong> managerSongs = new ArrayList<>();

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song instanceof ManagerSong) {
                managerSongs.add((ManagerSong) song);
            }
        }

        return managerSongs;
    }

    // 제목이 정확히 일치하는 노래를 검색한다.
    public List<Song> searchByTitle(String title) {
        validateSearchValue(title);
        List<Song> foundSongs = new ArrayList<>();

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song.getTitle().equals(title)) {
                foundSongs.add(song);
            }
        }

        ensureSearchResult(foundSongs, title);
        return foundSongs;
    }

    // 동물용 노래 중 대상 동물이 일치하는 노래를 검색한다.
    public List<AnimalSong> searchByTargetAnimal(String targetAnimal) {
        validateSearchValue(targetAnimal);
        List<AnimalSong> foundSongs = new ArrayList<>();

        List<AnimalSong> animalSongs = getAnimalSongs();
        for (int i = 0; i < animalSongs.size(); i++) {
            AnimalSong song = animalSongs.get(i);
            if (song.getTargetAnimal().equals(targetAnimal)) {
                foundSongs.add(song);
            }
        }

        ensureSearchResult(foundSongs, targetAnimal);
        return foundSongs;
    }

    // 사람용 노래 중 장르가 일치하는 노래를 검색한다.
    public List<ManagerSong> searchByGenre(String genre) {
        validateSearchValue(genre);
        List<ManagerSong> foundSongs = new ArrayList<>();

        List<ManagerSong> managerSongs = getManagerSongs();
        for (int i = 0; i < managerSongs.size(); i++) {
            ManagerSong song = managerSongs.get(i);
            if (song.getGenre().equals(genre)) {
                foundSongs.add(song);
            }
        }

        ensureSearchResult(foundSongs, genre);
        return foundSongs;
    }

    private boolean containsTitle(String title) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equals(title)) {
                return true;
            }
        }

        return false;
    }

    private static void validateSearchValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("검색 조건을 입력해주세요.");
        }
    }

    private static void ensureSearchResult(List<?> foundSongs, String condition) {
        if (foundSongs.isEmpty()) {
            throw new NoSuchElementException("\"" + condition + "\"에 해당하는 노래가 존재하지 않습니다.");
        }
    }
}
