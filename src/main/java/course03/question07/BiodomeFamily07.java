package course03.question07;

import java.util.List;
import java.util.NoSuchElementException;

public class BiodomeFamily07 {
    public static void main(String[] args) {
        try {
            MusicLibrary musicLibrary = new MusicLibrary();
            Player player = new Player();

            musicLibrary.addSong(new AnimalSong("초원을 그리며", "2분", "레이나", "사슴"));
            musicLibrary.addSong(new AnimalSong("영웅 호테", "1분", "돈키", "당나귀"));
            musicLibrary.addSong(new AnimalSong("과자를 줄게", "3분", "제롬", "코끼리"));
            musicLibrary.addSong(new ManagerSong("화양연화", "2분", "장양림", "재즈"));
            musicLibrary.addSong(new ManagerSong("시간의 수평선", "4분", "하윤", "팝"));

            System.out.println();

            List<ManagerSong> managerSongs = musicLibrary.getManagerSongs();
            player.play(managerSongs.get(0));
            player.setVolume(30);

            List<AnimalSong> donkeySongs = musicLibrary.searchByTargetAnimal("당나귀");
            player.play(donkeySongs.get(0));

            System.out.println();
            musicLibrary.removeSong("시간의 수평선");
        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
