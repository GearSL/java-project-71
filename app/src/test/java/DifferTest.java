import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static final String STYLISH = "stylish";
    private static final String PLAIN = "plain";
    private static final String JSON = "json";

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
    }

    @ParameterizedTest
    @ValueSource(strings = {"json", "yml"})
    public void generateTest(String format) throws Exception {
        String filePath1 = getFixturePath("file_1." + format).toString();
        String filePath2 = getFixturePath("file_2." + format).toString();

        Assertions.assertEquals(Differ.generate(filePath1, filePath2), resultStylish);
        Assertions.assertEquals(Differ.generate(filePath1, filePath2, STYLISH), resultStylish);
        Assertions.assertEquals(Differ.generate(filePath1, filePath2, PLAIN), resultPlain);
        Assertions.assertEquals(Differ.generate(filePath1, filePath2, JSON), resultJson);
    }
}
