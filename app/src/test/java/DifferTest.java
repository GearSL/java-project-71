import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

public class DifferTest {
    private static String firstJsonFilePath;
    private static String secondJsonFilePath;
    private static String firstYamlFilePath;
    private static String secondYamlFilePath;
    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";



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
    public void differJsonStylishTest() throws Exception {
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
        String actual = Differ.generate(firstJsonFilePath, secondJsonFilePath, STYLISH);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differYamlStylishTest() throws Exception {
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
        String actual = Differ.generate(firstYamlFilePath, secondYamlFilePath, STYLISH);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differYamlPlainTest() throws Exception {
        String expect = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;
        String actual = Differ.generate(firstYamlFilePath, secondYamlFilePath, PLAIN);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differJsonPlainTest() throws Exception {
        String expect = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;
        String actual = Differ.generate(firstJsonFilePath, secondJsonFilePath, PLAIN);
        Assertions.assertEquals(expect, actual);
    }
}
