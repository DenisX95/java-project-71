package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DifferTest {

    private static String stylishExpected;
    private static String plainExpected;
    private static String jsonExpected;

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toString();
    }

    @BeforeAll
    public static void setUp()  throws IOException {
        stylishExpected = Utills.readFile(getFixturePath("stylishExpected"))
                .replace("\r\n", "\n").trim();
        plainExpected = Utills.readFile(getFixturePath("plainExpected"))
                .replace("\r\n", "\n").trim();
        jsonExpected = Utills.readFile(getFixturePath("jsonExpected.json"))
                .replace("\r\n", "\n").trim();
    }

    @Test
    public void testGenerateWithJsonIntoStylish() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.json"),
                getFixturePath("filepath2.json")
        );
        assertNotNull(actual);
        assertEquals(stylishExpected, actual);
    }

    @Test
    public void testGenerateWithJsonIntoStylishExplicit() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.json"),
                getFixturePath("filepath2.json"),
                "stylish"
        );
        assertNotNull(actual);
        assertEquals(stylishExpected, actual);
    }

    @Test
    public void testGenerateWithYamlIntoStylish() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.yaml"),
                getFixturePath("filepath2.yaml")
        );
        assertNotNull(actual);
        assertEquals(stylishExpected, actual);
    }

    @Test
    public void testGenerateWithJsonIntoPlain() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.json"),
                getFixturePath("filepath2.json"),
                "plain"
        );
        assertNotNull(actual);
        assertEquals(plainExpected, actual);
    }

    @Test
    public void testGenerateWithYamlIntoPlain() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.yaml"),
                getFixturePath("filepath2.yaml"),
                "plain"
        );
        assertNotNull(actual);
        assertEquals(plainExpected, actual);
    }

    @Test
    public void testGenerateWithJsonIntoJson() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.json"),
                getFixturePath("filepath2.json"),
                "json"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode actualJson = objectMapper.readTree(actual);
        JsonNode expectedJson = objectMapper.readTree(jsonExpected);

        assertNotNull(actual);
        assertEquals(actualJson, expectedJson);
    }

    @Test
    public void testGenerateWithYamlIntoJson() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.yaml"),
                getFixturePath("filepath2.yaml"),
                "json"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode actualJson = objectMapper.readTree(actual);
        JsonNode expectedJson = objectMapper.readTree(jsonExpected);

        assertNotNull(actual);
        assertEquals(actualJson, expectedJson);
    }
}
