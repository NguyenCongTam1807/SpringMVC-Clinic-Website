package com.congtam.customer.utils;

import com.congtam.customer.pojos.Checkup;
import com.congtam.customer.pojos.CheckupDetail;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class WritePDF {
    private final int indentation = 52;

    public void write(Checkup checkup, String pdfFilePath){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();
            //Khởi tạo một table có 3 cột
            PdfPTable table = new PdfPTable(3);
            //Khởi tạo 3 ô header
            PdfPCell header1 = new PdfPCell(new Paragraph("Name"));
            PdfPCell header2 = new PdfPCell(new Paragraph("Quantity"));
            PdfPCell header3 = new PdfPCell(new Paragraph("Total"));
            //Thêm 3 ô header vào table
            table.addCell(header1);
            table.addCell(header2);
            table.addCell(header3);

            List<CheckupDetail> list = checkup.getCheckups();

            float sum = 0;
            for (CheckupDetail item : list){
                PdfPCell data1 = new PdfPCell(new Paragraph(String.valueOf(item.getMedicine().getName())));
                PdfPCell data2 = new PdfPCell(new Paragraph(String.valueOf(item.getQuantity())));
                PdfPCell data3 = new PdfPCell(new Paragraph(String.valueOf(item.getTotal())));
                sum += item.getTotal();
                table.addCell(data1);
                table.addCell(data2);
                table.addCell(data3);
            }
            Paragraph p1 = new Paragraph("DON THUOC \n\n", new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
            p1.setAlignment(Element.ALIGN_CENTER);

            Paragraph p2 = new Paragraph(String.format("Ho ten: %s\nEmail: %s\nSDT: %s\n\n",
                    unAccent(checkup.getShift().getName()),
                    checkup.getShift().getEmail(),
                    checkup.getShift().getPhone()
            ));
            p2.setIndentationLeft(indentation);
            
            Paragraph p3 = new Paragraph("\nTONG TIEN: "+ sum +" VND");
            p3.setIndentationRight(indentation);
            p3.setAlignment(Element.ALIGN_RIGHT);

            document.add(p1);
            document.add(p2);
            document.add(table);
            document.add(p3);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        // return pattern.matcher(temp).replaceAll("");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }
}
