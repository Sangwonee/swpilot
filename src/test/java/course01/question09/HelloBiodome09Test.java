package course01.question09;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloBiodome09Test {
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
        HelloBiodome09.main(new String[]{});
        String output = getOutput();
        assertTrue(output.contains("잘못된 입력입니다. 3~100 사이의 숫자를 입력하세요."), "출력 결과: \n" + output);
        assertTrue(output.contains("나무를 그릴 수 있도록 3~100까지의 숫자값을 입력해주세요"), "출력 결과: \n" + output);
    }

    @Test
    void testNonNumericInput() {
        HelloBiodome09.main(new String[]{"야옹"});
        String output = getOutput();
        assertTrue(output.contains("잘못된 입력입니다. 3~100 사이의 숫자를 입력하세요."), "출력 결과: \n" + output);
    }

    @Test
    void testOutOfBoundsLow() {
        HelloBiodome09.main(new String[]{"2"});
        String output = getOutput();
        assertTrue(output.contains("잘못된 입력입니다. 3~100 사이의 숫자를 입력하세요."), "출력 결과: \n" + output);
    }

    @Test
    void testOutOfBoundsHigh() {
        HelloBiodome09.main(new String[]{"101"});
        String output = getOutput();
        assertTrue(output.contains("잘못된 입력입니다. 3~100 사이의 숫자를 입력하세요."), "출력 결과: \n" + output);
    }

    @Test
    void testValidTreeHeight8() {
        HelloBiodome09.main(new String[]{"8"});
        String output = getOutput();
        
        // Expected rows
        assertTrue(output.contains("       *"), "출력 결과: \n" + output);
        assertTrue(output.contains("      ***"), "출력 결과: \n" + output);
        assertTrue(output.contains("     *****"), "출력 결과: \n" + output);
        assertTrue(output.contains("    *******"), "출력 결과: \n" + output);
        assertTrue(output.contains("   *********"), "출력 결과: \n" + output);
        assertTrue(output.contains("  ***********"), "출력 결과: \n" + output);
        assertTrue(output.contains(" *************"), "출력 결과: \n" + output);
        assertTrue(output.contains("***************"), "출력 결과: \n" + output);
        assertTrue(output.contains("       |"), "출력 결과: \n" + output);
    }

    @Test
    void testBonusTreeHeight5WithCenterFill() {
        HelloBiodome09.main(new String[]{"5", "#"});
        String output = getOutput();
        
        // Expected rows with center fill
        assertTrue(output.contains("    #"), "출력 결과: \n" + output);
        assertTrue(output.contains("   *#*"), "출력 결과: \n" + output);
        assertTrue(output.contains("  **#**"), "출력 결과: \n" + output);
        assertTrue(output.contains(" ***#***"), "출력 결과: \n" + output);
        assertTrue(output.contains("****#****"), "출력 결과: \n" + output);
        assertTrue(output.contains("    |"), "출력 결과: \n" + output);
    }
}
