import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class DifferTest {
    private static String firstJsonFileContent;
    private static String secondJsonFileContent;


    @BeforeAll
    public static void init() throws IOException {
        ClassLoader classLoader = DifferTest.class.getClassLoader();
        File file1 = new File(Objects.requireNonNull(classLoader.getResource("file_1.json")).getFile());
        File file2 = new File(Objects.requireNonNull(classLoader.getResource("file_2.json")).getFile());

        firstJsonFileContent = Files.readString(Path.of(file1.getAbsolutePath()));
        secondJsonFileContent = Files.readString(Path.of(file2.getAbsolutePath()));
    }

    @Test
    public void differTest() throws JsonProcessingException {
        String expect = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                }""";
        String actual = Differ.generate(firstJsonFileContent, secondJsonFileContent);
        Assertions.assertEquals(expect, actual);
    }
}
