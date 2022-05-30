package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Pair> result = genDiff(data1, data2);
        return Differ.toString(result);
    }

    public static Map<String, Pair> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys1 = new TreeSet<>(data1.keySet());
        Set<String> keys2 = new TreeSet<>(data2.keySet());

        Set<String> keysAll = new TreeSet<>();
        keysAll.addAll(keys1);
        keysAll.addAll(keys2);

        Map<String, Pair> result = new LinkedHashMap<>();

        for (String key: keysAll) {
            if (!keys2.contains(key)) {
                result.put(key, new Pair(data1.get(key), null, "deleted"));
            } else if (!keys1.contains(key)) {
                result.put(key, new Pair(data2.get(key), null, "added"));
            } else if (!data2.get(key).equals(data1.get(key))) {
                result.put(key, new Pair(data2.get(key), data1.get(key), "changed"));
            } else if (data2.get(key).equals(data1.get(key))) {
                result.put(key, new Pair(data2.get(key), null, "unchanged"));
            }
        }

        return result;
    }

    public static String toString(Map<String, Pair> map) {
        Set<String> keys = new TreeSet<>(map.keySet());

        StringBuilder result = new StringBuilder();

        result.append("{\n");
        for (String key : keys) {
            String status = map.get(key).getStatus();
            String value = map.get(key).toStringValue();
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
                    result.append(map.get(key).toStringOldValue());
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
