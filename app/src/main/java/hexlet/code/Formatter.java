package hexlet.code;

import hexlet.code.formatters.DataFormatter;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plan;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static DataFormatter getDataFormatter(String format) {
        return switch (format) {
            case "stylish" -> new Stylish();
            case "plan" -> new Plan();
            case "json" -> new Json();
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }

    public static String formatListStylish(List<Map<String, Object>> diffList, String format) throws IOException {
        DataFormatter dataFormatter = getDataFormatter(format);
        return dataFormatter.format(diffList);
    }
}
