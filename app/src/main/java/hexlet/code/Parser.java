package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static ObjectMapper getMapper(String fileAddress) {
        var fileFormat = fileAddress.substring(fileAddress.indexOf("."));
        return switch (fileFormat) {
            case ".json" -> new ObjectMapper();
            case ".yaml", ".yml" -> new ObjectMapper(new YAMLFactory());
            default ->
                    throw new IllegalStateException("Unexpected fileFormat: " + fileFormat);
        };
    }

    public static Map<String, Object> parseFileIntoJavaMap(String fileAddress) throws IOException {
        Path filePath = Paths.get(fileAddress).toAbsolutePath().normalize();
        String fileContent = Files.readString(filePath);

        if (fileContent.isEmpty()) {
            return new HashMap<>();
        }

        ObjectMapper mapper = getMapper(fileAddress);
        return mapper.readValue(fileContent, new TypeReference<>() { });
    }

}
