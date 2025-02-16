package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatListStylish(List<Map<String, Object>> diffList, String format) {

        var sb = new StringBuilder("{\n");

        for (Map<String, Object> map : diffList) {
            String sign = (String) map.get("sign");
            switch (sign) {
                case "+" -> sb.append(" ".repeat(2))
                        .append("+ %s: %s%n".formatted(map.get("key"), map.get("value")));
                case "-" -> sb.append(" ".repeat(2))
                        .append("- %s: %s%n".formatted(map.get("key"), map.get("value")));
                case "" -> sb.append(" ".repeat(4))
                        .append("%s: %s%n".formatted(map.get("key"), map.get("value")));
                case "-+" -> sb.append(" ".repeat(2))
                        .append("- %s: %s%n".formatted(map.get("key"), map.get("value1")))
                        .append(" ".repeat(2))
                        .append("+ %s: %s%n".formatted(map.get("key"), map.get("value2")));
                default -> throw new IllegalStateException("Unexpected value: " + sign);
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
