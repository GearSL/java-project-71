package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.SortedMap;

public class Differ {
    public static String generate(String file1, String file2) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SortedMap<String, Object> firstJsonMap = mapper.readValue(file1, new TypeReference<>() { });
        SortedMap<String, Object> secondJsonMap = mapper.readValue(file2, new TypeReference<>() { });

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (String key : firstJsonMap.keySet()) {
            if (secondJsonMap.containsKey(key) && secondJsonMap.get(key).equals(firstJsonMap.get(key))) {
                stringBuilder.append("   ").append(key).append(": ").append(firstJsonMap.get(key)).append("\n");
            } else if (secondJsonMap.containsKey(key) && !secondJsonMap.get(key).equals(firstJsonMap.get(key))) {
                stringBuilder.append(" - ").append(key).append(": ").append(firstJsonMap.get(key)).append("\n");
                stringBuilder.append(" + ").append(key).append(": ").append(secondJsonMap.get(key)).append("\n");
            } else if (!secondJsonMap.containsKey(key)) {
                stringBuilder.append(" - ").append(key).append(": ").append(firstJsonMap.get(key)).append("\n");
            }
        }
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
