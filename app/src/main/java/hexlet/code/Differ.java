package hexlet.code;

import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.SortedMap;
import java.util.Set;


public class Differ {
    public static LinkedHashMap<String, String> generate(String firstFilePath, String secondFilePath) throws Exception {

        SortedMap<String, Object> firstFileMap = Parser.parse(firstFilePath);
        SortedMap<String, Object> secondFileMap = Parser.parse(secondFilePath);

        Set<String> keys = new TreeSet<>(firstFileMap.keySet());
        keys.addAll(secondFileMap.keySet());

        LinkedHashMap<String, String> diff = new LinkedHashMap<>();
        for (String key : keys) {
            boolean hasInFirst = firstFileMap.containsKey(key);
            boolean hasInSecond = secondFileMap.containsKey(key);

            if (hasInFirst && hasInSecond) {
                if (firstFileMap.get(key) != null) {
                    if (firstFileMap.get(key).equals(secondFileMap.get(key))) {
                        diff.put(key + ": " + firstFileMap.get(key), "unchanged");
                    } else {
                        diff.put(key + ": " + firstFileMap.get(key), "deleted");
                        diff.put(key + ": " + secondFileMap.get(key), "added");
                    }
                } else if (firstFileMap.get(key) != secondFileMap.get(key)) {
                    diff.put(key + ": " + firstFileMap.get(key), "deleted");
                    diff.put(key + ": " + secondFileMap.get(key), "added");
                }
            } else if (hasInFirst) {
                diff.put(key + ": " + firstFileMap.get(key), "deleted");
            } else if (hasInSecond) {
                diff.put(key + ": " + secondFileMap.get(key), "added");
            }
        }
        return diff;
    }
}
