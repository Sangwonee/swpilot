package course03.question07;

public class Player {
    private static final int MIN_VOLUME = 0;
    private static final int ANIMAL_MAX_VOLUME = 5;
    private static final int MANAGER_MAX_VOLUME = 50;

    private Song currentSong;
    private int volume = 1;
    private boolean playing;

    // 새 노래를 재생하며 동물용 노래라면 안전한 음량으로 먼저 낮춘다.
    public void play(Song song) {
        if (song == null) {
            throw new IllegalStateException("재생할 음악이 없습니다.");
        }

        adjustVolumeFor(song);
        currentSong = song;
        playing = true;
        System.out.println("\"" + song + "\" 재생합니다.");
    }

    // 현재 선택된 노래를 다시 재생한다.
    public void play() {
        if (currentSong == null) {
            throw new IllegalStateException("재생할 음악이 없습니다.");
        }

        play(currentSong);
    }

    public void pause() {
        if (currentSong == null || !playing) {
            throw new IllegalStateException("현재 재생 중인 음악이 없습니다.");
        }

        playing = false;
        System.out.println("\"" + currentSong + "\" 일시 정지합니다.");
    }

    // 현재 노래의 대상에 따라 허용되는 범위 안에서만 음량을 변경한다.
    public void setVolume(int volume) {
        int maxVolume = currentSong instanceof AnimalSong ? ANIMAL_MAX_VOLUME : MANAGER_MAX_VOLUME;

        if (volume < MIN_VOLUME || volume > maxVolume) {
            throw new IllegalArgumentException("볼륨은 " + MIN_VOLUME + "부터 " + maxVolume + "까지 설정할 수 있습니다.");
        }

        this.volume = volume;
        System.out.println("볼륨을 " + volume + "으로 설정합니다.");
    }

    private void adjustVolumeFor(Song song) {
        if (song instanceof AnimalSong && volume > ANIMAL_MAX_VOLUME) {
            volume = ANIMAL_MAX_VOLUME;
            System.out.println("볼륨을 " + volume + "로 설정합니다.");
        }
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public int getVolume() {
        return volume;
    }

    public boolean isPlaying() {
        return playing;
    }
}
