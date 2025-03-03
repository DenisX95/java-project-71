package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utills {
    public static String readFile(String filePath) throws IOException {
        Path path = getPath(filePath);
        return Files.readString(path);
    }

    public static Path getPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static String getFileExtension(String filePath) {
        Path path = getPath(filePath);
        String fileName = path.getFileName().toString();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex <= 0 || lastDotIndex == fileName.length() - 1) {
            return "";
        } else {
            return fileName.substring(lastDotIndex + 1).trim().toLowerCase();
        }
    }
}
