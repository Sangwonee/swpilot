package course02.question02;

public class RoadToBiodome02 {

    private static final String ERROR_MESSAGE = "입력된 메시지가 올바르지 않습니다. 다시 한번 확인해주세요.";

    public static void main(String[] args) {
        // 인자가 없는 경우 에러 메시지 출력
        if (args.length == 0) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        // 커맨드 라인 인자들을 공백을 기준으로 하나의 문자열로 합침
        String input = String.join(" ", args).trim();

        // 문자열의 길이 및 허용된 문자(영어, 한글, 숫자, 공백)인지 유효성 검증
        if (!isValidInput(input)) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        // 대소문자를 구분하지 않고 회문(Palindrome)인지 검사 (보너스 과제)
        if (isPalindrome(input)) {
            // 회문인 경우 역순 암호화를 수행하지 않고 그대로 출력
            System.out.println(input);
            return;
        }

        // 직접 구현한 스택을 생성하고 문자열의 각 문자를 순서대로 푸시(push)
        CustomCharStack stack = new CustomCharStack(input.length());
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }

        // 스택에서 문자를 팝(pop)하여 역순 배열 생성 (StringBuilder 미사용)
        char[] reversedChars = new char[input.length()];
        int index = 0;
        while (!stack.isEmpty()) {
            reversedChars[index++] = stack.pop();
        }

        // 문자 배열을 새로운 문자열로 변환하여 출력
        String reversedString = new String(reversedChars);
        System.out.println(reversedString);
    }

    private static boolean isValidInput(String input) {
        if (input == null) {
            return false;
        }
        int len = input.length();
        // 제약사항: 최소 2글자에서 최대 1,000,000글자 사이여야 함
        if (len < 2 || len > 1000000) {
            return false;
        }

        // 각 문자가 허용된 문자 범위 내에 있는지 확인
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            if (!isValidChar(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidChar(char c) {
        // 영어 알파벳 (a-z, A-Z)
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        }
        // 숫자 (0-9)
        if (c >= '0' && c <= '9') {
            return true;
        }
        // 완성형 한글 (가-힣)
        if (c >= '\uAC00' && c <= '\uD7A3') {
            return true;
        }
        // 한글 자음/모음 (ㄱ-ㅎ, ㅏ-ㅣ)
        if ((c >= '\u3131' && c <= '\u314E') || (c >= '\u314F' && c <= '\u3163')) {
            return true;
        }
        // 공백 문자 (스페이스, 탭, 줄바꿈 등)
        if (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
            return true;
        }
        return false; // 그 외의 문자(특수 기호 등)는 허용하지 않음
    }

    // 앞쪽에서 읽으나 뒤쪽에서 읽을 때 변하지 않는 회문인지 검사
    private static boolean isPalindrome(String str) {
        int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            // 대소문자 구분을 없애기 위해 소문자로 변환하여 비교
            char left = Character.toLowerCase(str.charAt(i));
            char right = Character.toLowerCase(str.charAt(len - 1 - i));
            if (left != right) {
                return false;
            }
        }
        return true;
    }

    // Java 기본 제공 Stack을 대체하기 위해 직접 구현한 스택 구조 (LIFO)
    private static class CustomCharStack {
        private final char[] elements;
        private int top;

        public CustomCharStack(int capacity) {
            this.elements = new char[capacity];
            this.top = -1; // 초기 상태는 스택이 비어있음을 나타내는 -1
        }

        // 데이터를 스택의 가장 위에 추가
        public void push(char c) {
            elements[++top] = c;
        }

        // 스택의 가장 위에 있는 데이터를 꺼내서 반환
        public char pop() {
            return elements[top--];
        }

        // 스택이 비어있는지 확인
        public boolean isEmpty() {
            return top == -1;
        }
    }
}
