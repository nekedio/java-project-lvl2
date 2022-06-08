package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String getString(Map<String, Pair> map, String format) throws JsonProcessingException {
        return switch (format) {
            case ("stylish") -> Stylish.toString(map);
            case ("plain") -> Plain.toString(map);
            case ("json") -> Json.toString(map);
            default -> throw new RuntimeException("The \"" + format + "\" format is incorrect");
        };
    }
}
