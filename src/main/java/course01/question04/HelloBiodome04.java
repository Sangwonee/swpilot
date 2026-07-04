package course01.question04;

import course01.question03.HelloBiodome03;

public class HelloBiodome04 {

    public static void main(String[] args) {
        final double PI = 3.14;

        if (args.length < 3) {
            System.out.println("입력되지 않은 값이 있습니다. [온도][습도][산소농도] 순서대로 숫자 값을 입력해주세요");
            return;
        }

        double temperature;
        double humidity;
        double oxygen;

        try {
            temperature = Double.parseDouble(args[0]);
            humidity = Double.parseDouble(args[1]);
            oxygen = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("입력된 값이 올바르지 않습니다. [온도][습도][산소농도] 순서대로 숫자 값을 입력해주세요");
            return;
        }

        if (isStable(temperature, humidity, oxygen)) {
            // question03에서 구현한 h() 메서드 재사용
            double hValue = HelloBiodome03.h(temperature, humidity, oxygen, PI);

            double roundedValue = (int) (hValue * 100 + 0.5) / 100.0;

            System.out.println("생명의 나무는 안정적인 상태입니다 :)");
            System.out.println("생명의 나무는 안정적인 상태입니다. 건강지수는 " + roundedValue + "입니다.");
        } else {
            if (temperature < 10.0 || temperature >= 27.5) {
                System.out.println("온도값이 정상 범위를 벗어났습니다. 확인이 필요합니다.");
            } else if (humidity <= 40.0 || humidity >= 60.0) {
                System.out.println("습도값이 정상 범위를 벗어났습니다. 확인이 필요합니다.");
            } else if (oxygen < 19.5 || oxygen > 23.5) {
                System.out.println("산소농도값이 정상 범위를 벗어났습니다. 확인이 필요합니다.");
            }
        }
    }

    // 사용자가 입력한 환경 값의 범위를 검토하기 위해 조건문을 활용해 boolean값을 반환하는 메서드
    public static boolean isStable(double temperature, double humidity, double oxygen) {
        if (temperature >= 10.0 && temperature < 27.5) {
            if (humidity > 40.0 && humidity < 60.0) {
                if (oxygen >= 19.5 && oxygen <= 23.5) {
                    return true;
                }
            }
        }
        return false;
    }
}
