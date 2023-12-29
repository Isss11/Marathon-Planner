package com.application.marathonplanner.web;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public class PdfUtils {
    public static ByteArrayOutputStream generatePdfStream(List<Map<String, Object>> queryResults)
            throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open(); // Write column names
        Map<String, Object> firstRow = queryResults.get(0);
        for (String column : firstRow.keySet()) {
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Paragraph paragraph = new Paragraph(column, boldFont);
            document.add(paragraph);
        }
        document.add(new Paragraph("\n")); // Write data rows
        for (Map<String, Object> row : queryResults) {
            for (Object value : row.values()) {
                Paragraph paragraph = new Paragraph(value.toString());
                document.add(paragraph);
            }
            document.add(new Paragraph("\n"));
        }
        document.close();
        return outputStream;
    }
}