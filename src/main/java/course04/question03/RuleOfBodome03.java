package course04.question03;

import course04.question03.controller.DeviceController;
import course04.question03.device.NormalDevice;
import course04.question03.device.SmartDevice;

public class RuleOfBodome03 {

    public static void main(String[] args) {
        // 일반 기기와 고급 기능을 가진 스마트 기기를 생성한다.
        NormalDevice doorOpener = new NormalDevice("도어 오프너");
        SmartDevice smartMirror =
                new SmartDevice("자동 거울", "기분을 인식해 옷을 추천하는 기능");

        DeviceController controller = new DeviceController();

        // 컨트롤러에 연결된 기기만 컨트롤러를 통해 조작할 수 있다.
        System.out.println();
        controller.connectDevice(doorOpener);
        controller.connectDevice(smartMirror);

        // 스마트 기능은 전원을 켠 뒤 활성화하고, 일반 기기도 동일한 전원 인터페이스로 제어한다.
        System.out.println();
        controller.powerOn(smartMirror);
        controller.activateAdvancedFeature(smartMirror);
        controller.powerOn(doorOpener);

        System.out.println();
        controller.printAllDeviceInformation();

        System.out.println();
        controller.powerOffAllDevices();

        // 필수 예외 상황과 보너스 기능을 별도의 흐름으로 확인한다.
        demonstrateExceptionCases(controller, smartMirror);
        demonstrateEnergySavingMode(controller, doorOpener, smartMirror);
    }

    private static void demonstrateExceptionCases(
            DeviceController controller, SmartDevice poweredOffSmartDevice) {
        System.out.println("\n[예외 상황 확인]");

        controller.activateAdvancedFeature(poweredOffSmartDevice);

        SmartDevice noFeatureDevice = new SmartDevice("기능 미등록 스마트 기기", null);
        controller.connectDevice(noFeatureDevice);
        controller.powerOn(noFeatureDevice);
        controller.activateAdvancedFeature(noFeatureDevice);
        controller.powerOff(noFeatureDevice);
    }

    private static void demonstrateEnergySavingMode(
            DeviceController controller, NormalDevice normalDevice, SmartDevice smartDevice) {
        System.out.println("\n[보너스: 에너지 절약 모드 확인]");

        controller.enableEnergySavingMode(smartDevice);
        controller.disableEnergySavingMode(smartDevice);

        controller.enableEnergySavingModeForAll();
        controller.disableEnergySavingMode(normalDevice);
        controller.disableEnergySavingModeForAll();
    }
}
