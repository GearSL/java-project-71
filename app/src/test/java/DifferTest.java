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
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        String actual = Differ.generate(firstJsonFilePath, secondJsonFilePath);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differYamlTest() throws Exception {
        String expect = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        String actual = Differ.generate(firstYamlFilePath, secondYamlFilePath);
        Assertions.assertEquals(expect, actual);
    }
}
