package course06.question07;

public class EnergyManageCenter {
    private static final int INITIAL_ENERGY = 5_000;
    private static final EnergyManageCenter INSTANCE = new EnergyManageCenter();

    private int totalEnergy;

    private EnergyManageCenter() {
        totalEnergy = INITIAL_ENERGY;
    }

    public static EnergyManageCenter getInstance() {
        return INSTANCE;
    }

    // 여러 곳에서 요청해도 중앙 센터의 에너지가 한 번에 하나씩 변경되도록 한다.
    public synchronized int allocateEnergy(int requestedEnergy) {
        if (requestedEnergy <= 0) {
            throw new IllegalArgumentException("할당할 에너지양은 0보다 커야 합니다.");
        }
        if (requestedEnergy > totalEnergy) {
            throw new IllegalStateException("중앙 에너지 센터의 보유 에너지가 부족합니다.");
        }

        totalEnergy -= requestedEnergy;
        return totalEnergy;
    }

    public synchronized int replenishEnergy(int replenishedEnergy) {
        if (replenishedEnergy <= 0) {
            throw new IllegalArgumentException("보충할 에너지양은 0보다 커야 합니다.");
        }
        if (totalEnergy > Integer.MAX_VALUE - replenishedEnergy) {
            throw new IllegalArgumentException("보충 후 에너지양이 저장 가능한 범위를 초과합니다.");
        }

        totalEnergy += replenishedEnergy;
        return totalEnergy;
    }

    public synchronized int getTotalEnergy() {
        return totalEnergy;
    }
}
