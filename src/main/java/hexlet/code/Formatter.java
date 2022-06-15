package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String getString(Map<String, Pair> map, String format) throws Exception {
        return switch (format) {
            case ("stylish") -> Stylish.toString(map);
            case ("plain") -> Plain.toString(map);
            case ("json") -> Json.toString(map);
            default -> throw new Exception("The \"" + format + "\" format is incorrect");
        };
    }
}
