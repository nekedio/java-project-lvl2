package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private File filepath1;

    @Parameters(index = "1", description = "path to second file")
    private File filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {
        byte[] fileContents1 = Files.readAllBytes(filepath1.toPath());
        byte[] fileContents2 = Files.readAllBytes(filepath2.toPath());

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map1 = mapper.readValue(fileContents1, new TypeReference<>() { });

        Map<String, Object> map2 = mapper.readValue(fileContents2, new TypeReference<>() { });

        String result = Differ.generate(map1, map2);
        System.out.println(result);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
