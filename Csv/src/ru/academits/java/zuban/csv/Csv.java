package ru.academits.java.zuban.csv;

import java.io.*;

public class Csv {
    private int quotesCount;
    private boolean isOpenQuote = false;

    public void convertFileCsvToHtml(String csvFileName, String htmlFileName) throws IOException {
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
            htmlWriter.println("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
            htmlWriter.println("    <title>Таблица</title>");
            htmlWriter.println("</head>");
            htmlWriter.println("<body>");
            htmlWriter.println("<table>");


            String csvLine;

            while ((csvLine = csvReader.readLine()) != null) {
                if (csvLine.isEmpty()) {
                    continue;
                }

                htmlWriter.write(replaceChars(csvLine));
            }

            htmlWriter.println("</table>");
            htmlWriter.println("</body>");
            htmlWriter.println("</html>");
        }
    }

    private String replaceChars(String line) {
        StringBuilder htmlLine = new StringBuilder();

        if (quotesCount == 0) {
            htmlLine.append("    <tr>")
                    .append(System.lineSeparator())
                    .append("        <td>")
                    .append(System.lineSeparator())
                    .append("            ");

            quotesCount = getQuotesNumber(line);
        }

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
                } else if (i + 1 < line.length() && symbol == '"' && line.charAt(i + 1) == ',') {
                    i++;
                    isOpenQuote = false;

                    htmlLine.append(createHtmlTableCellSeparator());
                } else if (i + 1 == line.length() && symbol == '"') {
                    break;
                } else {
                    htmlLine.append(handleSpecialCharacters(symbol));
                }
            }
        }

        if (quotesCount % 2 != 0) {
            htmlLine.append("<br/>");
            quotesCount += getQuotesNumber(line);
        } else {
            htmlLine.append(System.lineSeparator())
                    .append("        </td>")
                    .append(System.lineSeparator())
                    .append("    </tr>")
                    .append(System.lineSeparator());

            quotesCount = 0;
            isOpenQuote = false;
        }

        return htmlLine.toString();
    }

    private static String createHtmlTableCellSeparator() {
        return System.lineSeparator() +
                "        </td>" +
                System.lineSeparator() +
                "        <td>" +
                System.lineSeparator() +
                "            ";
    }

    private String handleSpecialCharacters(char symbol) {
        if (symbol == '&') {
            return "&amp;";
        } else if (symbol == '<') {
            return "&lt;";
        } else if (symbol == '>') {
            return "&gt;";
        }

        return String.valueOf(symbol);
    }

    private static int getQuotesNumber(String line) {
        int quotesCount = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                quotesCount++;
            }
        }

        return quotesCount;
    }
}