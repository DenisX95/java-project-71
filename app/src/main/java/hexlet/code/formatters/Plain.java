package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class Plain implements DataFormatter {
    @Override
    public String format(List<Map<String, Object>> diffList) {
        var sb = new StringBuilder();

        var modifiedList = diffList.stream()
                .filter(m -> !m.get("status").equals("unmodified"))
                .toList();

        for (Map<String, Object> map : modifiedList) {
            String status = (String) map.get("status");

            sb.append("Property '%s' was ".formatted(map.get("key")));
            switch (status) {
                case "added" -> sb.append("added with value: %s%n".formatted(
                        formatValue(map.get("value"))));
                case "removed" -> sb.append("removed\n");
                case "updated" -> sb.append("updated. From %s to %s%n".formatted(
                        formatValue(map.get("value1")),
                        formatValue(map.get("value2"))
                ));
                default -> throw new IllegalStateException("Unexpected map status for Plain: " + status);
            }
        }

        return sb.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value.getClass().isArray() || value instanceof List || value instanceof Map) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }
}
