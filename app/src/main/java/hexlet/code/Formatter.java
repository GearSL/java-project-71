package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.List;

public class Formatter {
    public static String toStylish(List<Diff> diff) {
        return Stylish.format(diff);
    }

    public static String toPlain(List<Diff> diff) {
        return Plain.format(diff);
    }

    public static String toJson(List<Diff> diff) {
        return Json.format(diff);
    }
}
