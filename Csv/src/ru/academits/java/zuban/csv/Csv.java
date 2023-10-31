package ru.academits.java.zuban.csv;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Csv {
    public static void convertFileCsvToHtml(String csvFileName, String htmlFileName) throws IOException {
        if (csvFileName == null) {
            throw new NullPointerException("Путь к CSV файлу равен null");
        }

        if (htmlFileName == null) {
            throw new NullPointerException("Путь к HTML файлу равен null");
        }

        try (BufferedReader csvReader = new BufferedReader(new FileReader(csvFileName));
             PrintWriter htmlWriter = new PrintWriter(htmlFileName)) {
            htmlWriter.println("<!DOCTYPE html>");
            htmlWriter.println("<html>");
            htmlWriter.println("<head>");
            htmlWriter.println("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
            htmlWriter.println("\t<title>Таблица</title>");
            htmlWriter.println("</head>");
            htmlWriter.println("<body>");
            htmlWriter.println("<table>");

            String csvLine;

            int quotesCount = 0;
            boolean isOpenQuote = false;

            while ((csvLine = csvReader.readLine()) != null) {
                htmlWriter.write(replaceChars(csvLine, quotesCount, isOpenQuote));

                quotesCount += getQuotesCount(csvLine);

                if (quotesCount % 2 == 0) {
                    quotesCount = 0;
                }
            }

            htmlWriter.println("</table>");
            htmlWriter.println("</body>");
            htmlWriter.println("</html>");
        }
    }

    private static String replaceChars(String line, int quotesCount, boolean isOpenQuote) {
        StringBuilder htmlLine = new StringBuilder();

        if (quotesCount == 0) {
            htmlLine.append("\t<tr>")
                    .append(System.lineSeparator())
                    .append("\t\t<td>")
                    .append(System.lineSeparator())
                    .append("\t\t\t");
        }

        quotesCount += getQuotesCount(line);

        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);

            if (symbol == '"' || (i + 1 < line.length() && symbol == ',' && line.charAt(i + 1) == '"')) {
                isOpenQuote = true;
            }

            if (!isOpenQuote) {
                if (symbol != ',') {
                    htmlLine.append(handleSpecialCharacters(symbol));
                } else {
                    htmlLine.append(createHtmlTableCellSeparator());
                }
            } else {
                if (i + 2 < line.length() && symbol == '"' && line.charAt(i + 1) == ',' && line.charAt(i + 2) == '"') {
                    i += 2;

                    htmlLine.append(createHtmlTableCellSeparator());
                } else if (i + 2 < line.length() && symbol == '"' && line.charAt(i + 1) == '"' && line.charAt(i + 2) == ',') {
                    i += 2;

                    htmlLine.append("\",");
                } else if (i + 1 < line.length() && symbol == '"' && line.charAt(i + 1) == '"') {
                    i++;

                    htmlLine.append('"');
                } else if (i + 1 < line.length() && symbol == ',' && line.charAt(i + 1) == '"') {
                    i++;

                    htmlLine.append(createHtmlTableCellSeparator());
                } else if (i + 1 < line.length() && symbol == '"' && line.charAt(i + 1) == ',' && i != 0) {
                    i++;
                    isOpenQuote = false;

                    htmlLine.append(createHtmlTableCellSeparator());
                } else if (symbol != '"') {
                    htmlLine.append(handleSpecialCharacters(symbol));
                }
            }
        }

        if (quotesCount % 2 != 0) {
            htmlLine.append("<br/>");
        } else {
            htmlLine.append(System.lineSeparator())
                    .append("\t\t</td>")
                    .append(System.lineSeparator())
                    .append("\t</tr>")
                    .append(System.lineSeparator());
        }

        return htmlLine.toString();
    }

    private static String createHtmlTableCellSeparator() {
        return System.lineSeparator() +
                "\t\t</td>" +
                System.lineSeparator() +
                "\t\t<td>" +
                System.lineSeparator() +
                "\t\t\t";
    }

    private static String handleSpecialCharacters(char symbol) {
        if (symbol == '&') {
            return "&amp;";
        }

        if (symbol == '<') {
            return "&lt;";
        }

        if (symbol == '>') {
            return "&gt;";
        }

        return String.valueOf(symbol);
    }

    private static int getQuotesCount(String line) {
        int quotesCount = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                quotesCount++;
            }
        }

        return quotesCount;
    }
}