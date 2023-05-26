package hexlet.code;

import java.util.*;

public class DiffCalculator {
    public static List<Diff> createDiff(Map<String, Object> firstFileMap, Map<String, Object> secondFileMap) {
        Set<String> keys = new TreeSet<>(firstFileMap.keySet());
        keys.addAll(secondFileMap.keySet());

        List<Diff> diff = new ArrayList<>();
        for (String key : keys) {
            Object firstValue = firstFileMap.get(key);
            Object secondValue = secondFileMap.get(key);

            if (!secondFileMap.containsKey(key)) {
                diff.add(new Diff(key, firstValue, secondValue, "removed"));
            } else if (!firstFileMap.containsKey(key)) {
                diff.add(new Diff(key, firstValue, secondValue, "added"));
            } else if (!Objects.equals(firstValue, secondValue)) {
                diff.add(new Diff(key, firstValue, secondValue, "updated"));
            } else {
                diff.add(new Diff(key, firstValue, secondValue, "unchanged"));
            }
        }
        return diff;
    }
}
