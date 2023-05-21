package hexlet.code.formatters;

import hexlet.code.Diff;

import java.util.List;

public class Stylish {
    public static String format(List<Diff> diff) {
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
                case "unchanged" -> stringBuilder.append("   ").append(diffEl.getKey()).append(": ")
                        .append(diffEl.getOldValue()).append("\n");
                default -> throw new RuntimeException("The status is wrong: " + diffEl.getState());
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString().trim();
    }
}
