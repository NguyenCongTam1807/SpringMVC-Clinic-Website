package com.clinic.customer.utils;

import com.clinic.customer.entity.Checkup;
import com.clinic.customer.entity.CheckupDetail;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WritePDF {

    public void write(Checkup oder, String excelFilePath){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(excelFilePath));
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

            List<CheckupDetail> list = oder.getCheckups();

            float sum = 0;
            for (CheckupDetail item : list){
                PdfPCell data1 = new PdfPCell(new Paragraph("huan"));
                PdfPCell data2 = new PdfPCell(new Paragraph(String.valueOf(item.getQuantity())));
                PdfPCell data3 = new PdfPCell(new Paragraph(String.valueOf(item.getTotal())));
                sum += item.getTotal();
                table.addCell(data1);
                table.addCell(data2);
                table.addCell(data3);
            }
            Paragraph paragraph1 = new Paragraph("HOA DON BAN HANG \n\n\n");
            paragraph1.setIndentationLeft(100);
            paragraph1.setIndentationRight(100);
            paragraph1.setAlignment(Element.ALIGN_CENTER);

            Paragraph paragraph2 = new Paragraph("Tong tien:"+String.valueOf(sum)+"VND");
            paragraph2.setIndentationLeft(52);
            paragraph2.setIndentationRight(80);
            paragraph2.setAlignment(Element.ALIGN_LEFT);

            document.add(paragraph1);
            document.add(table);
            document.add(paragraph2);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
