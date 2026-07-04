package course01.question09;

public class HelloBiodome09 {

    public static void main(String[] args) {
        if (args.length == 0) {
            printErrorMessage();
            return;
        }

        int height;
        try {
            height = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            printErrorMessage();
            return;
        }

        if (height < 3 || height > 100) {
            printErrorMessage();
            return;
        }

        // 보너스 과제: 두 번째 인자가 주어지면 정중앙 채우기 문자로 사용, 없으면 '*' 사용
        char centerChar = '*';
        if (args.length >= 2 && args[1].length() > 0) {
            centerChar = args[1].charAt(0);
        }

        drawTree(height, centerChar);
    }

    private static void printErrorMessage() {
        System.out.println("잘못된 입력입니다. 3~100 사이의 숫자를 입력하세요.");
        System.out.println("나무를 그릴 수 있도록 3~100까지의 숫자값을 입력해주세요");
    }

    // 나무 그리기 메서드
    public static void drawTree(int height, char centerChar) {
        // H와 동일한 높이의 나무 그리기
        for (int i = 0; i < height; i++) {
            // 1. 공백 출력 (H - 1 - i 개)
            for (int j = 0; j < height - 1 - i; j++) {
                System.out.print(" ");
            }

            // 2. 왼쪽 별 출력 (i 개)
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }

            // 3. 정중앙 문자 출력
            System.out.print(centerChar);

            // 4. 오른쪽 별 출력 (i 개)
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

        // 5. 정중앙 아래에 나무 기둥 '|' 출력 (H - 1 개의 공백 + '|')
        for (int j = 0; j < height - 1; j++) {
            System.out.print(" ");
        }
        System.out.println("|");
    }
}
