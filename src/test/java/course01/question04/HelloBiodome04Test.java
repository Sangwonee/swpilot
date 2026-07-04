package course01.question04;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloBiodome04Test {
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
    void testNormalInputStable() {
        HelloBiodome04.main(new String[] { "25.5", "55.0", "21.0" });
        String output = getOutput();
        assertTrue(output.contains("생명의 나무는 안정적인 상태입니다 :)"), "출력 결과: \n" + output);
        assertTrue(output.contains("생명의 나무는 안정적인 상태입니다. 건강지수는 9.63입니다."), "출력 결과: \n" + output);
    }

    @Test
    void testBonusInputStable() {
        HelloBiodome04.main(new String[] { "23.1", "50.0", "23.0" });
        String output = getOutput();
        assertTrue(output.contains("생명의 나무는 안정적인 상태입니다. 건강지수는 8.98입니다."), "출력 결과: \n" + output);
    }

    @Test
    void testUnstableHumidity() {
        HelloBiodome04.main(new String[] { "25.5", "65.0", "21.0" });
        String output = getOutput();
        assertTrue(output.contains("습도값이 정상 범위를 벗어났습니다. 확인이 필요합니다."), "출력 결과: \n" + output);
    }

    @Test
    void testUnstableTemperature() {
        HelloBiodome04.main(new String[] { "-27.0", "55.0", "20.0" });
        String output = getOutput();
        assertTrue(output.contains("온도값이 정상 범위를 벗어났습니다. 확인이 필요합니다."), "출력 결과: \n" + output);
    }

    @Test
    void testMissingArgs() {
        HelloBiodome04.main(new String[] { "3.2", "55.1" });
        String output = getOutput();
        assertTrue(output.contains("입력되지 않은 값이 있습니다. [온도][습도][산소농도] 순서대로 숫자 값을 입력해주세요"), "출력 결과: \n" + output);
    }

    @Test
    void testNonNumericInput() {
        HelloBiodome04.main(new String[] { "3.2", "55.1", "aa" });
        String output = getOutput();
        assertTrue(output.contains("입력된 값이 올바르지 않습니다. [온도][습도][산소농도] 순서대로 숫자 값을 입력해주세요"), "출력 결과: \n" + output);
    }
}
