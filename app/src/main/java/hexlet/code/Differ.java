package hexlet.code;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath) throws Exception {

        SortedMap<String, Object> firstFileMap = Parser.parse(firstFilePath);
        SortedMap<String, Object> secondFileMap = Parser.parse(secondFilePath);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        Set<String> keys = new TreeSet<>(firstFileMap.keySet());
        keys.addAll(secondFileMap.keySet());

        for (String key : keys) {
            Object value1 = firstFileMap.get(key);
            Object value2 = secondFileMap.get(key);

            if (value1 == null) {
                stringBuilder.append(" + ").append(key).append(": ").append(secondFileMap.get(key)).append("\n");
            } else if (value2 == null) {
                stringBuilder.append(" - ").append(key).append(": ").append(firstFileMap.get(key)).append("\n");
            } else if (!value1.equals(value2)) {
                stringBuilder.append(" - ").append(key).append(": ").append(firstFileMap.get(key)).append("\n");
                stringBuilder.append(" + ").append(key).append(": ").append(secondFileMap.get(key)).append("\n");
            } else {
                stringBuilder.append("   ").append(key).append(": ").append(firstFileMap.get(key)).append("\n");
            }
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
