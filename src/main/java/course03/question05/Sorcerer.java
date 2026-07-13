package course03.question05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sorcerer {
    private final String name;
    private final List<AncientArtifact> artifacts;

    public Sorcerer(String name) {
        this.name = name;
        this.artifacts = new ArrayList<>();
        System.out.println("마법사 '" + name + "'이 생성되었습니다.");
    }

    public void addArtifact(AncientArtifact artifact) {
        artifacts.add(artifact);
        System.out.println("마법사 '" + name + "'이 " + artifact.getName() + "을 소유하게 되었습니다.");
    }

    public void inspectArtifact(AncientArtifact artifact) {
        checkOwnership(artifact);
        System.out.println("마법사 '" + name + "'이 " + artifact.getName() + "의 능력을 확인합니다.");
        artifact.describe();
    }

    public void useEnergyGeneration(AncientArtifact artifact) {
        checkOwnership(artifact);

        if (!(artifact instanceof EnergyGenerator energyGenerator)) {
            System.out.println(artifact.getName() + "에는 에너지 생성 능력이 없습니다.");
            return;
        }

        System.out.println("마법사 '" + name + "'이 " + artifact.getName() + "의 에너지 생성 능력을 사용합니다.");
        energyGenerator.generateEnergy();
    }

    public void useWeatherControl(AncientArtifact artifact) {
        checkOwnership(artifact);

        if (!(artifact instanceof WeatherController weatherController)) {
            System.out.println(artifact.getName() + "에는 날씨 조절 능력이 없습니다.");
            return;
        }

        System.out.println("마법사 '" + name + "'이 " + artifact.getName() + "의 날씨 조절 능력을 사용합니다.");
        weatherController.controlWeather();
    }

    public String getName() {
        return name;
    }

    public List<AncientArtifact> getArtifacts() {
        return Collections.unmodifiableList(artifacts);
    }

    private void checkOwnership(AncientArtifact artifact) {
        if (!artifacts.contains(artifact)) {
            throw new IllegalArgumentException("마법사가 소유하지 않은 유물입니다.");
        }
    }
}
