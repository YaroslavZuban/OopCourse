package ru.academits.java.zuban.csv;

import java.io.*;

public class CSV {
    private int quotesCount;

    public void conversionFileCvsToHtml(String fileNameCsv, String fileNameHtml) {
        if (fileNameCsv == null) {
            throw new NullPointerException("Первый переданный аргумент равен null");
        }

        if (fileNameHtml == null) {
            throw new NullPointerException("Второй переданный аргумент равен null");
        }

        translateToHtml(fileNameCsv, fileNameHtml);
    }

    public void translateToHtml(String fileNameCsv, String fileNameHtml) {
        try (BufferedReader cvsReader = new BufferedReader(new FileReader(fileNameCsv));
             PrintWriter htmlWriter = new PrintWriter(fileNameHtml)) {

            htmlWriter.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n");
            htmlWriter.write("<html>\n");
            htmlWriter.write("<head>\n");
            htmlWriter.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n");
            htmlWriter.write("    <title>Таблица</title>\n");
            htmlWriter.write("</head>\n");
            htmlWriter.write("<body>\n");
            htmlWriter.write("<table>\n");

            String csvLine;

            while ((csvLine = cvsReader.readLine()) != null) {
                if (csvLine.isEmpty()) {
                    continue;
                }

                htmlWriter.write(replaceChar(csvLine));
            }

            htmlWriter.write("</table>\n");
            htmlWriter.write("</body>\n");
            htmlWriter.write("</html>\n");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден или не может быть открыт.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла.");
        }
    }

    private String replaceChar(String line) {
        StringBuilder lineResult = new StringBuilder();

        if (quotesCount == 0) {
            lineResult.append("    <tr>\n");
            lineResult.append("        <td>\n            ");
            quotesCount = countOccurrences(line);
        }

        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);

            if (i + 2 < line.length() && symbol == '"' && line.charAt(i + 1) == ',' && line.charAt(i + 2) == '"') {
                i += 2;
                lineResult.append("\n        </td>\n").append("        <td>\n            ");
            } else if (i + 2 < line.length() && symbol == '"' && line.charAt(i + 1) == '"' && line.charAt(i + 2) == ',') {
                i += 2;
                lineResult.append("\",");
            } else if (i + 1 < line.length() && symbol == '"' && line.charAt(i + 1) == '"') {
                i += 1;
                lineResult.append('"');
            } else if (i + 1 < line.length() && symbol == ',' && line.charAt(i + 1) == '"' || i + 1 < line.length() && symbol == '"' && line.charAt(i + 1) == ',') {
                lineResult.append("\n        </td>\n").append("        <td>\n            ");
                i += 1;
            } else if (symbol == '&') {
                lineResult.append("&amp;");
            } else if (symbol == '<') {
                lineResult.append("&lt;");
            } else if (symbol == '>') {
                lineResult.append("&gt;");
            } else if (symbol != '"') {
                lineResult.append(symbol);
            }
        }

        if (quotesCount % 2 != 0) {
            lineResult.append("<br/>");
            quotesCount += countOccurrences(line);
        } else {
            lineResult.append("\n        </td>\n");
            lineResult.append("    </tr>\n");
            quotesCount = 0;
        }

        return lineResult.toString();
    }

    private static int countOccurrences(String str) {
        int quoteCounter = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '"') {
                quoteCounter++;
            }
        }

        return quoteCounter;
    }
}