package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        String extension1 = filepath1.substring(filepath1.indexOf('.') + 1);
        String extension2 = filepath2.substring(filepath2.indexOf('.') + 1);

        Map<String, Object> content1 = Parser.getContents(filepath1, extension1);
        Map<String, Object> content2 = Parser.getContents(filepath2, extension2);

        Map<String, Pair> map = genDiff(content1, content2);

        return Formatter.getString(map, format);
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
}
