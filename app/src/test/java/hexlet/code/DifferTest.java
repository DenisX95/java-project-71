package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DifferTest {

    private static String jsonFile1;
    private static String jsonFile2;
    private static String emptyFile;

    private static Path getFilePath(String fileName) {
        return Paths.get("src", "test", "resources", fileName);
    }

    private static String readFile(String fileName) throws IOException {
        var path = getFilePath(fileName);
        return Files.readString(path).trim();
    }

    @BeforeAll
    public static void setUp()  throws IOException {
        jsonFile1 = getFilePath("file1.json").toString();
        jsonFile2 = getFilePath("file2.json").toString();
        emptyFile = getFilePath("empty.json").toString();
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
        String actual = Differ.generate(jsonFile1, emptyFile);
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
        String actual = Differ.generate(emptyFile, jsonFile2);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testGenerateWithBothEmptyFiles() throws IOException {
        String expected = "{}";
        String actual = Differ.generate(emptyFile, emptyFile);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}
