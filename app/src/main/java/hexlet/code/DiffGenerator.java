package hexlet.code;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class DiffGenerator {
    public static List<Map<String, Object>> makeDiffList(
            Map<String, Object> map1,
            Map<String, Object> map2
    ) {
        List<Map<String, Object>> diffList = new ArrayList<>();

        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());

        for (var key : keys) {
            diffList.add(getDiffListElement(key, map1, map2));
        }

        return diffList;
    }

    private static Map<String, Object> getDiffListElement(
            String key,
            Map<String, Object> map1,
            Map<String, Object> map2
    ) {
        Map<String, Object> diffListElement = new LinkedHashMap<>();
        Optional<Object> value1 = Optional.ofNullable(map1.get(key));
        Optional<Object> value2 = Optional.ofNullable(map2.get(key));

        diffListElement.put("key", key);
        if (!map1.containsKey(key)) {
            diffListElement.put("status", "added");
            diffListElement.put("value", value2.orElse(null));
        } else if (!map2.containsKey(key)) {
            diffListElement.put("status", "removed");
            diffListElement.put("value", value1.orElse(null));
        } else if (value1.equals(value2)) {
            diffListElement.put("status", "unmodified");
            diffListElement.put("value", value2.orElse(null));
        } else {
            diffListElement.put("status", "updated");
            diffListElement.put("value1", value1.orElse(null));
            diffListElement.put("value2", value2.orElse(null));
        }

        return diffListElement;
    }
}
