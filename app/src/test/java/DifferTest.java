import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    private static String firstJsonFilePath;
    private static String secondJsonFilePath;
    private static String firstYamlFilePath;
    private static String secondYamlFilePath;
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";
    private static final String DEFAULT = "default";

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void init() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");

        firstJsonFilePath = getFixturePath("file_1.json").toString();
        secondJsonFilePath = getFixturePath("file_2.json").toString();
        firstYamlFilePath = getFixturePath("file_1.yml").toString();
        secondYamlFilePath = getFixturePath("file_2.yml").toString();
    }

    @Test
    public void differJsonStylishTest() throws Exception {
        String expect = resultStylish;
        String actual = Differ.generate(firstJsonFilePath, secondJsonFilePath, STYLISH);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differJsonPlainTest() throws Exception {
        String expect = resultPlain;
        String actual = Differ.generate(firstJsonFilePath, secondJsonFilePath, PLAIN);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differJsonTest() throws Exception {
        String expect = resultJson;
        String actual = Differ.generate(firstJsonFilePath, secondJsonFilePath, JSON);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differDefaultTest() throws Exception {
        String expect = resultStylish;
        String actual = Differ.generate(firstJsonFilePath, secondJsonFilePath, DEFAULT);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differYamlStylishTest() throws Exception {
        String expect = resultStylish;
        String actual = Differ.generate(firstYamlFilePath, secondYamlFilePath, STYLISH);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differYamlPlainTest() throws Exception {
        String expect = resultPlain;
        String actual = Differ.generate(firstYamlFilePath, secondYamlFilePath, PLAIN);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differYamlJsonTest() throws Exception {
        String expect = resultJson;
        String actual = Differ.generate(firstYamlFilePath, secondYamlFilePath, JSON);
        Assertions.assertEquals(expect, actual);
    }

    @Test
    public void differYamlDefaultTest() throws Exception {
        String expect = resultStylish;
        String actual = Differ.generate(firstYamlFilePath, secondYamlFilePath, DEFAULT);
        Assertions.assertEquals(expect, actual);
    }
}
