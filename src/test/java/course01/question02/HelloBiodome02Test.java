package course01.question02;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloBiodome02Test {
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
    void testNormalInput() {
        HelloBiodome02.main(new String[]{"10000", "20000", "30000"});
        String output = getOutput();
        assertTrue(output.contains("총 에너지 사용량은 60000입니다."), "출력 결과: \n" + output);
        assertTrue(output.contains("태양광 16.66"), "태양광 비율 검사 실패. 출력 결과: \n" + output);
        assertTrue(output.contains("풍력 33.33"), "풍력 비율 검사 실패. 출력 결과: \n" + output);
        assertTrue(output.contains("지열 50.0"), "지열 비율 검사 실패. 출력 결과: \n" + output);
    }

    @Test
    void testInvalidArgsLengthLess() {
        HelloBiodome02.main(new String[]{"10000", "20000"});
        String output = getOutput();
        assertTrue(output.contains("세 가지 에너지 생산량을 입력 받아주세요."), "출력 결과: \n" + output);
    }

    @Test
    void testInvalidArgsLengthMore() {
        HelloBiodome02.main(new String[]{"10000", "20000", "30000", "40000"});
        String output = getOutput();
        assertTrue(output.contains("세 가지 에너지 생산량을 입력 받아주세요."), "출력 결과: \n" + output);
    }

    @Test
    void testNegativeInput() {
        HelloBiodome02.main(new String[]{"-100", "20000", "30000"});
        String output = getOutput();
        assertTrue(output.contains("에너지 생산량은 음수일 수 없습니다."), "출력 결과: \n" + output);
    }

    @Test
    void testOverflowInput() {
        HelloBiodome02.main(new String[]{"30001", "20000", "30000"});
        String output = getOutput();
        assertTrue(output.contains("에너지 생산량은 30,000을 넘을 수 없습니다."), "출력 결과: \n" + output);
    }

    @Test
    void testNonNumericInput() {
        HelloBiodome02.main(new String[]{"abc", "20000", "30000"});
        String output = getOutput();
        assertTrue(output.contains("에너지 생산량은 정수여야 합니다."), "출력 결과: \n" + output);
    }
}
