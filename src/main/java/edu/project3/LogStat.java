package edu.project3;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogStat {

    private final List<Log> logs;
    private static final Pattern RESOURCE_REGEX = Pattern.compile("\\w+ ([\\w/\\\\]+) .+");
    private static final Pattern AGENT_REGEX = Pattern.compile(".*\\((.+)\\)");

    public enum ResponseCodes {
        NOT_FOUND(404, "Not Found"),
        NOT_MODIFIED(304, "Not Modified"),
        OK(200, "OK"),
        FORBIDDEN(403, "Forbidden"),
        PARTIAL_CONTENT(206, "Partial Content");

        private final int code;
        private final String codeDescription;

        ResponseCodes(int code, String codeDescription) {
            this.code = code;
            this.codeDescription = codeDescription;
        }

        int getCode() {
            return code;
        }

        String getDescription() {
            return codeDescription;
        }
    }

    public LogStat(List<Log> logs) {
        this.logs = logs;
    }

    public long getRequestCount() {
        return logs.size();
    }

    public Map<String, Long> getMostRequestedResources() {
        Function<Log, String> mapper = log -> Path.of(log.request()).getFileName().toString();
        return getMostFrequentStat(mapper, RESOURCE_REGEX);
    }

    public Map<Integer, Long> getMostReturnedStatuses() {
        Map<Integer, Long> statusCount = logs.stream()
            .map(Log::status)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return statusCount.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new
            ));
    }

    public int getAvgServerAnswer() {
        return (int) logs.stream()
            .mapToInt(Log::bodyBytesSent)
            .average()
            .orElse(0);
    }

    public Map<String, Long> getMostFrequentAgents() {
        return getMostFrequentStat(Log::httpUserAgent, AGENT_REGEX);
    }

    public Map<String, Long> getMostRequestedResourcesWithServerError() {
        List<Log> filteredLogs = logs.stream()
            .filter(log -> log.status() == ResponseCodes.NOT_FOUND.getCode())
            .toList();
        return new LogStat(filteredLogs).getMostRequestedResources();
    }

    private String getFirstRegexGroup(String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            return null;
        }
        return matcher.group(1);
    }

    private Map<String, Long> getMostFrequentStat(Function<Log, String> mapper, Pattern pattern) {
        Map<String, Long> requestCount = logs.stream()
            .map(mapper)
            .map(request -> getFirstRegexGroup(request, pattern))
            .filter(Objects::nonNull)
            .map(request -> Path.of(request).getFileName().toString())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return requestCount.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new
            ));
    }
}
