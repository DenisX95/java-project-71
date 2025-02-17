package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish implements DataFormatter {
    @Override
    public String format(List<Map<String, Object>> diffList) {
        var sb = new StringBuilder("{\n");

        for (Map<String, Object> map : diffList) {
            String status = (String) map.get("status");
            switch (status) {
                case "added" -> sb.append(" ".repeat(2))
                        .append("+ %s: %s%n".formatted(map.get("key"), map.get("value")));
                case "removed" -> sb.append(" ".repeat(2))
                        .append("- %s: %s%n".formatted(map.get("key"), map.get("value")));
                case "unmodified" -> sb.append(" ".repeat(4))
                        .append("%s: %s%n".formatted(map.get("key"), map.get("value")));
                case "updated" -> sb.append(" ".repeat(2))
                        .append("- %s: %s%n".formatted(map.get("key"), map.get("from")))
                        .append(" ".repeat(2))
                        .append("+ %s: %s%n".formatted(map.get("key"), map.get("to")));
                default -> throw new IllegalStateException("Unexpected value: " + status);
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
