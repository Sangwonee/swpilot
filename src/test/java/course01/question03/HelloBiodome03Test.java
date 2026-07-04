package course01.question03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloBiodome03Test {
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
        HelloBiodome03.main(new String[]{"25.5", "65.0", "21.0"});
        String output = getOutput();
        assertTrue(output.contains("생명지수 H = 9.0964172442"), "출력 결과: \n" + output);
        assertTrue(output.contains("생명지수 H (반올림) = 9.1"), "출력 결과: \n" + output);
    }

    @Test
    void testBonusInput() {
        HelloBiodome03.main(new String[]{"23.1", "60.0", "23.0"});
        String output = getOutput();
        assertTrue(output.contains("생명지수 H = 9.23"), "출력 결과: \n" + output);
        assertTrue(output.contains("생명지수 H (반올림) = 9.23"), "출력 결과: \n" + output);
    }

    @Test
    void testInvalidArgsLengthLess() {
        HelloBiodome03.main(new String[]{"25.5", "65.0"});
        String output = getOutput();
        assertTrue(output.contains("올바른 값이 입력되지 않았습니다. [온도][습도][산소농도] 순서 대로 숫자 값을 입력해주세요"), "출력 결과: \n" + output);
    }

    @Test
    void testNonNumericInput() {
        HelloBiodome03.main(new String[]{"3.2", "55.1", "aa"});
        String output = getOutput();
        assertTrue(output.contains("올바른 값이 입력되지 않았습니다. [온도][습도][산소농도] 순서 대로 숫자 값을 입력해주세요"), "출력 결과: \n" + output);
    }
}
