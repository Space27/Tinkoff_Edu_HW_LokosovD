package edu.project3;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

@CommandLine.Command(name = "nginx log stats",
                     description = "prints nginx log stats")
public final class LogAnalyzer implements Runnable {

    private LogAnalyzer() {
    }

    @CommandLine.Option(names = {"-p", "--path"},
                        description = "Path to logs (local template or URL)",
                        required = true,
                        arity = "1..*")
    private List<String> path;

    @CommandLine.Option(names = {"-s", "--from"}, description = "Start date of logs (ISO8601)")
    private String from;

    @CommandLine.Option(names = {"-e", "--to"}, description = "End date of logs (ISO8601)")
    private String to;

    @CommandLine.Option(names = {"-f", "--format"}, description = "Output format (markdown or adoc)")
    private Format format;

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private enum MetricKeys {
        RESOURCE("Ресурс(ы)"),
        START_DATE("Начальная дата"),
        END_DATE("Конечная дата"),
        REQUEST_COUNT("Количество запросов"),
        AVG_REQUEST_SIZE("Средний размер запроса");

        MetricKeys(String string) {
            this.value = string;
        }

        private final String value;
    }

    private Map<String, String> generalMetric = new LinkedHashMap<>() {
        {
            put(MetricKeys.RESOURCE.value, "-");
            put(MetricKeys.START_DATE.value, "-");
            put(MetricKeys.END_DATE.value, "-");
            put(MetricKeys.REQUEST_COUNT.value, "-");
            put(MetricKeys.AVG_REQUEST_SIZE.value, "-");
        }
    };

    private static final int TOP_COUNT = 3;

    public enum Format {
        markdown,
        adoc
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        CommandLine.run(new LogAnalyzer(), System.err, args);
    }

    @SuppressWarnings({"checkstyle:MultipleStringLiterals", "checkstyle:RegexpSinglelineJava"})
    @Override
    public void run() {
        LocalDateTime startDate = LocalDateTime.MIN;
        LocalDateTime endDate = LocalDateTime.MAX;

        if (CLIParser.parseDateFromString(from) != null) {
            startDate = CLIParser.parseDateFromString(from);
            generalMetric.put(MetricKeys.START_DATE.value, startDate.toLocalDate().toString());
        }
        if (CLIParser.parseDateFromString(to) != null) {
            endDate = CLIParser.parseDateFromString(to);
            generalMetric.put(MetricKeys.END_DATE.value, endDate.toLocalDate().toString());
        }

        List<Log> logList = new ArrayList<>();
        for (String i : path) {
            try {
                logList.addAll(LogParser.parseStringList(new HttpSource(new URL(i).toURI()).getStringList()));
            } catch (URISyntaxException | MalformedURLException e) {
                logList.addAll(LogParser.parseStringList(new FileSource(Path.of(i)).getStringList()));
            }
        }
        generalMetric.put(MetricKeys.RESOURCE.value, String.join(", ", path));

        logList = LogParser.filterLogsForDate(logList, startDate, endDate);
        LogStat logStat = new LogStat(logList);
        StatPrinter statPrinter = new StatPrinter(format);

        generalMetric.put(MetricKeys.REQUEST_COUNT.value, String.valueOf(logStat.getRequestCount()));
        generalMetric.put(MetricKeys.AVG_REQUEST_SIZE.value, String.valueOf(logStat.getAvgServerAnswer()));
        System.out.println(statPrinter.printSortedMapFirstKElements(
            generalMetric,
            generalMetric.size(),
            "Общая метрика",
            "Метрика",
            "Значение"
        ));

        System.out.println(statPrinter.printSortedMapFirstKElements(
            logStat.getMostRequestedResources(),
            TOP_COUNT,
            "Запрашиваемые ресурсы",
            "Ресурс",
            "Количество"
        ));

        System.out.println(statPrinter.printServerCodes(
            logStat.getMostReturnedStatuses(),
            TOP_COUNT,
            "Коды ответа",
            "Код",
            "Имя",
            "Количество"
        ));

        System.out.println(statPrinter.printSortedMapFirstKElements(
            logStat.getMostRequestedResourcesWithServerError(),
            TOP_COUNT,
            "Ресурсы, доступ к которым закончился ошибкой 404",
            "Ресурс",
            "Количество"
        ));

        System.out.println(statPrinter.printSortedMapFirstKElements(
            logStat.getMostFrequentAgents(),
            TOP_COUNT,
            "Агенты",
            "Агент",
            "Количество"
        ));
    }
}
