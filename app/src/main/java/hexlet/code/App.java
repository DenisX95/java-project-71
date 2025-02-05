package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "app 1.0",
        description = "Compares two configuration files and shows a difference")
public class App implements Callable<Integer> {

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws IOException {

        Map<String, Object> fileMap1 = Utils.parseJsonIntoJavaMap(filepath1);
        Map<String, Object> fileMap2 = Utils.parseJsonIntoJavaMap(filepath2);

        Map<String, Object> unionMap = new LinkedHashMap<>();

        TreeSet<String> keys = new TreeSet<>();
        keys.addAll(fileMap1.keySet());
        keys.addAll(fileMap2.keySet());

        for (var key : keys) {

            if (fileMap1.containsKey(key) && fileMap2.containsKey(key)) {

                var value1 = fileMap1.get(key);
                var value2 = fileMap2.get(key);

                if (value1.equals(value2)) {
                    unionMap.put(key, value1);
                } else {
                    unionMap.put("-" + key, value1);
                    unionMap.put("+" + key, value2);
                }
            } else if (fileMap1.containsKey(key)) {

                var value1 = fileMap1.get(key);
                unionMap.put("-" + key, value1);
            } else {

                var value2 = fileMap2.get(key);
                unionMap.put("+" + key, value2);
            }
        }

        System.out.println(Utils.parseMapIntoJson(unionMap));
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
