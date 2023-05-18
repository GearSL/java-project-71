package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;


public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String formatName) throws Exception {

        SortedMap<String, Object> firstFileMap = Parser.parse(firstFilePath);
        SortedMap<String, Object> secondFileMap = Parser.parse(secondFilePath);

        Set<String> keys = new TreeSet<>(firstFileMap.keySet());
        keys.addAll(secondFileMap.keySet());

        List<Diff> diff = new ArrayList<>();
        for (String key : keys) {
            boolean hasInFirst = firstFileMap.containsKey(key);
            boolean hasInSecond = secondFileMap.containsKey(key);

            Object valueFirst = firstFileMap.get(key);
            Object valueSecond = secondFileMap.get(key);

            if (hasInFirst && hasInSecond) {
                if (firstFileMap.get(key) != null) {
                    if (firstFileMap.get(key).equals(secondFileMap.get(key))) {
                        diff.add(new Diff(key, valueFirst, valueSecond, "unchanged"));
                    } else {
                        diff.add(new Diff(key, valueFirst, valueSecond, "updated"));
                    }
                } else if (firstFileMap.get(key) != secondFileMap.get(key)) {
                    diff.add(new Diff(key, valueFirst, valueSecond, "updated"));
                }
            } else if (hasInFirst) {
                diff.add(new Diff(key, valueFirst, valueSecond, "removed"));
            } else if (hasInSecond) {
                diff.add(new Diff(key, valueFirst, valueSecond, "added"));
            }
        }

        if (formatName.equals("plain")) {
            return Formatter.toPlain(diff);
        } else {
            return Formatter.toStylish(diff);
        }
    }
}
