package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Stylish implements DataFormatter {
    private static final int NUM_OF_SPACES = 2;

    @Override
    public String format(List<Map<String, Object>> diffList) {
        var sb = new StringBuilder("{\n");

        for (Map<String, Object> map : diffList) {
            String status = (String) map.get("status");
            switch (status) {
                case "added" -> sb.append(" ".repeat(NUM_OF_SPACES))
                        .append("+ %s: %s%n".formatted(map.get("key"), map.get("value")));
                case "removed" -> sb.append(" ".repeat(NUM_OF_SPACES))
                        .append("- %s: %s%n".formatted(map.get("key"), map.get("value")));
                case "unmodified" -> sb.append(" ".repeat(NUM_OF_SPACES * 2))
                        .append("%s: %s%n".formatted(map.get("key"), map.get("value")));
                case "updated" -> sb.append(" ".repeat(NUM_OF_SPACES))
                        .append("- %s: %s%n".formatted(map.get("key"), map.get("from")))
                        .append(" ".repeat(NUM_OF_SPACES))
                        .append("+ %s: %s%n".formatted(map.get("key"), map.get("to")));
                default -> throw new IllegalStateException("Unexpected map status for Stylish: " + status);
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
