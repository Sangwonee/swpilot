package course03.question05;

public class WindAmulet extends AncientArtifact implements WeatherController {

    public WindAmulet(String name) {
        super(name);
        System.out.println(name + " 유물이 생성되었습니다.");
    }

    @Override
    public void describe() {
        System.out.println("\"바람의 부적은 공기의 흐름을 이용해 날씨를 조절합니다.\"");
    }

    @Override
    public void controlWeather() {
        System.out.println("\"바람의 부적으로 공기의 흐름을 바꾸어 날씨를 조절했습니다!\"");
    }
}
