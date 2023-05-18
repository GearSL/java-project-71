package hexlet.code;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String toStylish(List<Diff> diff) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{\n");
        for (Diff diffEl : diff) {
            switch (diffEl.getState()) {
                case "removed" -> stringBuilder.append(" - ").append(diffEl.getKey()).append(": ")
                        .append(diffEl.getOldValue()).append("\n");
                case "added" -> stringBuilder.append(" + ").append(diffEl.getKey()).append(": ")
                        .append(diffEl.getNewValue()).append("\n");
                case "updated" -> {
                    stringBuilder.append(" - ").append(diffEl.getKey()).append(": ")
                            .append(diffEl.getOldValue()).append("\n");
                    stringBuilder.append(" + ").append(diffEl.getKey()).append(": ")
                            .append(diffEl.getNewValue()).append("\n");
                }
                default -> stringBuilder.append("   ").append(diffEl.getKey()).append(": ")
                        .append(diffEl.getOldValue()).append("\n");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static String toPlain(List<Diff> diff) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Diff diffEl : diff) {
            switch (diffEl.getState()) {
                case "removed" ->{
                    String diffString = String.format("Property '%s' was %s\n", diffEl.getKey(), diffEl.getState());
                    stringBuilder.append(diffString);
                }
                case "added" -> {
                    String diffString = String.format("Property '%s' was %s with value: %s\n",
                            diffEl.getKey(), diffEl.getState(), plainValueFormat(diffEl.getNewValue()));
                    stringBuilder.append(diffString);
                }
                case "updated" -> {
                    String diffString = String.format("Property '%s' was %s. From %s to %s\n",
                            diffEl.getKey(), diffEl.getState(),
                            plainValueFormat(diffEl.getOldValue()), plainValueFormat(diffEl.getNewValue()));
                    stringBuilder.append(diffString);
                }
            }
        }
        return stringBuilder.toString();
    }

    private static String plainValueFormat(Object value) {
        if (value == null) {
            return "null";
        } else if (value.getClass().isArray()) {
            return "[complex value]";
        } else if (value instanceof Collection<?>) {
            return "[complex value]";
        } else if (value instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (value instanceof Integer || value instanceof Boolean) {
            return value.toString();
        } else {
            return String.format("'%s'", value);
        }
    }
}
