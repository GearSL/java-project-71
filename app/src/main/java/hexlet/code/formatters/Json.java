package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Diff;
import java.util.List;

public class Json {
    public static String format(List<Diff> diffs) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(diffs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return json;
    }
}
