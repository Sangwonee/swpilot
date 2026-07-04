package course01.question05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloBiodome05Test {
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
    void testFindGH() {
        int[] gh = HelloBiodome05.findGH();
        assertArrayEquals(new int[]{1, 2}, gh);
    }

    @Test
    void testEvaluateFormula() {
        int result = HelloBiodome05.evaluateFormula(1, 2);
        assertEquals(42, result);
    }

    @Test
    void testEvaluateBonusFormula() {
        int result = HelloBiodome05.evaluateBonusFormula(1, 2);
        assertEquals(42, result);
    }

    @Test
    void testMainOutput() {
        HelloBiodome05.main(new String[]{});
        String output = getOutput();
        assertTrue(output.contains("g = 1, h = 2"), "출력 결과: \n" + output);
        assertTrue(output.contains("세 번째 수식 계산 결과 = 42"), "출력 결과: \n" + output);
        assertTrue(output.contains("보너스 수식 계산 결과 = 42"), "출력 결과: \n" + output);
    }
}
