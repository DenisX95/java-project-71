package hexlet.code;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws IOException {
        String content1 = Utills.readFile(filepath1);
        String extension1 = Utills.getFileExtension(filepath1);

        String content2 = Utills.readFile(filepath2);
        String extension2 = Utills.getFileExtension(filepath2);

        Map<String, Object> map1 = Parser.parseStringIntoMap(content1, extension1);
        Map<String, Object> map2 = Parser.parseStringIntoMap(content2, extension2);

        List<Map<String, Object>> diffList = DiffGenerator.makeDiffList(map1, map2);
        return Formatter.formatListStylish(diffList, format);
    }

    public static String generate(String filepath1, String filepath2) throws IOException {
        return generate(filepath1, filepath2, "stylish");
    }
}
