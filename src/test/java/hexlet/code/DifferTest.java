package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class DifferTest {

    private static String expectedStylishFormat;
    private static String expectedJsonFormat;
    private static String expectedPlainFormat;

    @BeforeAll
    public static void setExpected() throws IOException {
        expectedStylishFormat = Files.readString(new File("src/test/resources/result.stylish").toPath());
        expectedJsonFormat = Files.readString(new File("src/test/resources/result.json").toPath());
        expectedPlainFormat = Files.readString(new File("src/test/resources/result.plain").toPath());
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testDiffGenerate(String format) throws Exception {
        String filePath1 = "src/test/resources/file1." + format;
        String filePath2 = "src/test/resources/file2." + format;

        String actualDefault = Differ.generate(filePath1, filePath2);
        String actualStylish = Differ.generate(filePath1, filePath2, "stylish");
        String actualJson = Differ.generate(filePath1, filePath2, "json");
        String actualPlain = Differ.generate(filePath1, filePath2, "plain");

        assertThat(actualDefault).isEqualTo(expectedStylishFormat);
        assertThat(actualStylish).isEqualTo(expectedStylishFormat);
        assertThat(actualJson).isEqualTo(expectedJsonFormat);
        assertThat(actualPlain).isEqualTo(expectedPlainFormat);
    }

    @Test
    public void testDiffGenerateFormatException() {
        Exception exception;
        exception = assertThrows(Exception.class, () -> Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "er"
        ));

        String expectedMessage = "The \"er\" format is incorrect";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    public void testDiffGenerateParsingException() {
        Exception exception;
        exception = assertThrows(Exception.class, () -> Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/errorExtension.jso",
                "stylish"
        ));

        String expectedMessage = "Unknown format: \"jso\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
