package ru.academits.java.zuban.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    public void conversionFileCVSToHTML(String link, String fileNew) {
        checkNotNull(link);

        List<StringBuilder> data = new ArrayList<>();

        readingCVS(link, data);

        writingHTML(fileNew, data);
    }

    public static void readingCVS(String link, List<StringBuilder> sheetRecord) {
        try (BufferedReader cvsReader = new BufferedReader(new FileReader(link))) {
            String lineCVS;
            StringBuilder stringBuilder = new StringBuilder();

            while ((lineCVS = cvsReader.readLine()) != null) {
                stringBuilder.append(lineCVS);

                if ((countOccurrences(stringBuilder.toString(), '"') % 2) != 0) {
                    stringBuilder.append("\\n");
                } else {
                    sheetRecord.add(stringBuilder);
                    stringBuilder = new StringBuilder();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writingHTML(String nameFile, List<StringBuilder> sheetRecord) {
        try (BufferedWriter htmlWriting = new BufferedWriter(new FileWriter(nameFile))) {
            htmlWriting.write("<meta charset=\"UTF-8\">");
            htmlWriting.write("<table>");

            for (StringBuilder line : sheetRecord) {
                htmlWriting.write("<tr>");
                String[] cells = parseRow(line.toString());

                for (int i = 0; i < cells.length; i++) {
                    htmlWriting.write("<td>");
                    htmlWriting.write(checkingForbiddenCharacters(cells[i]));
                    htmlWriting.write("</td>");
                }

                htmlWriting.write("</tr>");
            }


            htmlWriting.write("</table>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String checkingForbiddenCharacters(String cell) {
        String cellNew = cell;

        if (cell.indexOf('&') != -1) {
            cellNew = cell.replace("&", "&amp");
        }

        if (cell.contains("\\n")) {
            cellNew = cell.replace("\\n", "<br/>");
        }

        if (cell.indexOf('<') != -1) {
            cellNew = cell.replace("<", "&lt");
        }

        if (cell.indexOf('>') != -1) {
            cellNew = cell.replace(">", "&gt");
        }

        return cellNew;
    }

    private static String[] parseRow(String line) {
        boolean creatingNewCell = false;

        StringBuilder cellCurrent = new StringBuilder();
        List<String> cells = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                if (creatingNewCell && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    cellCurrent.append(c);
                    i++;
                } else {
                    creatingNewCell = !creatingNewCell;
                }
            } else if (c == ',' && !creatingNewCell) {
                cells.add(cellCurrent.toString());
                cellCurrent.setLength(0);
            } else {
                cellCurrent.append(c);
            }
        }

        cells.add(cellCurrent.toString());
        cellCurrent.setLength(0);

        return cells.toArray(new String[0]);
    }

    private static void checkNotNull(String link) {
        if (link == null) {
            throw new NullPointerException("Переданный объект равен null");
        }
    }

    private static int countOccurrences(String str, char ch) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                counter++;
            }
        }

        return counter;
    }
}