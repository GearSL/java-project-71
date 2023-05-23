package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeSet;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String formatName) throws Exception {

        SortedMap<String, Object> firstFileMap = getParsedData(firstFilePath);
        SortedMap<String, Object> secondFileMap = getParsedData(secondFilePath);
        List<Diff> diff = createDiff(firstFileMap, secondFileMap);

        return Formatter.format(diff, formatName);
    }

    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        return generate(firstFilePath, secondFilePath, "stylish");
    }

    private static List<Diff> createDiff(Map<String, Object> firstFileMap, Map<String, Object> secondFileMap) {
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
        return diff;
    }

    private static String getFileContent(String filePath) throws Exception {
        Path normalizedPath = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(normalizedPath)) {
            throw new Exception("File '" + normalizedPath + "' does not exist");
        }
        return Files.readString(normalizedPath);
    }

    private static SortedMap<String, Object> getParsedData(String filePath) throws Exception {
        String content = getFileContent(filePath);
        int dotExtensionIdx = filePath.lastIndexOf(".");
        String extension = filePath.substring(dotExtensionIdx + 1);

        return Parser.parse(content, extension);
    }
}
