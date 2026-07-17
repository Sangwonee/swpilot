package course04.question03.device;

public class NormalDevice extends ElectronicDevice {

    public NormalDevice(String productName) {
        super(productName);
        System.out.printf("일반 기기가 생성되었습니다 : %s, %s%n", getProductName(), getBrandName());
    }

    @Override
    protected String getDeviceType() {
        return "일반 기기";
    }
}
