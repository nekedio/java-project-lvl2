package hexlet.code;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String extension1 = getExtension(filepath1);
        String extension2 = getExtension(filepath2);

        String content1 = Files.readString((new File(filepath1)).toPath());
        String content2 = Files.readString((new File(filepath2)).toPath());

        Map<String, Object> data1 = Parser.getContents(content1, extension1);
        Map<String, Object> data2 = Parser.getContents(content2, extension2);

        Map<String, Pair> diff = Comparison.genDiff(data1, data2);

        return Formatter.getString(diff, format);
    }

    private static String getExtension(String filepath) throws Exception {
        if (filepath.indexOf('.') < 0) {
            throw new Exception("Unknown format file \"" + filepath + "\"");
        }

        return filepath.substring(filepath.indexOf('.') + 1);
    }


    public static String generate(String filepath1, String filepath2) throws Exception {
        return Differ.generate(filepath1, filepath2, "stylish");
    }


}
