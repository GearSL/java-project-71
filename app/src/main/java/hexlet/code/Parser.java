package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedMap;

public class Parser {
    private static final String JSON = ".json";
    private static final String YAML = ".yml";

    public static SortedMap<String, Object> parse(String filePath) throws Exception {
        Path normalizedPath = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(normalizedPath)) {
            throw new Exception("File '" + normalizedPath + "' does not exist");
        }

        SortedMap<String, Object> result = null;
        String fileContent = Files.readString(normalizedPath);
        if (filePath.endsWith(JSON)) {
            ObjectMapper jsonMapper = new ObjectMapper();
            result = jsonMapper.readValue(fileContent, new TypeReference<>() { });
            System.out.println("JSON RESULT:" + result.toString());
        } else if (filePath.endsWith(YAML)) {
            ObjectMapper yamlMapper = new YAMLMapper();
            result = yamlMapper.readValue(fileContent, new TypeReference<>() { });
        }
        return result;
    }
}
