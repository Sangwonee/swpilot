package course01.question07;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloBiodome07Test {
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
        HelloBiodome07.main(new String[]{});
        String output = getOutput();
        assertTrue(output.contains("염기서열이 입력되지 않았습니다. 다시 입력해주세요"), "출력 결과: \n" + output);
    }

    @Test
    void testSpacesOnlyInput() {
        HelloBiodome07.main(new String[]{"   "});
        String output = getOutput();
        assertTrue(output.contains("염기서열이 입력되지 않았습니다. 다시 입력해주세요"), "출력 결과: \n" + output);
    }

    @Test
    void testInvalidCharacters() {
        HelloBiodome07.main(new String[]{"KKKYYYHHWWMMM"});
        String output = getOutput();
        assertTrue(output.contains("염기서열은 C, J, H, E, Y 다섯가지로만 입력됩니다. 확인하고 다시 입력해주세요"), "출력 결과: \n" + output);
    }

    @Test
    void testMainExample1() {
        HelloBiodome07.main(new String[]{"JJJCCCEEHHYYYEEEEE"});
        String output = getOutput();
        assertTrue(output.contains("J3C3E2H2Y3E5"), "출력 결과: \n" + output);
    }

    @Test
    void testMainExample2() {
        HelloBiodome07.main(new String[]{"CCCCjjjYYEEHHCCYYY"});
        String output = getOutput();
        assertTrue(output.contains("C4J3Y2E2H2C2Y3"), "출력 결과: \n" + output);
    }

    @Test
    void testSpaceRemovalAndBonusExample1() {
        HelloBiodome07.main(new String[]{"EEE", "EECCCCYYY"});
        String output = getOutput();
        // Main task output (first line)
        assertTrue(output.contains("E5C4Y3"), "출력 결과: \n" + output);
        // Bonus task output (second line)
        assertTrue(output.contains("E3 E2C4Y3"), "출력 결과: \n" + output);
    }

    @Test
    void testBonusExample2() {
        HelloBiodome07.main(new String[]{"CCCCHHHH", "JJ", "EEEEJJ"});
        String output = getOutput();
        // Main task output (first line)
        assertTrue(output.contains("C4H4J2E4J2"), "출력 결과: \n" + output);
        // Bonus task output (second line)
        assertTrue(output.contains("C4H4 J2 E4J2"), "출력 결과: \n" + output);
    }

    @Test
    void testEvaluationGuideExample() {
        HelloBiodome07.main(new String[]{"CCJJhhJJJJJ", "EEEEEHHHH", "jjjCCCCC"});
        String output = getOutput();
        // Main task output: C2J2H2J5E5H4J3C5
        assertTrue(output.contains("C2J2H2J5E5H4J3C5"), "출력 결과: \n" + output);
    }

    @Test
    void testCompressHelper() {
        assertEquals("A3B2", HelloBiodome07.compress("AAABB"));
        assertEquals("", HelloBiodome07.compress(""));
    }

    @Test
    void testCompressBonusHelper() {
        assertEquals("A3 B2", HelloBiodome07.compressBonus("AAA BB"));
        assertEquals("A3 B2", HelloBiodome07.compressBonus("AAA   BB")); // multiple spaces normalized
    }
}
