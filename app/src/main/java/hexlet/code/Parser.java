package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Parser {
    public static ObjectMapper getMapper(Path filePath) {
        return switch (Utills.getFileExtension(filePath)) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new ObjectMapper(new YAMLFactory());
            default ->
                    throw new IllegalStateException("Unexpected fileFormat: " + Utills.getFileExtension(filePath));
        };
    }

    public static Map<String, Object> parseStringIntoMap(String fileAddress) throws IOException {
        Path filePath = Utills.getPath(fileAddress);
        String fileContent = Utills.readFile(filePath);

        ObjectMapper mapper = getMapper(filePath);
        return mapper.readValue(fileContent, new TypeReference<>() { });
    }

}
