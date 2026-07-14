package course03.question07;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class BiodomeFamily07Test {
    @Test
    void 동물용과_사람용_노래를_한_라이브러리에서_관리한다() {
        MusicLibrary library = new MusicLibrary();
        library.addSong(new AnimalSong("초원을 그리며", "2분", "레이나", "사슴"));
        library.addSong(new ManagerSong("화양연화", "2분", "장양림", "재즈"));

        assertEquals(2, library.getAllSongs().size());
        assertEquals(1, library.getAnimalSongs().size());
        assertEquals(1, library.getManagerSongs().size());
    }

    @Test
    void 같은_제목의_노래는_추가할_수_없다() {
        MusicLibrary library = new MusicLibrary();
        library.addSong(new AnimalSong("같은 제목", "2분", "레이나", "사슴"));

        assertThrows(IllegalArgumentException.class, () ->
                library.addSong(new ManagerSong("같은 제목", "3분", "하윤", "팝")));
    }

    @Test
    void 대상_동물과_장르로_노래를_검색한다() {
        MusicLibrary library = new MusicLibrary();
        library.addSong(new AnimalSong("영웅 호테", "1분", "돈키", "당나귀"));
        library.addSong(new ManagerSong("화양연화", "2분", "장양림", "재즈"));

        List<AnimalSong> animalSongs = library.searchByTargetAnimal("당나귀");
        List<ManagerSong> managerSongs = library.searchByGenre("재즈");

        assertEquals("영웅 호테", animalSongs.get(0).getTitle());
        assertEquals("화양연화", managerSongs.get(0).getTitle());
        assertThrows(NoSuchElementException.class,
                () -> library.searchByTargetAnimal("사슴"));
    }

    @Test
    void 동물용_노래를_재생하면_볼륨을_5로_낮춘다() {
        Player player = new Player();
        player.play(new ManagerSong("화양연화", "2분", "장양림", "재즈"));
        player.setVolume(30);

        player.play(new AnimalSong("영웅 호테", "1분", "돈키", "당나귀"));

        assertEquals(5, player.getVolume());
        assertTrue(player.isPlaying());
        assertThrows(IllegalArgumentException.class, () -> player.setVolume(6));
    }

    @Test
    void 노래를_삭제하고_재생을_일시_정지한다() {
        MusicLibrary library = new MusicLibrary();
        Player player = new Player();
        Song song = new ManagerSong("시간의 수평선", "4분", "하윤", "팝");
        library.addSong(song);

        player.play(song);
        player.pause();
        Song removedSong = library.removeSong("시간의 수평선");

        assertEquals(song, removedSong);
        assertFalse(player.isPlaying());
        assertTrue(library.getAllSongs().isEmpty());
    }

    @Test
    void 재생할_노래가_없으면_예외가_발생한다() {
        Player player = new Player();

        assertEquals(1, player.getVolume());
        assertThrows(IllegalStateException.class, player::play);
    }
}
