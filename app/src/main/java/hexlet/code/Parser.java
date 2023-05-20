package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.SortedMap;

public class Parser {
    public static SortedMap<String, Object> parse(String content, Type type) throws JsonProcessingException {

        SortedMap<String, Object> result;
        switch (type) {
            case JSON -> {
                ObjectMapper jsonMapper = new ObjectMapper();
                result = jsonMapper.readValue(content, new TypeReference<>() { });
            }
            case YAML -> {
                ObjectMapper yamlMapper = new YAMLMapper();
                result = yamlMapper.readValue(content, new TypeReference<>() { });
            }
            default -> throw new RuntimeException("The type is wrong: " + type);
        }
        return result;
    }
}
