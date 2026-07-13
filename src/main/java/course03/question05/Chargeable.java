package course03.question05;

public interface Chargeable {
    String getName();

    int readChargeLevel();

    void updateChargeLevel(int chargeLevel);

    default void charge(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("충전량은 0보다 커야 합니다.");
        }

        int newChargeLevel = Math.addExact(getChargeLevel(), amount);
        updateChargeLevel(newChargeLevel);
        System.out.println(getName() + "에 " + amount + "만큼 에너지를 충전합니다.");
    }

    default int getChargeLevel() {
        return readChargeLevel();
    }

    static void showChargingTips() {
        System.out.println("에너지를 효율적으로 충전하려면 마법사의 기분이 좋아야 합니다.");
    }
}
