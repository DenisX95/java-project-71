package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DifferTest {

    private static String stylishExpected;
    private static String planExpected;
    private static String jsonExpected;

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toString();
    }

    @BeforeAll
    public static void setUp()  throws IOException {
        stylishExpected = Utills.readFile(Utills.getPath(getFixturePath("stylishExpected")))
                .replace("\r\n", "\n").trim();
        planExpected = Utills.readFile(Utills.getPath(getFixturePath("planExpected")))
                .replace("\r\n", "\n").trim();
        jsonExpected = Utills.readFile(Utills.getPath(getFixturePath("jsonExpected.json")))
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
    public void testGenerateWithYamlIntoStylish() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.yaml"),
                getFixturePath("filepath2.yaml")
        );
        assertNotNull(actual);
        assertEquals(stylishExpected, actual);
    }

    @Test
    public void testGenerateWithJsonIntoPlan() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.json"),
                getFixturePath("filepath2.json"),
                "plan"
        );
        assertNotNull(actual);
        assertEquals(planExpected, actual);
    }

    @Test
    public void testGenerateWithYamlIntoPlan() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.yaml"),
                getFixturePath("filepath2.yaml"),
                "plan"
        );
        assertNotNull(actual);
        assertEquals(planExpected, actual);
    }

    @Test
    public void testGenerateWithJsonIntoJson() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.json"),
                getFixturePath("filepath2.json"),
                "json"
        );
        assertNotNull(actual);
        assertEquals(jsonExpected, actual);
    }

    @Test
    public void testGenerateWithYamlIntoJson() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.yaml"),
                getFixturePath("filepath2.yaml"),
                "json"
        );
        assertNotNull(actual);
        assertEquals(jsonExpected, actual);
    }
}
