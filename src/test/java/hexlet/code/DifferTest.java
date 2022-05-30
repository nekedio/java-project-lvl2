package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;


public class DifferTest {
    @Test
    void testDiffGenerate() {

        Map<String, Object> map1 = new HashMap<>(
                Map.of("host", "hexlet.io",
                        "timeout", Integer.valueOf("50"),
                        "proxy", "123.234.53.22",
                        "follow", false
                )
        );

        Map<String, Object> map2 = new HashMap<>(
                Map.of(
                        "timeout", Integer.valueOf("20"),
                        "verbose", true,
                        "host", "hexlet.io"
                )
        );

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

        String actual = Differ.generate(map1, map2);

        assertThat(actual).isEqualTo(expected);
    }
}
