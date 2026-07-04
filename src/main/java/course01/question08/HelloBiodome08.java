package course01.question08;

public class HelloBiodome08 {

    // 영어 단어 사전
    private static final String[] ENGLISH_DICTIONARY = {
        "hello", "where", "this", "biodome", "help", "tree", "new", "is", "problem", 
        "please", "need", "we", "isn't", "isn’t", "there", "a", "your", "any", 
        "thanks", "the", "for", "solution", "can", "you", "?"
    };

    // 한글 단어 사전
    private static final String[] KOREAN_DICTIONARY = {
        "안녕하세요", "새로운", "나무를", "발견했습니다", "신속한", "지원", "감사합니다", 
        "당신의", "도움이", "필요합니다"
    };

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("메시지가 입력되지 않았습니다.");
            return;
        }

        String input = String.join(" ", args).trim();
        if (input.length() > 100) {
            System.out.println("입력 가능한 메시지의 최대 길이는 100자입니다.");
            return;
        }

        // 한국어 문자 포함 여부에 따른 사전 선택
        String[] dict = isKorean(input) ? KOREAN_DICTIONARY : ENGLISH_DICTIONARY;

        // 최장 단어 우선 매칭을 위한 사전 정렬 (길이 기준 내림차순)
        String[] sortedDict = getSortedDictionary(dict);

        // 띄어쓰기 복원 프로세스 실행
        String spacedResult = processSentence(input, sortedDict);
        System.out.println(spacedResult);
    }

    // 한국어 포함 여부 판별
    public static boolean isKorean(String input) {
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch >= 0xAC00 && ch <= 0xD7A3) {
                return true;
            }
        }
        return false;
    }

    // 사전 단어를 가장 긴 단어 순서대로 내림차순 정렬
    public static String[] getSortedDictionary(String[] dict) {
        String[] sorted = new String[dict.length];
        for (int i = 0; i < dict.length; i++) {
            sorted[i] = dict[i];
        }

        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - 1 - i; j++) {
                if (sorted[j].length() < sorted[j + 1].length()) {
                    String temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    // StringBuilder를 활용해 띄어쓰기 문장을 복원하는 핵심 메서드
    public static String processSentence(String input, String[] dict) {
        StringBuilder result = new StringBuilder();
        StringBuilder unknown = new StringBuilder();

        int i = 0;
        int len = input.length();

        while (i < len) {
            boolean matched = false;

            for (int k = 0; k < dict.length; k++) {
                String word = dict[k];
                if (i + word.length() <= len && input.substring(i, i + word.length()).equals(word)) {
                    // 이전에 쌓인 사전에 없는 단어가 있다면 먼저 결과에 추가
                    if (unknown.length() > 0) {
                        if (result.length() > 0) {
                            result.append(" ");
                        }
                        result.append(unknown.toString());
                        unknown.delete(0, unknown.length());
                    }

                    // 물음표(?)의 경우 이전 단어와 띄어쓰기 생략
                    if (word.equals("?")) {
                        if (result.length() > 0 && result.charAt(result.length() - 1) == ' ') {
                            result.delete(result.length() - 1, result.length());
                        }
                        result.append("?");
                    } else {
                        if (result.length() > 0 && result.charAt(result.length() - 1) != ' ') {
                            result.append(" ");
                        }
                        result.append(word);
                    }

                    i += word.length();
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                unknown.append(input.charAt(i));
                i++;
            }
        }

        // 남은 알 수 없는 단어 처리
        if (unknown.length() > 0) {
            if (result.length() > 0 && result.charAt(result.length() - 1) != ' ') {
                result.append(" ");
            }
            result.append(unknown.toString());
        }

        // 마침표(.) 또는 물음표(?) 등의 문장 종결 처리
        if (result.length() > 0) {
            char lastChar = result.charAt(result.length() - 1);
            if (lastChar != '?') {
                result.insert(result.length(), '.');
            }
        }

        return result.toString();
    }
}
