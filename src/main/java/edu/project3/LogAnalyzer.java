package edu.project3;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

@CommandLine.Command(name = "nginx log stats",
                     description = "prints nginx log stats")
public final class LogAnalyzer implements Runnable {

    private LogAnalyzer() {
    }

    @CommandLine.Option(names = {"-p", "--path"}, description = "Path to logs (local template or URL)", required = true)
    private String path;

    @CommandLine.Option(names = {"-s", "--from"}, description = "Start date of logs (ISO8601)")
    private String from;

    @CommandLine.Option(names = {"-e", "--to"}, description = "End date of logs (ISO8601)")
    private String to;

    @CommandLine.Option(names = {"-f", "--format"}, description = "Output format (markdown or adoc)")
    private String format;

    public enum Format {
        markdown,
        adoc
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        CommandLine.run(new LogAnalyzer(), System.err, args);
    }

    @Override
    public void run() {
        LocalDateTime startDate = LocalDateTime.MIN;
        LocalDateTime endDate = LocalDateTime.MAX;
        Format outputFormat = CLIParser.parseOutputFormatFromString(format);

        if (CLIParser.parseDateFromString(from) != null) {
            startDate = CLIParser.parseDateFromString(from);
        }
        if (CLIParser.parseDateFromString(to) != null) {
            endDate = CLIParser.parseDateFromString(to);
        }

        List<Log> logList;
        try {
            logList = LogParser.parseStringList(new HttpSource(new URI(path)).getStringList());
        } catch (URISyntaxException e) {
            logList = LogParser.parseStringList(new FileSource(Path.of(path)).getStringList());
        }
        logList = LogParser.filterLogsForDate(logList, startDate, endDate);
    }
}
