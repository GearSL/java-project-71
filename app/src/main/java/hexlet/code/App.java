package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "checksum 4.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private final static Boolean help = false;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    private final static Boolean version = false;
    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private final static String format = "stylish";
    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private static String filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private static String filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

        System.out.println("Hello, world!");
    }

    @Override
    public Integer call() throws Exception {
        Path file1Path = Paths.get(filepath1).toAbsolutePath().normalize();
        Path file2Path = Paths.get(filepath2).toAbsolutePath().normalize();

        if (!Files.exists(file1Path)) {
            throw new Exception("File '" + file1Path + "' does not exist");
        }
        if (!Files.exists(file2Path)) {
            throw new Exception("File '" + file2Path + "' does not exist");
        }

        String contentFile1 = Files.readString(file1Path);
        String contentFile2 = Files.readString(file2Path);

        System.out.println(Differ.generate(contentFile1, contentFile2));

        return null;
    }
}
