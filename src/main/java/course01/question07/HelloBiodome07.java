package course01.question07;

public class HelloBiodome07 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("염기서열이 입력되지 않았습니다. 다시 입력해주세요");
            return;
        }

        String rawInput = String.join(" ", args);

        // 2. 공백 제거 후 빈 값인지 검사
        String trimmed = "";
        boolean hasSpace = false;
        for (int i = 0; i < rawInput.length(); i++) {
            char ch = rawInput.charAt(i);
            if (ch == ' ') {
                hasSpace = true;
            } else {
                trimmed += ch;
            }
        }

        if (trimmed.length() == 0) {
            System.out.println("염기서열이 입력되지 않았습니다. 다시 입력해주세요");
            return;
        }

        // 3. 염기서열 유효성 검사 (C, Y, J, E, H 및 공백만 허용, 대소문자 무관)
        String upper = rawInput.toUpperCase();
        for (int i = 0; i < upper.length(); i++) {
            char ch = upper.charAt(i);
            if (ch != ' ' && ch != 'C' && ch != 'Y' && ch != 'J' && ch != 'E' && ch != 'H') {
                System.out.println("염기서열은 C, J, H, E, Y 다섯가지로만 입력됩니다. 확인하고 다시 입력해주세요");
                return;
            }
        }

        // 4. 메인 과제: 공백 제거 후 압축 결과 출력
        String noSpaces = "";
        for (int i = 0; i < upper.length(); i++) {
            char ch = upper.charAt(i);
            if (ch != ' ') {
                noSpaces += ch;
            }
        }
        String mainCompressed = compress(noSpaces);
        System.out.println(mainCompressed);

        // 5. 보너스 과제: 공백이 있는 경우, 공백을 유지(1개로 정규화)한 결과 출력
        if (hasSpace) {
            String bonusCompressed = compressBonus(upper);
            System.out.println(bonusCompressed);
        }
    }

    // 문자열을 압축하는 메서드 (for 구문만 사용)
    public static String compress(String input) {
        if (input.length() == 0) {
            return "";
        }
        String result = "";
        char prev = input.charAt(0);
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current == prev) {
                count++;
            } else {
                result += prev + "" + count;
                prev = current;
                count = 1;
            }
        }
        result += prev + "" + count;
        return result;
    }

    // 공백을 1개로 유지하며 압축하는 메서드 (for 구문만 사용)
    public static String compressBonus(String input) {
        // 1. 공백 정규화 (연속된 공백을 1개의 공백으로 변경 및 앞뒤 공백 제거)
        String normalized = "";
        boolean lastWasSpace = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == ' ') {
                if (!lastWasSpace) {
                    normalized += " ";
                    lastWasSpace = true;
                }
            } else {
                normalized += ch;
                lastWasSpace = false;
            }
        }

        // 2. 정규화된 문자열을 기준으로 공백 유지 압축 진행
        String result = "";
        char prev = '\0';
        int count = 0;
        for (int i = 0; i < normalized.length(); i++) {
            char current = normalized.charAt(i);
            if (current == ' ') {
                if (count > 0) {
                    result += prev + "" + count;
                    count = 0;
                }
                result += " ";
                prev = '\0';
            } else {
                if (prev == '\0') {
                    prev = current;
                    count = 1;
                } else if (current == prev) {
                    count++;
                } else {
                    result += prev + "" + count;
                    prev = current;
                    count = 1;
                }
            }
        }
        if (count > 0) {
            result += prev + "" + count;
        }
        return result;
    }
}
