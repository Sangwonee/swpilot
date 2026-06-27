package course01.question01;

public class HelloBiodome01 {
    public static void main(String[] args) {
        String name = String.join(" ", args);
        // 띄어쓰기 포함 전체 출력
        // 한글자이상 압력
        // 10번쨰 이후 문자 잘라
        if (args.length == 0 || args[0].trim().isEmpty()) {
            System.out.println("이름을 1글자 이상 입력해 주세요. 프로그램을 종료합니다.");
            return;
        }

        // 정규표현식 사용
        name = name.replaceAll("^(.{10}).*", "$1");


        System.out.println("안녕하세요 반갑습니다, " + name + "님!");

    }
}
