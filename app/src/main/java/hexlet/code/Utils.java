package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Utils {
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
            } else if (!isEqual(firstValue, secondValue)) {
                diff.add(new Diff(key, firstValue, secondValue, "updated"));
            } else {
                diff.add(new Diff(key, firstValue, secondValue, "unchanged"));
            }
        }
        return diff;
    }

    private static boolean isEqual(Object firstValue, Object secondValue) {
        if (firstValue != null && secondValue != null) {
            return firstValue.equals(secondValue);
        } else {
            return firstValue == null && secondValue == null;
        }
    }
}
