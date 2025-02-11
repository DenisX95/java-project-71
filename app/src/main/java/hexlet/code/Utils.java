package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

public class Utils {
    public static Map<String, Object> parseJsonIntoJavaMap(String fileAddress) throws IOException {
        Path filePath = Paths.get(fileAddress).toAbsolutePath().normalize();
        String fileContent = Files.readString(filePath);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(fileContent, new TypeReference<>() {
        });
    }

    public static String parseMapIntoJson(Map<String, Object> mapObject) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString((mapObject));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String parseMapIntoYaml(Map<String, Object> mapObject) {
        ObjectMapper mapper = new YAMLMapper(); // Используем YAML-форматтер
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(mapObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
