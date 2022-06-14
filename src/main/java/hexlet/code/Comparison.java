package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Comparison {
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
            } else if (!getComparison(value1, value2)) {
                result.put(key, new Pair(data2.get(key), data1.get(key), "changed"));
            } else {
                result.put(key, new Pair(data2.get(key), null, "unchanged"));
            }
        }

        return result;
    }

    public static boolean getComparison(Object value1, Object value2) {
        if ((value1 == null) && (value2 == null)) {
            return true;
        }

        if ((value1 == null) || (value2 == null)) {
            return false;
        }

        return value1.equals(value2);
    }
}
