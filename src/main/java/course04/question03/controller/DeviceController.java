package course04.question03.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import course04.question03.device.AdvancedFeatureCapable;
import course04.question03.device.Device;
import course04.question03.device.EnergySavingCapable;

public class DeviceController {

    private final List<Device> connectedDevices = new ArrayList<>();

    public boolean connectDevice(Device device) {
        if (device == null) {
            System.out.println("연결할 기기 정보가 없습니다.");
            return false;
        }
        if (connectedDevices.contains(device)) {
            System.out.printf("%s은(는) 이미 컨트롤러에 연결되어 있습니다.%n", device.getProductName());
            return false;
        }

        connectedDevices.add(device);
        System.out.printf("컨트롤러에 기기가 등록되었습니다 : %s%n", device.getProductName());
        return true;
    }

    public boolean powerOn(Device device) {
        if (!isConnected(device)) {
            printNotConnectedMessage(device);
            return false;
        }

        device.powerOn();
        return true;
    }

    public boolean powerOff(Device device) {
        if (!isConnected(device)) {
            printNotConnectedMessage(device);
            return false;
        }

        device.powerOff();
        return true;
    }

    public boolean activateAdvancedFeature(AdvancedFeatureCapable smartDevice) {
        // 고급 기능 구현 여부와 별개로 컨트롤러에 실제 연결된 객체인지 확인한다.
        if (!(smartDevice instanceof Device) || !connectedDevices.contains(smartDevice)) {
            System.out.println("컨트롤러에 연결된 스마트 기기만 고급 기능을 사용할 수 있습니다.");
            return false;
        }

        smartDevice.activateAdvancedFeature();
        return true;
    }

    public void powerOffAllDevices() {
        if (connectedDevices.isEmpty()) {
            System.out.println("종료할 기기가 없습니다.");
            return;
        }

        // 종료 대상 이름을 먼저 모아 전체 작업 내역을 한 줄로 출력한다.
        StringBuilder deviceNames = new StringBuilder();
        for (Device device : connectedDevices) {
            if (deviceNames.length() > 0) {
                deviceNames.append(", ");
            }
            deviceNames.append(device.getProductName());
        }

        System.out.printf("모든 기기 전원을 종료합니다 : %s%n", deviceNames);
        for (Device device : connectedDevices) {
            device.powerOff();
        }
    }

    public boolean enableEnergySavingMode(EnergySavingCapable device) {
        if (!(device instanceof Device) || !connectedDevices.contains(device)) {
            System.out.println("컨트롤러에 연결된 기기만 에너지 절약 모드를 설정할 수 있습니다.");
            return false;
        }

        device.enableEnergySavingMode();
        return true;
    }

    public boolean disableEnergySavingMode(EnergySavingCapable device) {
        if (!(device instanceof Device) || !connectedDevices.contains(device)) {
            System.out.println("컨트롤러에 연결된 기기만 에너지 절약 모드를 설정할 수 있습니다.");
            return false;
        }

        device.disableEnergySavingMode();
        return true;
    }

    public void enableEnergySavingModeForAll() {
        System.out.println("모든 기기의 에너지 절약 모드를 활성화합니다.");
        for (Device device : connectedDevices) {
            // 절약 모드를 지원하는 기기만 해당 기능으로 안전하게 확장한다.
            if (device instanceof EnergySavingCapable energySavingDevice) {
                energySavingDevice.enableEnergySavingMode();
            }
        }
    }

    public void disableEnergySavingModeForAll() {
        System.out.println("모든 기기의 에너지 절약 모드를 비활성화합니다.");
        for (Device device : connectedDevices) {
            // 절약 모드를 지원하지 않는 새 기기가 추가되어도 기존 전원 제어에는 영향이 없다.
            if (device instanceof EnergySavingCapable energySavingDevice) {
                energySavingDevice.disableEnergySavingMode();
            }
        }
    }

    public void printAllDeviceInformation() {
        System.out.println("[연결된 모든 전자기기 정보]");
        if (connectedDevices.isEmpty()) {
            System.out.println("연결된 기기가 없습니다.");
            return;
        }

        for (Device device : connectedDevices) {
            device.printInformation();
        }
    }

    public List<Device> getConnectedDevices() {
        return Collections.unmodifiableList(connectedDevices);
    }

    private boolean isConnected(Device device) {
        return device != null && connectedDevices.contains(device);
    }

    private void printNotConnectedMessage(Device device) {
        if (device == null) {
            System.out.println("조작할 기기 정보가 없습니다.");
            return;
        }
        System.out.printf("%s은(는) 컨트롤러에 연결되지 않은 기기입니다.%n", device.getProductName());
    }
}
