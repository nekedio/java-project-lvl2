package hexlet.code.formatters;

import hexlet.code.Pair;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Plain {
    public static String toString(Map<String, Pair> map) throws Exception {
        Set<String> keys = new TreeSet<>(map.keySet());

        StringBuilder result = new StringBuilder();

        for (String key : keys) {
            String status = map.get(key).getStatus();
            String keyFormat = "'" + key + "'";
            String value = map.get(key).toStringValuePlainFormat();
            String oldValue = map.get(key).toStringOldValuePlainFormat();
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
                default -> throw new Exception("Error! Status \"" + status + "\" not detected.");
            }
        }

        return result.toString().trim();
    }

    public static String getValuePlainFormat(Object value) {

        if (value == null) {
            return null;
        }

        if (value instanceof ArrayList<?> || value instanceof LinkedHashMap<?, ?>) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return String.valueOf(value);
    }
}
