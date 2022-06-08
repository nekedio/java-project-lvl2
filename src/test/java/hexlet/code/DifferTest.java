package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import org.junit.jupiter.api.Test;

public class DifferTest {
    @Test
    void testDiffGenerateYaml() throws Exception {
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
                "src/test/resources/file1.yml",
                "src/test/resources/file2.yml",
                "stylish"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateJson() throws Exception {
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
                "src/test/resources/file1.json",
                "src/test/resources/file2.json"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateToPlainFormat() throws Exception {
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
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "plain"
        );

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testDiffGenerateToJsonFormat() throws Exception {

        String expected = "{\"chars1\":{\"status\":\"unchanged\"},\"chars2\":"
                + "{\"status\":\"changed\"},\"checked\":{\"status\":\"changed\"},"
                + "\"default\":{\"status\":\"changed\"},\"id\":{\"status\":\"changed\"},"
                + "\"key1\":{\"status\":\"deleted\"},\"key2\":{\"status\":\"added\"},"
                + "\"numbers1\":{\"status\":\"unchanged\"},\"numbers2\":{\"status\":\"changed\"},"
                + "\"numbers3\":{\"status\":\"deleted\"},\"numbers4\":{\"status\":\"added\"},"
                + "\"obj1\":{\"status\":\"added\"},\"setting1\":{\"status\":\"changed\"},"
                + "\"setting2\":{\"status\":\"changed\"},\"setting3\":{\"status\":\"changed\"}}";

        String actual = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "json"
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
