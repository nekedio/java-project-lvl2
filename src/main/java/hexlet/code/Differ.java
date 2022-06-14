package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String extension1 = filepath1.substring(filepath1.indexOf('.') + 1);
        String extension2 = filepath2.substring(filepath2.indexOf('.') + 1);

        byte[] content1 = Files.readAllBytes((new File(filepath1)).toPath());
        byte[] content2 = Files.readAllBytes((new File(filepath2)).toPath());

        Map<String, Object> data1 = Parser.getContents(content1, extension1);
        Map<String, Object> data2 = Parser.getContents(content2, extension2);

        Map<String, Pair> map = Comparison.genDiff(data1, data2);

        return Formatter.getString(map, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return Differ.generate(filepath1, filepath2, "stylish");
    }
}
