package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DifferTest {

    private static String expected;

    private static String getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toString();
    }

    @BeforeAll
    public static void setUp()  throws IOException {
        expected = Utills.readFile(Utills.getPath(getFixturePath("expected")))
                .replace("\r\n", "\n").trim();
    }

    @Test
    public void testGenerateWithJson() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.json"),
                getFixturePath("filepath2.json")
        );
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateWithYaml() throws IOException {
        String actual = Differ.generate(
                getFixturePath("filepath1.yaml"),
                getFixturePath("filepath2.yaml")
        );
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
