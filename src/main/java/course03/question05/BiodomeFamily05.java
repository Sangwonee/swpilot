package course03.question05;

public class BiodomeFamily05 {

    public static void main(String[] args) {
        Sorcerer sorcerer = new Sorcerer("아리엘");
        SolarStone solarStone = new SolarStone("태양의 돌");
        WindAmulet windAmulet = new WindAmulet("바람의 부적");
        WaterMirror waterMirror = new WaterMirror("물의 거울");

        System.out.println();

        sorcerer.addArtifact(solarStone);
        sorcerer.addArtifact(windAmulet);
        sorcerer.addArtifact(waterMirror);

        System.out.println();

        sorcerer.inspectArtifact(solarStone);

        System.out.println();

        sorcerer.useEnergyGeneration(waterMirror);

        System.out.println("\n보너스 과제 실행:");

        solarStone.charge(50);
        waterMirror.charge(30);
        solarStone.charge(10);

        System.out.println();
        System.out.println(solarStone.getName() + "에 총 "
                + solarStone.getChargeLevel() + "만큼의 에너지가 충전되었습니다.");
        System.out.println(waterMirror.getName() + "에 총 "
                + waterMirror.getChargeLevel() + "만큼의 에너지가 충전되었습니다.");

        System.out.println();
        Chargeable.showChargingTips();
    }
}
