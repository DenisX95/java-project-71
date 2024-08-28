package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
//import picocli.CommandLine.Option;

@Command(name = "app", mixinStandardHelpOptions = true, version = "app 1.0",
        description = "Print standart greeting")
public class App implements Runnable {

    //@Option(names = {"-h", "--help"}, description = "Show help info")
    //private boolean help;

    @Override
    public void run() {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }
}
