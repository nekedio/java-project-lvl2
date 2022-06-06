package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        Map<String, Pair> map = genDiff(
                Parser.getContents(filepath1, filepath1.substring(filepath1.indexOf('.') + 1)),
                Parser.getContents(filepath2, filepath2.substring(filepath2.indexOf('.') + 1))
        );

        String result;
        switch (format) {
            case ("stylish"):
                result = Differ.toStringStylishFormat(map);
                break;
            case ("plain"):
                result = Differ.toStringPlainFormat(map);
                break;
            case ("json"):
                result = Differ.toStringJsonFormat(map);
                break;
            default:
                throw new RuntimeException("The \"" + format + "\" format is incorrect");
        }

        return result;
    }

    public static Map<String, Pair> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        Set<String> keys1 = new TreeSet<>(data1.keySet());
        Set<String> keys2 = new TreeSet<>(data2.keySet());

        Set<String> keysAll = new TreeSet<>();
        keysAll.addAll(keys1);
        keysAll.addAll(keys2);

        Map<String, Pair> result = new LinkedHashMap<>();

        for (String key: keysAll) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!keys2.contains(key)) {
                result.put(key, new Pair(data1.get(key), null, "deleted"));
            } else if (!keys1.contains(key)) {
                result.put(key, new Pair(data2.get(key), null, "added"));
            } else if (!comparison(value1, value2)) {
                result.put(key, new Pair(data2.get(key), data1.get(key), "changed"));
            } else {
                result.put(key, new Pair(data2.get(key), null, "unchanged"));
            }
        }

        return result;
    }

    public static boolean comparison(Object value1, Object value2) {
        if ((value1 == null) && (value2 == null)) {
            return true;
        }

        if ((value1 == null) || (value2 == null)) {
            return false;
        }

        return value1.equals(value2);
    }

    public static String toStringStylishFormat(Map<String, Pair> map) {
        Set<String> keys = new TreeSet<>(map.keySet());

        StringBuilder result = new StringBuilder();

        result.append("{\n");
        for (String key : keys) {
            String status = map.get(key).getStatus();
            String value = map.get(key).toStringValue();
            String oldValue = map.get(key).toStringOldValue();
            switch (status) {
                case ("deleted") -> {
                    result.append("  - ");
                    result.append(key);
                    result.append(": ");
                    result.append(value);
                }
                case ("added") -> {
                    result.append("  + ");
                    result.append(key);
                    result.append(": ");
                    result.append(value);
                }
                case ("changed") -> {
                    result.append("  - ");
                    result.append(key);
                    result.append(": ");
                    result.append(oldValue);
                    result.append("\n");
                    result.append("  + ");
                    result.append(key);
                    result.append(": ");
                    result.append(value);
                }
                case ("unchanged") -> {
                    result.append("    ");
                    result.append(key);
                    result.append(": ");
                    result.append(value);
                }
                default -> throw new RuntimeException("Error! Status \"" + status + "\" not detected.");
            }
            result.append("\n");
        }
        result.append("}\n");

        return result.toString();
    }

    public static String toStringPlainFormat(Map<String, Pair> map) {
        Set<String> keys = new TreeSet<>(map.keySet());

        StringBuilder result = new StringBuilder();

        for (String key : keys) {
            String status = map.get(key).getStatus();
            String keyFormat = "'" + key + "'";
//            String value = map.get(key).toStringValue();
            String value = map.get(key).toStringValuePlainFormat();
            String oldValue = map.get(key).toStringOldValuePlainFormat();
            System.out.println();
            switch (status) {
                case ("deleted") -> {
                    result.append("Property ");
                    result.append(keyFormat);
                    result.append(" was removed");
                    result.append("\n");
                }
                case ("added") -> {
                    result.append("Property ");
                    result.append(keyFormat);
                    result.append(" was added with value: ");
                    result.append(value);
                    result.append("\n");
                }
                case ("changed") -> {
                    result.append("Property ");
                    result.append(keyFormat);
                    result.append(" was updated. From ");
                    result.append(oldValue);
                    result.append(" to ");
                    result.append(value);
                    result.append("\n");
                }
                case ("unchanged") -> { }
                default -> throw new RuntimeException("Error! Status \"" + status + "\" not detected.");
            }
        }
//        result.append("\n");

        return result.toString();
    }

    public static String toStringJsonFormat(Map<String, Pair> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(map);
        return result;
    }
}
