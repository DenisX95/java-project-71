package hexlet.code;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DifferTest {

    private static String jsonFile1;
    private static String jsonFile2;
    private static String jsonEmptyFile;
    private static String ymlFile1;
    private static String ymlFile2;
    private static String ymlEmptyFile;

    private static String getFilePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toString();
    }

    @BeforeAll
    public static void setUp()  throws IOException {
        jsonFile1 = getFilePath("file1.json");
        jsonFile2 = getFilePath("file2.json");
        jsonEmptyFile = getFilePath("empty.json");
        ymlFile1 = getFilePath("filepath1.yml");
        ymlFile2 = getFilePath("filepath2.yml");
        ymlEmptyFile = getFilePath("empty.yml");
    }

    @Test
    public void testGenerateWithValidFiles() throws IOException {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """.trim();
        String actual = Differ.generate(jsonFile1, jsonFile2);
        assertNotNull(actual);
        assertEquals(expected, actual);

        actual = Differ.generate(ymlFile1, ymlFile2);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateWithEmptyFile1() throws IOException {
        String expected = """
                {
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                }
                """.trim();
        String actual = Differ.generate(jsonFile1, jsonEmptyFile);
        assertNotNull(actual);
        assertEquals(expected, actual);

        actual = Differ.generate(ymlFile1, ymlEmptyFile);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateWithEmptyFile2() throws IOException {
        String expected = """
                {
                  + host: hexlet.io
                  + timeout: 20
                  + verbose: true
                }
                """.trim();
        String actual = Differ.generate(jsonEmptyFile, jsonFile2);
        assertNotNull(actual);
        assertEquals(expected, actual);

        actual = Differ.generate(ymlEmptyFile, ymlFile2);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateWithBothEmptyFiles() throws IOException {
        String expected = "{}";
        String actual = Differ.generate(jsonEmptyFile, jsonEmptyFile);
        assertNotNull(actual);
        assertEquals(expected, actual);

        actual = Differ.generate(ymlEmptyFile, ymlEmptyFile);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

}
