package course06.question01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemberWelcomeService {
    public static final String NEW_MEMBER_GROUP = "신입 멤버";
    public static final String REGULAR_MEMBER_GROUP = "일반 멤버";

    private static final String NEW_MEMBER_PREFIX = "신입-";
    private static final String REGULAR_MEMBER_PREFIX = "멤버-";

    public ArrayList<String> parseMemberData(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("회원 정보를 입력해주세요.");
        }

        String normalizedInput = removeBrackets(input.trim());
        
        ArrayList<String> members = Arrays.stream(normalizedInput.split(","))
                .map(String::trim)
                .filter(member -> !member.isBlank())
                .collect(Collectors.toCollection(ArrayList::new));

        if (members.isEmpty()) {
            throw new IllegalArgumentException("회원 정보를 한 명 이상 입력해주세요.");
        }
        return members;
    }

    // 신입 회원만 선택하고 이름을 환영 문구로 변환한 뒤 리스트로 수집한다.
    public List<String> createWelcomeMessages(List<String> members) {
        return members.stream()
                .filter(member -> member.startsWith(NEW_MEMBER_PREFIX))
                .map(member -> member.substring(NEW_MEMBER_PREFIX.length()))
                .filter(name -> !name.isBlank())
                .map(name -> name + "님 환영합니다")
                .collect(Collectors.toList());
    }

    // 운영진을 제외한 회원을 유형별로 묶고 입력된 이름 순서를 유지한다.
    public Map<String, List<String>> groupMembers(List<String> members) {
        return members.stream()
                .filter(this::isNewOrRegularMember)
                .filter(member -> !extractName(member).isBlank())
                .collect(Collectors.groupingBy(
                        this::classifyMember,
                        LinkedHashMap::new,
                        Collectors.mapping(this::extractName, Collectors.toList())));
    }

    private boolean isNewOrRegularMember(String member) {
        return member.startsWith(NEW_MEMBER_PREFIX) || member.startsWith(REGULAR_MEMBER_PREFIX);
    }

    private String classifyMember(String member) {
        if (member.startsWith(NEW_MEMBER_PREFIX)) {
            return NEW_MEMBER_GROUP;
        }
        return REGULAR_MEMBER_GROUP;
    }

    private String extractName(String member) {
        int separatorIndex = member.indexOf('-');
        return member.substring(separatorIndex + 1).trim();
    }

    private String removeBrackets(String input) {
        String result = input;
        if (result.startsWith("[")) {
            result = result.substring(1);
        }
        if (result.endsWith("]")) {
            result = result.substring(0, result.length() - 1);
        }
        return result.trim();
    }
}
