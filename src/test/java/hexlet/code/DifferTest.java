package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.Test;

public class DifferTest {
    @Test
    void testDiffGenerateJson() throws Exception {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;

        String actual = Differ.generate(
                "/home/nekedio/he/app/src/test/resources/file1.json",
                "/home/nekedio/he/app/src/test/resources/file2.json"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateYaml() throws Exception {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;

        String actual = Differ.generate(
                "/home/nekedio/he/app/src/test/resources/file1.yml",
                "/home/nekedio/he/app/src/test/resources/file2.yml"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateException() throws Exception {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            Differ.generate(
                "/home/nekedio/he/app/src/test/resources/file1.yml",
                "/home/nekedio/he/app/src/test/resources/errorExtension.jso"
            );
        });

        String expectedMessage = "\"jso\" invalid file extension";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
