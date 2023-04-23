package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    public static Boolean help = false;

    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    public static Boolean version = false;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

        System.out.println("Hello, world!");
    }

    @Override
    public Integer call(){
        return null;
    }
}
