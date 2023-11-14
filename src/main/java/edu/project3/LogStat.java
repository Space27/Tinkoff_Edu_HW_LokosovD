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
        Pattern pattern = Pattern.compile("\\w+ ([\\w/\\\\]+) .+");
        return logs.stream()
            .map(Log::request)
            .map(request -> {
                Matcher matcher = pattern.matcher(request);
                if (!matcher.find()) {
                    return null;
                }
                return Path.of(matcher.group(1)).getFileName().toString();
            })
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new
            ));
    }

    public Map<Integer, Long> getMostReturnedStatuses() {
        return logs.stream()
            .map(Log::status)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
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
        Pattern pattern = Pattern.compile(".*\\((.+)\\)");
        return logs.stream()
            .map(Log::httpUserAgent)
            .map(agent -> {
                Matcher matcher = pattern.matcher(agent);
                if (!matcher.find()) {
                    return null;
                }
                return matcher.group(1);
            })
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new
            ));
    }

    public Map<String, Long> getMostRequestedResourcesWithServerError() {
        List<Log> filteredLogs = logs.stream()
            .filter(log -> log.status() == ResponseCodes.NOT_FOUND.getCode())
            .toList();
        return new LogStat(filteredLogs).getMostRequestedResources();
    }
}
