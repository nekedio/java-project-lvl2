package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

public class DifferTest {
    @Test
    void testDiffGenerateParsingYaml() throws Exception {

        String expected = Files.readString(new File("src/test/resources/result.stylish").toPath());

        String actual = Differ.generate(
                "src/test/resources/file1.yml",
                "src/test/resources/file2.yml"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateParsingJson() throws Exception {
        String expected = Files.readString(new File("src/test/resources/result.stylish").toPath());

        String actual = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateToPlainFormat() throws Exception {
        String expected = Files.readString(new File("src/test/resources/result.plain").toPath());

        String actual = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "plain"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateToStylishFormat() throws Exception {
        String expected = Files.readString(new File("src/test/resources/result.stylish").toPath());

        String actual = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "stylish"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateToJsonFormat() throws Exception {
        String expected = Files.readString(new File("src/test/resources/result.json").toPath());

        String actual = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "json"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateFormatException() {
        Exception exception;
        exception = assertThrows(Exception.class, () -> Differ.generate(
            "src/test/resources/file1.yml",
                "src/test/resources/file2.yml",
                "er"
        ));

        String expectedMessage = "The \"er\" format is incorrect";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testDiffGenerateParsingException() {
        Exception exception;
        exception = assertThrows(Exception.class, () -> Differ.generate(
                "src/test/resources/file1.yml",
                "src/test/resources/errorExtension.jso",
                "stylish"
        ));

        String expectedMessage = "Unknown format: \"jso\"";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
