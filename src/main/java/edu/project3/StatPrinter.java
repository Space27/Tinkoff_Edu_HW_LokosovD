package edu.project3;

import java.util.Map;

public class StatPrinter {

    private final LogAnalyzer.Format printFormat;
    private static final String ADOC_TABLE = "|===";
    private static final String ADOC_TITLE = ".";
    private static final String MARKDOWN_TITLE = "### ";

    public StatPrinter(LogAnalyzer.Format format) {
        printFormat = format;
    }

    public String printSortedMapFirstKElements(
        Map<?, ?> printedMap,
        int k,
        String tableName,
        String col1Name,
        String col2Name
    ) {
        StringBuilder result = new StringBuilder();

        if (printFormat == LogAnalyzer.Format.adoc) {
            result.append(ADOC_TITLE).append(tableName).append('\n');
            result.append(ADOC_TABLE).append('\n');
            result.append("|").append(col1Name).append("|").append(col2Name).append('\n').append('\n');
            int i = 0;
            for (var j : printedMap.entrySet()) {
                if (++i > k) {
                    break;
                }
                result.append("|").append(j.getKey())
                    .append("|").append(j.getValue()).append('\n');
            }
            result.append(ADOC_TABLE).append('\n');
        } else {
            result.append(MARKDOWN_TITLE).append(tableName).append('\n');
            result.append("|").append(col1Name).append("|").append(col2Name).append("|").append('\n');
            result.append("|:-:|-:|").append('\n');
            int i = 0;
            for (var j : printedMap.entrySet()) {
                if (++i > k) {
                    break;
                }
                result.append("|").append(j.getKey())
                    .append("|").append(j.getValue())
                    .append("|").append('\n');
            }
        }

        return result.toString();
    }

    public String printServerCodes(
        Map<Integer, ?> printedMap,
        int k,
        String tableName,
        String col1Name,
        String col2Name,
        String col3Name
    ) {
        StringBuilder result = new StringBuilder();

        if (printFormat == LogAnalyzer.Format.adoc) {
            result.append(ADOC_TITLE).append(tableName).append('\n');
            result.append(ADOC_TABLE).append('\n');
            result.append("|").append(col1Name)
                .append("|").append(col2Name)
                .append("|").append(col3Name).append('\n').append('\n');
            int i = 0;
            for (var j : printedMap.entrySet()) {
                if (++i > k) {
                    break;
                }
                result.append("|").append(j.getKey())
                    .append("|").append(findCodeDescriptionForInt(j.getKey()))
                    .append("|").append(j.getValue()).append('\n');
            }
            result.append(ADOC_TABLE).append('\n');
        } else {
            result.append(MARKDOWN_TITLE).append(tableName).append('\n');
            result.append("|").append(col1Name).append("|").append(col2Name).append("|").append(col3Name).append("|")
                .append('\n');
            result.append("|:-:|:-:|-:|").append('\n');
            int i = 0;
            for (var j : printedMap.entrySet()) {
                if (++i > k) {
                    break;
                }
                result.append("|").append(j.getKey())
                    .append("|").append(findCodeDescriptionForInt(j.getKey()))
                    .append("|").append(j.getValue()).append("|").append('\n');
            }
        }

        return result.toString();
    }

    private String findCodeDescriptionForInt(int code) {
        for (var i : LogStat.ResponseCodes.values()) {
            if (i.getCode() == code) {
                return i.getDescription();
            }
        }

        return "";
    }
}
