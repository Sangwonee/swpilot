package course01.question06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelloBiodome06Test {
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
    void testMissingArguments() {
        HelloBiodome06.main(new String[]{"askkk"});
        String output = getOutput();
        assertTrue(output.contains("두 개의 유전자 코드를 입력해주세요."), "출력 결과: \n" + output);
    }

    @Test
    void testEqualGenes() {
        HelloBiodome06.main(new String[]{"asfd215j", "asfd215j"});
        String output = getOutput();
        assertTrue(output.contains("동일한 유전자 코드입니다."), "출력 결과: \n" + output);
        assertFalse(output.contains("부분적으로 포함됩니다."), "출력 결과: \n" + output);
    }

    @Test
    void testUnmatchingGenes() {
        HelloBiodome06.main(new String[]{"sww29", "spp29"});
        String output = getOutput();
        assertTrue(output.contains("일치하지 않습니다."), "출력 결과: \n" + output);
        assertTrue(output.contains("포함되지 않습니다."), "출력 결과: \n" + output);
    }

    @Test
    void testPartialInclusion() {
        HelloBiodome06.main(new String[]{"dna123", "dna123456"});
        String output = getOutput();
        assertTrue(output.contains("일치하지 않습니다."), "출력 결과: \n" + output);
        assertTrue(output.contains("부분적으로 포함됩니다."), "출력 결과: \n" + output);
    }

    @Test
    void testNoInclusion() {
        HelloBiodome06.main(new String[]{"abc456", "def123"});
        String output = getOutput();
        assertTrue(output.contains("일치하지 않습니다."), "출력 결과: \n" + output);
        assertTrue(output.contains("포함되지 않습니다."), "출력 결과: \n" + output);
    }

    @Test
    void testInvalidValidation() {
        HelloBiodome06.main(new String[]{"abc", "def123"});
        String output = getOutput();
        assertTrue(output.contains("유전자 코드가 유효하지 않습니다. 5~20자의 숫자와 영어 소문자여야 합니다."), "출력 결과: \n" + output);
    }

    @Test
    void testIsEqualHelper() {
        assertTrue(HelloBiodome06.isEqual("hello", "hello"));
        assertFalse(HelloBiodome06.isEqual("hello", "world"));
        assertFalse(HelloBiodome06.isEqual("hello", "helloo"));
    }

    @Test
    void testContainsHelper() {
        assertTrue(HelloBiodome06.contains("world", "helloworld"));
        assertTrue(HelloBiodome06.contains("hello", "helloworld"));
        assertFalse(HelloBiodome06.contains("worldd", "helloworld"));
    }
}
