package course01.question03;

public class HelloBiodome03 {
    
    public static void main(String[] args) {
        final double PI = 3.14;

        if (args.length != 3) {
            System.out.println("올바른 값이 입력되지 않았습니다. [온도][습도][산소농도] 순서 대로 숫자 값을 입력해주세요");
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
            System.out.println("올바른 값이 입력되지 않았습니다. [온도][습도][산소농도] 순서 대로 숫자 값을 입력해주세요");
            return;
        }

        double hValue = h(temperature, humidity, oxygen, PI);
        System.out.println("생명지수 H = " + hValue);
        System.out.println("루트값 (미사용) : " + sqrt(humidity));
        System.out.println("절대값 (미사용) : " + abs(temperature, sqrt(humidity)));

        // 보너스 과제: 소수점 셋째 자리에서 반올림한 값도 함께 출력 (Math 패키지 없이 구현)
        double roundedValue = (int) (hValue * 100 + 0.5) / 100.0;
        System.out.println("생명지수 H (반올림) = " + roundedValue);
    }

    // 습도값을 인자로 입력 받아 루트 계산 결과를 반환하는 수식(√)을 메서드로 구현
    public static double sqrt(double humidity) {
        if (humidity < 0) {
            return 0;
        }
        return sqrtHelper(humidity, humidity / 2.0);
    }

    // 루프 대신 재귀를 활용하여 Math 패키지 없이 루트 계산 구현
    private static double sqrtHelper(double value, double t) {
        if (t == 0) {
            return 0;
        }
        double next = (t + value / t) / 2.0;
        if (abs(t, next) < 0.000001) {
            return next;
        }
        return sqrtHelper(value, next);
    }

    // √습도와 온도를 입력 받아 절대값을 계산하고 결과를 반환하는 수식
    public static double abs(double temperature, double sqrtHumidity) {
        if (temperature > sqrtHumidity) {
            return temperature - sqrtHumidity;
        } else {
            return sqrtHumidity - temperature;
        }
    }

    // 생명나무의 건강지수를 계산하는 수식을 메서드로 구현
    public static double h(double temperature, double humidity, double oxygen, double pi) {
        return 0.415 * abs(temperature, sqrt(humidity)) + oxygen / (pi * pi);
    }
}
