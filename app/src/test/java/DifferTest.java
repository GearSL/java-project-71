import hexlet.code.Differ;
import hexlet.code.Formatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Objects;

public class DifferTest {
    private static String firstJsonFilePath;
    private static String secondJsonFilePath;
    private static String firstYamlFilePath;
    private static String secondYamlFilePath;



    @BeforeAll
    public static void init() {
        ClassLoader classLoader = DifferTest.class.getClassLoader();
        File jsonFile1 = new File(Objects.requireNonNull(classLoader.getResource("file_1.json")).getFile());
        File jsonFile2 = new File(Objects.requireNonNull(classLoader.getResource("file_2.json")).getFile());
        File yamlFile1 = new File(Objects.requireNonNull(classLoader.getResource("file_1.yml")).getFile());
        File yamlFile2 = new File(Objects.requireNonNull(classLoader.getResource("file_2.yml")).getFile());

        firstJsonFilePath = Path.of(jsonFile1.getAbsolutePath()).toString();
        secondJsonFilePath = Path.of(jsonFile2.getAbsolutePath()).toString();
        firstYamlFilePath = Path.of(yamlFile1.getAbsolutePath()).toString();
        secondYamlFilePath = Path.of(yamlFile2.getAbsolutePath()).toString();
    }

    @Test
    public void differJsonTest() throws Exception {
        String expect = """
                {
                   chars1: [a, b, c]
                 - chars2: [d, e, f]
                 + chars2: false
                 - checked: false
                 + checked: true
                 - default: null
                 + default: [value1, value2]
                 - id: 45
                 + id: null
                 - key1: value1
                 + key2: value2
                   numbers1: [1, 2, 3, 4]
                 - numbers2: [2, 3, 4, 5]
                 + numbers2: [22, 33, 44, 55]
                 - numbers3: [3, 4, 5]
                 + numbers4: [4, 5, 6]
                 + obj1: {nestedKey=value, isNested=true}
                 - setting1: Some value
                 + setting1: Another value
                 - setting2: 200
                 + setting2: 300
                 - setting3: true
                 + setting3: none
                }""";
        LinkedHashMap<String, String> diff = Differ.generate(firstJsonFilePath, secondJsonFilePath);
        String actual = Formatter.toStylish(diff);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differYamlTest() throws Exception {
        String expect = """
                {
                   chars1: [a, b, c]
                 - chars2: [d, e, f]
                 + chars2: false
                 - checked: false
                 + checked: true
                 - default: null
                 + default: [value1, value2]
                 - id: 45
                 + id: null
                 - key1: value1
                 + key2: value2
                   numbers1: [1, 2, 3, 4]
                 - numbers2: [2, 3, 4, 5]
                 + numbers2: [22, 33, 44, 55]
                 - numbers3: [3, 4, 5]
                 + numbers4: [4, 5, 6]
                 + obj1: {nestedKey=value, isNested=true}
                 - setting1: Some value
                 + setting1: Another value
                 - setting2: 200
                 + setting2: 300
                 - setting3: true
                 + setting3: none
                }""";
        LinkedHashMap<String, String> diff = Differ.generate(firstYamlFilePath, secondYamlFilePath);
        String actual = Formatter.toStylish(diff);
        Assertions.assertEquals(expect, actual);
    }
}
