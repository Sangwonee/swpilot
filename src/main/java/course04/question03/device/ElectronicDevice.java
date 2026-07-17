package course04.question03.device;

public abstract class ElectronicDevice implements Device, EnergySavingCapable {

    public static final String BRAND_NAME = "DOMETech";

    private final String productName;
    private boolean poweredOn;
    private boolean energySavingModeEnabled;

    protected ElectronicDevice(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("제품명은 비어 있을 수 없습니다.");
        }
        this.productName = productName.trim();
    }

    @Override
    public final String getProductName() {
        return productName;
    }

    @Override
    public final String getBrandName() {
        return BRAND_NAME;
    }

    @Override
    public final boolean isPoweredOn() {
        return poweredOn;
    }

    @Override
    public final void powerOn() {
        if (poweredOn) {
            System.out.printf("%s 전원은 이미 켜져 있습니다.%n", productName);
            return;
        }

        poweredOn = true;
        System.out.printf("%s 전원을 켰습니다.%n", productName);
    }

    @Override
    public final void powerOff() {
        if (!poweredOn) {
            System.out.printf("%s 전원은 이미 꺼져 있습니다.%n", productName);
            return;
        }

        poweredOn = false;
        System.out.printf("%s 전원을 껐습니다.%n", productName);
    }

    @Override
    public final boolean isEnergySavingModeEnabled() {
        return energySavingModeEnabled;
    }

    @Override
    public final void enableEnergySavingMode() {
        if (energySavingModeEnabled) {
            System.out.printf("%s 에너지 절약 모드는 이미 활성화되어 있습니다.%n", productName);
            return;
        }

        energySavingModeEnabled = true;
        System.out.printf("%s 에너지 절약 모드를 활성화했습니다.%n", productName);
    }

    @Override
    public final void disableEnergySavingMode() {
        if (!energySavingModeEnabled) {
            System.out.printf("%s 에너지 절약 모드는 이미 비활성화되어 있습니다.%n", productName);
            return;
        }

        energySavingModeEnabled = false;
        System.out.printf("%s 에너지 절약 모드를 비활성화했습니다.%n", productName);
    }

    @Override
    public void printInformation() {
        System.out.printf(
                "%s 정보 - 제품명: %s, 브랜드: %s, 전원: %s, 에너지 절약 모드: %s%n",
                getDeviceType(),
                productName,
                BRAND_NAME,
                poweredOn ? "켜짐" : "꺼짐",
                energySavingModeEnabled ? "활성화" : "비활성화");
    }

    protected abstract String getDeviceType();
}
