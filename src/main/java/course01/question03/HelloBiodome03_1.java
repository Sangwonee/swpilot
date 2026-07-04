package course01.question03;

public class HelloBiodome03_1 {

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
        System.out.println("루트값 (사용) : " + sqrt(humidity));
        System.out.println("절대값 (사용) : " + abs(temperature, sqrt(humidity)));

        // 보너스 과제: 소수점 셋째 자리에서 반올림한 값도 함께 출력 (Math 패키지 사용)
        double roundedValue = Math.round(hValue * 100.0) / 100.0;
        System.out.println("생명지수 H (반올림) = " + roundedValue);
    }

    // 습도값을 인자로 입력 받아 루트 계산 결과를 반환하는 수식(√)을 메서드로 구현
    public static double sqrt(double humidity) {
        return Math.sqrt(humidity);
    }

    // √습도와 온도를 입력 받아 절대값을 계산하고 결과를 반환하는 수식
    public static double abs(double temperature, double sqrtHumidity) {
        return Math.abs(temperature - sqrtHumidity);
    }

    // 생명나무의 건강지수를 계산하는 수식을 메서드로 구현
    public static double h(double temperature, double humidity, double oxygen, double pi) {
        return 0.415 * abs(temperature, sqrt(humidity)) + oxygen / (pi * pi);
    }
}
