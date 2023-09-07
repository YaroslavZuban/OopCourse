package ru.academits.java.zuban.csv;

import java.io.*;

public class Csv {
    private int quotesCount;

    public void convertFileCsvToHtml(String csvFileName, String htmlFileName) throws IOException {
        if (csvFileName == null) {
            throw new NullPointerException("Путь к CSV файлу равен null");
        }

        if (htmlFileName == null) {
            throw new NullPointerException("Путь к HTML файлу равен null");
        }

        try (BufferedReader cvsReader = new BufferedReader(new FileReader(csvFileName));
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

            while ((csvLine = cvsReader.readLine()) != null) {
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
            htmlLine.append("    <tr>");
            htmlLine.append("        <td>")
                    .append(System.lineSeparator())
                    .append("            ");

            quotesCount = getQuotesNumber(line);
        }

        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);

            if (i + 2 < line.length() && symbol == '"' && line.charAt(i + 1) == ',' && line.charAt(i + 2) == '"') {
                i += 2;

                htmlLine.append(System.lineSeparator())
                        .append("        </td>")
                        .append(System.lineSeparator())
                        .append("        <td>")
                        .append(System.lineSeparator())
                        .append("            ");
            } else if (i + 2 < line.length() && symbol == '"' && line.charAt(i + 1) == '"' && line.charAt(i + 2) == ',') {
                i += 2;

                htmlLine.append("\",");
            } else if (i + 1 < line.length() && symbol == '"' && line.charAt(i + 1) == '"') {
                i++;

                htmlLine.append('"');
            } else if ((i + 1 < line.length() && symbol == ',' && line.charAt(i + 1) == '"') || (i + 1 < line.length() && symbol == '"' && line.charAt(i + 1) == ',')) {
                i++;

                htmlLine.append(System.lineSeparator())
                        .append("        </td>")
                        .append(System.lineSeparator())
                        .append("        <td>")
                        .append(System.lineSeparator())
                        .append("            ");
            } else if (symbol == '&') {
                htmlLine.append("&amp;");
            } else if (symbol == '<') {
                htmlLine.append("&lt;");
            } else if (symbol == '>') {
                htmlLine.append("&gt;");
            } else if (symbol != '"') {
                htmlLine.append(symbol);
            }
        }

        if (quotesCount % 2 != 0) {
            htmlLine.append("<br/>");
            quotesCount += getQuotesNumber(line);
        } else {
            htmlLine.append(System.lineSeparator())
                    .append("        </td>")
                    .append(System.lineSeparator());

            htmlLine.append("    </tr>").append(System.lineSeparator());

            quotesCount = 0;
        }

        return htmlLine.toString();
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