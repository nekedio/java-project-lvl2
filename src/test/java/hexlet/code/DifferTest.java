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
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "stylish"
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
                "src/test/resources/file1.yml",
                "src/test/resources/file2.yml",
                "stylish"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateRecursionStruct() throws Exception {
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }
                """;

        String actual = Differ.generate(
                "src/test/resources/fileRec1.json",
                "src/test/resources/fileRec2.json",
                "stylish"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateRecursionStructPlainFormat() throws Exception {
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;

        String actual = Differ.generate(
                "src/test/resources/fileRec1.json",
                "src/test/resources/fileRec2.json",
                "plain"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateException() {
        Exception exception;
        exception = assertThrows(RuntimeException.class, () -> Differ.generate(
            "src/test/resources/file1.yml",
            "src/test/resources/errorExtension.jso",
                "stylish"
        ));

        String expectedMessage = "\"jso\" invalid file extension";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}
