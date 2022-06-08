package hexlet.code.formatters;

import hexlet.code.Pair;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Stylish {
    public static String toString(Map<String, Pair> map) {
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
}
