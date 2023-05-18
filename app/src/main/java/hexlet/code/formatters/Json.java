package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hexlet.code.Diff;
import java.util.List;

public class Json {
    public static String format(List<Diff> diffs) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode result = mapper.createObjectNode();

        diffs.forEach(diff -> {
            ObjectNode jsonDiff = mapper.createObjectNode();
            jsonDiff.put("state", diff.getState());
            jsonDiff.put("oldValue", diff.getOldValue() != null ? diff.getOldValue().toString() : null);
            jsonDiff.put("newValue", diff.getNewValue() != null ? diff.getNewValue().toString() : null);
            result.set(diff.getKey(), jsonDiff);
        });

        return result.toString();
    }
}
