//package com.home.Service;
//
//import com.home.entity.Booking;
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.PdfWriter;
//import org.springframework.stereotype.Service;
//
//import java.io.FileOutputStream;
//@Service
//public class PDFService {
//    public void generateBookingDetailsPdf(Booking booking){
//        try {
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream("G:\\pdfService\\booking-confirmation"+booking.getId()+".pdf"));
//
//            document.open();
//            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLUE);
//            Chunk chunk = new Chunk("Hello World", font);
//
//            document.add(chunk);
//            document.close();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        }
//    }


    package com.home.Service;

import com.home.entity.Booking;
import com.itextpdf.text.*;
        import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PDFService {

    public String generateBookingDetailsPdf(Booking booking) {
        Document document = null;
        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("G:\\pdfService\\booking-confirmation" + booking.getId() + ".pdf"));

            document.open();

            // Add title to the PDF
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Booking Confirmation", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add a blank line
            document.add(new Paragraph("\n"));

            // Create a table with 2 columns
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(2f);
            table.setSpacingAfter(2f);

            // Add table headers
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
            PdfPCell header1 = new PdfPCell(new Phrase("Field", headerFont));
            header1.setBackgroundColor(BaseColor.GRAY);
            table.addCell(header1);

            PdfPCell header2 = new PdfPCell(new Phrase("Details", headerFont));
            header2.setBackgroundColor(BaseColor.GRAY);
            table.addCell(header2);

            // Add booking details to the table
            addTableCell(table, "Booking ID", booking.getId().toString());
            addTableCell(table, "Guest Name", booking.getGuestName());
            addTableCell(table, "Total Nights", booking.getTotalNights().toString());
            addTableCell(table, "Booking Date", booking.getBookingDate().toString());
            addTableCell(table, "Check-in Time", booking.getCheckInTime().toString());
            addTableCell(table, "Property ID", booking.getProperty().getId().toString());
            addTableCell(table, "User ID", booking.getAppUser().getId().toString());
            addTableCell(table, "Total Price", booking.getTotalPrice().toString());

            // Add table to document
            document.add(table);
            return "G:\\pdfService\\booking-confirmation" + booking.getId() + ".pdf";
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
        return null;
    }

    private void addTableCell(PdfPTable table, String fieldName, String fieldValue) {
        Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        PdfPCell cell1 = new PdfPCell(new Phrase(fieldName, cellFont));
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase(fieldValue, cellFont));
        table.addCell(cell2);
    }
}

