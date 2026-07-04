package course01.question08;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloBiodome08Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restore() {
        System.setOut(originalOut);
    }

    private String getOutput() {
        return outContent.toString();
    }

    @Test
    void testEmptyInput() {
        HelloBiodome08.main(new String[]{});
        String output = getOutput();
        assertTrue(output.contains("메시지가 입력되지 않았습니다."), "출력 결과: \n" + output);
    }

    @Test
    void testLongInput() {
        String longStr = "a".repeat(101);
        HelloBiodome08.main(new String[]{longStr});
        String output = getOutput();
        assertTrue(output.contains("입력 가능한 메시지의 최대 길이는 100자입니다."), "출력 결과: \n" + output);
    }

    @Test
    void testExample1() {
        HelloBiodome08.main(new String[]{"helloisthereanyproblem?"});
        String output = getOutput().trim();
        assertEquals("hello is there any problem?", output);
    }

    @Test
    void testExample2() {
        HelloBiodome08.main(new String[]{"weneedyourhelpplease"});
        String output = getOutput().trim();
        assertEquals("we need your help please.", output);
    }

    @Test
    void testExample3() {
        HelloBiodome08.main(new String[]{"whereisthenewtree?"});
        String output = getOutput().trim();
        assertEquals("where is the new tree?", output);
    }

    @Test
    void testExample4() {
        HelloBiodome08.main(new String[]{"canwebelieveinyou?"});
        String output = getOutput().trim();
        assertEquals("can we believein you?", output);
    }

    @Test
    void testRepetitiveWords() {
        HelloBiodome08.main(new String[]{"hellohelloisthereanyproblem?"});
        String output = getOutput().trim();
        assertEquals("hello hello is there any problem?", output);
    }

    @Test
    void testKoreanBonus1() {
        HelloBiodome08.main(new String[]{"안녕하세요새로운나무를발견했습니다"});
        String output = getOutput().trim();
        assertEquals("안녕하세요 새로운 나무를 발견했습니다.", output);
    }

    @Test
    void testKoreanBonus2() {
        HelloBiodome08.main(new String[]{"신속한지원감사합니다"});
        String output = getOutput().trim();
        assertEquals("신속한 지원 감사합니다.", output);
    }

    @Test
    void testKoreanBonus3() {
        HelloBiodome08.main(new String[]{"당신의신속한도움이필요합니다"});
        String output = getOutput().trim();
        assertEquals("당신의 신속한 도움이 필요합니다.", output);
    }
}
