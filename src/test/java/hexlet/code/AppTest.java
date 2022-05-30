package hexlet.code;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import picocli.CommandLine;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testString() {
        String[] args = {"-h"};
        int exitCode = new CommandLine(new App()).execute(args);
        String expected = """
                Usage: gendiff [-hV] [-f=<format>] <filepath1> <filepath2>
                Compares two configuration files and shows a difference.
                      <filepath1>         path to first file
                      <filepath2>         path to second file
                  -f, --format=<format>   output format [default: stylish]
                  -h, --help              Show this help message and exit.
                  -V, --version           Print version information and exit.
                """;

        assertThat(exitCode).isEqualTo(0);
        assertThat(output.toString()).isEqualTo(expected);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
