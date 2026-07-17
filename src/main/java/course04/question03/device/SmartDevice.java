package course04.question03.device;

public class SmartDevice extends ElectronicDevice implements AdvancedFeatureCapable {

    private final String advancedFeature;

    public SmartDevice(String productName, String advancedFeature) {
        super(productName);
        this.advancedFeature = normalizeFeature(advancedFeature);

        System.out.printf(
                "스마트 기기가 생성되었습니다 : %s, %s, %s%n",
                getProductName(),
                getBrandName(),
                hasAdvancedFeature() ? this.advancedFeature : "고급 기능 미입력");
    }

    public String getAdvancedFeature() {
        return advancedFeature;
    }

    @Override
    public void activateAdvancedFeature() {
        // 꺼진 상태에서는 기능 유무와 관계없이 어떤 고급 기능도 실행하지 않는다.
        if (!isPoweredOn()) {
            System.out.printf(
                    "%s 전원이 꺼져 있어 고급 기능을 활성화할 수 없습니다.%n", getProductName());
            return;
        }
        // 기능이 누락된 스마트 기기는 예외 종료 대신 안내 문구를 출력한다.
        if (!hasAdvancedFeature()) {
            System.out.printf("%s에 고급 기능이 입력되지 않았습니다.%n", getProductName());
            return;
        }

        System.out.printf(
                "%s 고급 기능을 활성화했습니다 : %s%n", getProductName(), advancedFeature);
    }

    @Override
    public void printInformation() {
        super.printInformation();
        System.out.printf(
                "고급 기능: %s%n", hasAdvancedFeature() ? advancedFeature : "입력되지 않음");
    }

    @Override
    protected String getDeviceType() {
        return "스마트 기기";
    }

    private boolean hasAdvancedFeature() {
        return advancedFeature != null;
    }

    private static String normalizeFeature(String advancedFeature) {
        if (advancedFeature == null || advancedFeature.trim().isEmpty()) {
            return null;
        }
        return advancedFeature.trim();
    }
}
