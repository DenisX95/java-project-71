package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

public class Utils {
    public static Map<String, Object> parseJsonIntoJavaMap(String fileAddress) throws IOException{
        Path filePath = Paths.get(fileAddress).toAbsolutePath().normalize();
        String fileContent = Files.readString(filePath);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(fileContent, new TypeReference<>() {
        });
    }
}
