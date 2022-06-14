package hexlet.code;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import picocli.CommandLine;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;

public class AppTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testHelp() {
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

    @Test
    public void testGenDiffStylishFormat() throws IOException {
        String[] args = {"-f=stylish", "src/test/resources/file1.json", "src/test/resources/file2.json"};
        int exitCode = new CommandLine(new App()).execute(args);
        String expected = Files.readString(new File("src/test/resources/result.stylish").toPath()) + "\n";

        assertThat(exitCode).isEqualTo(0);
        assertThat(output.toString()).isEqualTo(expected);
    }

    @Test
    public void testGenDiffPlainFormat() throws IOException {
        String[] args = {"-f=plain", "src/test/resources/file1.json", "src/test/resources/file2.json"};
        int exitCode = new CommandLine(new App()).execute(args);
        String expected = Files.readString(new File("src/test/resources/result.plain").toPath()) + "\n";

        assertThat(exitCode).isEqualTo(0);
        assertThat(output.toString()).isEqualTo(expected);
    }

    @Test
    public void testGenDiffDefaultFormat() throws IOException {
        String[] args = {"src/test/resources/file1.json", "src/test/resources/file2.json"};
        int exitCode = new CommandLine(new App()).execute(args);
        String expected = Files.readString(new File("src/test/resources/result.stylish").toPath()) + "\n";

        assertThat(exitCode).isEqualTo(0);
        assertThat(output.toString()).isEqualTo(expected);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
