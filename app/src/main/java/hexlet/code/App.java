package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private static Boolean help = false;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private static Boolean version = false;
    @Option(names = {"-f", "--format"}, paramLabel = "format", defaultValue = "default",
            description = "output format [default: stylish]")
    private String format;
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String firstFilePath;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String secondFilePath;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        String diff = Differ.generate(firstFilePath, secondFilePath, format);
        System.out.println(diff);
        return 0;
    }
}
