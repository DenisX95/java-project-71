package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public interface DataFormatter {
    String format(List<Map<String, Object>> data);
}
