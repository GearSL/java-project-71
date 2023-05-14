package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;

public class Formatter {
    public static String toStylish(LinkedHashMap<String, String> diff) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\n");
        for (Map.Entry<String, String> entry : diff.entrySet()) {
            if (entry.getValue().equals("unchanged")) {
                stringBuilder.append("   ").append(entry.getKey()).append("\n");
            } else if (entry.getValue().equals("added")) {
                stringBuilder.append(" + ").append(entry.getKey()).append("\n");
            } else {
                stringBuilder.append(" - ").append(entry.getKey()).append("\n");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String toPlain(LinkedHashMap<String, String> diff) {
        return " ";
    }
}
