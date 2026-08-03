package course06.question01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RunBiodome01 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MemberWelcomeService welcomeService = new MemberWelcomeService();

        System.out.println("멤버 리스트를 입력하세요:");

        try {
            String input = reader.readLine();
            ArrayList<String> members = welcomeService.parseMemberData(input);

            List<String> welcomeMessages = welcomeService.createWelcomeMessages(members);
            System.out.println(welcomeMessages);

            printGroupedMembers(welcomeService.groupMembers(members));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("회원 정보를 읽는 중 오류가 발생했습니다.");
        }
    }

    private static void printGroupedMembers(Map<String, List<String>> groupedMembers) {
        System.out.println();
        System.out.printf("%s: %s%n", MemberWelcomeService.NEW_MEMBER_GROUP,
                groupedMembers.getOrDefault(MemberWelcomeService.NEW_MEMBER_GROUP, List.of()));
        System.out.printf("%s: %s%n", MemberWelcomeService.REGULAR_MEMBER_GROUP,
                groupedMembers.getOrDefault(MemberWelcomeService.REGULAR_MEMBER_GROUP, List.of()));
    }
}
