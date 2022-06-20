package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class DifferTest {

    private final String expectedStylishFormat;
    private final String expectedJsonFormat;
    private final String expectedPlainFormat;

    public DifferTest() throws IOException {
        this.expectedStylishFormat = Files.readString(new File("src/test/resources/result.stylish").toPath());
        this.expectedJsonFormat = Files.readString(new File("src/test/resources/result.json").toPath());
        this.expectedPlainFormat = Files.readString(new File("src/test/resources/result.plain").toPath());
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testDiffGenerateDefaultFormat(String format) throws Exception {
        String filePath1 = "src/test/resources/file1." + format;
        String filePath2 = "src/test/resources/file2." + format;
        String actual = Differ.generate(filePath1, filePath2);

        assertThat(actual).isEqualTo(expectedStylishFormat);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testDiffGenerateStylishFormat(String format) throws Exception {
        String filePath1 = "src/test/resources/file1." + format;
        String filePath2 = "src/test/resources/file2." + format;
        String actual = Differ.generate(filePath1, filePath2, "stylish");

        assertThat(actual).isEqualTo(expectedStylishFormat);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testDiffGenerateJsonFormat(String format) throws Exception {
        String filePath1 = "src/test/resources/file1." + format;
        String filePath2 = "src/test/resources/file2." + format;
        String actual = Differ.generate(filePath1, filePath2, "json");

        assertThat(actual).isEqualTo(expectedJsonFormat);
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void testDiffGeneratePlainFormat(String format) throws Exception {
        String filePath1 = "src/test/resources/file1." + format;
        String filePath2 = "src/test/resources/file2." + format;
        String actual = Differ.generate(filePath1, filePath2, "plain");

        assertThat(actual).isEqualTo(expectedPlainFormat);
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
