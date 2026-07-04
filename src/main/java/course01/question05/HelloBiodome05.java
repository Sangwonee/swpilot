package course01.question05;

public class HelloBiodome05 {

    public static void main(String[] args) {
        // 1. 수식을 만족하는 g와 h값을 구한다 (4 bit 범위인 0 ~ 15 탐색)
        int[] gh = findGH();
        int g = gh[0];
        int h = gh[1];

        System.out.println("구해진 변수 값: g = " + g + ", h = " + h);

        // 2. 세 번째 수식 계산 및 결과 출력
        int result = evaluateFormula(g, h);
        System.out.println("세 번째 수식 계산 결과 = " + result);

        // 3. 보너스 과제: 동일한 결과값(42)을 나타내는 새로운 수식 계산 및 결과 출력
        int bonusResult = evaluateBonusFormula(g, h);
        System.out.println("보너스 수식 계산 결과 = " + bonusResult);
    }

    // 두 개의 수식을 모두 만족하는 g와 h값을 구하는 메서드
    public static int[] findGH() {
        for (int g = 0; g <= 15; g++) {
            for (int h = 0; h <= 15; h++) {
                int term1 = g & ((1 >> g) << 2);
                int term2 = (h + g) ^ h;
                // 두 수식(수식 1 및 결과가 42인 조건)을 모두 만족하는 g, h 탐색
                if ((term1 | term2) == 1 && evaluateFormula(g, h) == 42) {
                    return new int[]{g, h};
                }
            }
        }
        return new int[]{0, 0};
    }

    // 세 번째 수식의 결과 값을 구하는 메서드
    public static int evaluateFormula(int g, int h) {
        return (h * h + g) * (h << h) + (g << g);
    }

    public static int evaluateBonusFormula(int g, int h) {
        return ((h << 2) + h) * (h * 2) + h;
    }
}
