package hexlet.code.formatters;

import hexlet.code.Diff;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Diff> diff) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Diff diffEl : diff) {
            String state = diffEl.getState();
            if (state.equals("removed")) {
                String diffString = String.format("Property '%s' was %s\n", diffEl.getKey(), diffEl.getState());
                stringBuilder.append(diffString);
            } else if (state.equals("added")) {
                String diffString = String.format("Property '%s' was %s with value: %s\n",
                        diffEl.getKey(), diffEl.getState(), plainValueFormat(diffEl.getNewValue()));
                stringBuilder.append(diffString);
            } else if (state.equals("updated")) {
                String diffString = String.format("Property '%s' was %s. From %s to %s\n",
                        diffEl.getKey(), diffEl.getState(),
                        plainValueFormat(diffEl.getOldValue()), plainValueFormat(diffEl.getNewValue()));
                stringBuilder.append(diffString);
            }
        }
        return stringBuilder.toString().trim();
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
