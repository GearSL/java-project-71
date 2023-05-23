package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;

public class Formatter {
    public static String format(List<Diff> diff, String format) throws Exception {
        switch (format) {
            case "plain" -> {
                return toPlain(diff);
            }
            case "json" -> {
                return toJson(diff);
            }
            case "stylish", "default" -> {
                return toStylish(diff);
            }
            default -> throw new RuntimeException("Unsupported format: " + format);
        }
    }

    private static String toStylish(List<Diff> diff) {
        return Stylish.format(diff);
    }

    private static String toPlain(List<Diff> diff) {
        return Plain.format(diff);
    }

    private static String toJson(List<Diff> diff) throws Exception {
        return Json.format(diff);
    }
}
