package com.application.marathonplanner.web;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfUtils {
    public static ByteArrayOutputStream generatePdfStream(List<DayPlan> trainingSchedule)
            throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // add header
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Paragraph paragraph = new Paragraph("Marathon Training Plan", boldFont);
        document.add(paragraph);

        // appending each day's training plan to the schedule
        for (DayPlan dailyPlan : trainingSchedule) {
            Paragraph dayHeader;

            if (Double.compare(dailyPlan.getDistance(), 0.0) != 0) {
                dayHeader = new Paragraph(
                        String.format("%s: Run: %1.1f kilometers.\n", dailyPlan.getDate(), dailyPlan.getDistance()));

            } else {
                dayHeader = new Paragraph(String.format("%s: Rest day.\n", dailyPlan.getDate()));
            }

            document.add(dayHeader);
        }

        document.close();
        return outputStream;
    }
}