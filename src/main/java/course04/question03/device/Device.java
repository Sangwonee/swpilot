package course04.question03.device;

public interface Device {

    String getProductName();

    String getBrandName();

    boolean isPoweredOn();

    void powerOn();

    void powerOff();

    void printInformation();
}
