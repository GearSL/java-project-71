package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedMap;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String formatName) throws Exception {

        SortedMap<String, Object> firstFileMap = getParsedData(firstFilePath);
        SortedMap<String, Object> secondFileMap = getParsedData(secondFilePath);
        List<Diff> diff = DiffCalculator.createDiff(firstFileMap, secondFileMap);

        return Formatter.format(diff, formatName);
    }

    public static String generate(String firstFilePath, String secondFilePath) throws Exception {
        return generate(firstFilePath, secondFilePath, "stylish");
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
