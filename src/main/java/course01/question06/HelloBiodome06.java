package course01.question06;

public class HelloBiodome06 {  

    public static void main(String[] args) {
        // 1. 입력값 개수 검증
        if (args.length < 2) {
            System.out.println("두 개의 유전자 코드를 입력해주세요.");
            return;
        }

        String gene1 = args[0];
        String gene2 = args[1];

        // 2. 유전자 코드 유효성 검증 (숫자와 영어 소문자, 길이 5~20자)
        if (!isValidGeneCode(gene1) || !isValidGeneCode(gene2)) {
            System.out.println("유전자 코드가 유효하지 않습니다. 5~20자의 숫자와 영어 소문자여야 합니다.");
            return;
        }

        // 3. 두 문자열이 동일한지 비교 (equals() 없이 구현) 및 보너스 과제 처리
        if (isEqual(gene1, gene2)) {
            System.out.println("동일한 유전자 코드입니다.");
        } else {
            System.out.println("일치하지 않습니다.");
            // 동일하지 않은 경우에만 부분 포함 여부 출력
            if (contains(gene1, gene2)) {
                System.out.println("부분적으로 포함됩니다.");
            } else {
                System.out.println("포함되지 않습니다.");
            }
        }
    }

    // 유전자 코드 유효성 검증 메서드 (while 구문 사용)
    public static boolean isValidGeneCode(String code) {
        if (code == null) {
            return false;
        }
        int len = code.length();
        if (len < 5 || len > 20) {
            return false;
        }
        int i = 0;
        while (i < len) {
            char ch = code.charAt(i);
            if (!((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9'))) {
                return false;
            }
            i++;
        }
        return true;
    }

    // 두 문자열이 완벽히 동일한지 비교하는 메서드 (while 구문 사용)
    public static boolean isEqual(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        int i = 0;
        int len = s1.length();
        while (i < len) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    // 첫 번째 문자열(target)이 두 번째 문자열(source)에 포함되는지 여부를 판단하는 메서드 (while 구문 사용)
    public static boolean contains(String target, String source) {
        if (target == null || source == null) {
            return false;
        }
        int targetLen = target.length();
        int sourceLen = source.length();

        if (targetLen > sourceLen) {
            return false;
        }

        int i = 0;
        int limit = sourceLen - targetLen;
        while (i <= limit) {
            int j = 0;
            boolean match = true;
            while (j < targetLen) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    match = false;
                    break;
                }
                j++;
            }
            if (match) {
                return true;
            }
            i++;
        }
        return false;
    }
}
