package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Diff;
import java.util.List;

public class Json {
    public static String format(List<Diff> diffs) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffs).trim();
    }
}
