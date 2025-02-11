package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws IOException {

        Map<String, Object> fileMap1 = Utils.parseJsonIntoJavaMap(filepath1);
        Map<String, Object> fileMap2 = Utils.parseJsonIntoJavaMap(filepath2);

        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(fileMap1.keySet());
        keys.addAll(fileMap2.keySet());

        if (keys.isEmpty()) {
            return "{}";
        }

        StringBuilder result = new StringBuilder("{\n");

        for (var key : keys) {

            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {
                var value1 = fileMap1.get(key);
                var value2 = fileMap2.get(key);

                if (value1.equals(value2)) {
                    result.append("    ").append(key).append(": ").append(value1).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(value1).append("\n");
                    result.append("  + ").append(key).append(": ").append(value2).append("\n");
                }
            } else if (fileMap1.containsKey(key)) {
                var value1 = fileMap1.get(key);
                result.append("  - ").append(key).append(": ").append(value1).append("\n");
            } else {
                var value2 = fileMap2.get(key);
                result.append("  + ").append(key).append(": ").append(value2).append("\n");
            }
        }

        result.append("}");

        return result.toString();
    }

}
