package com.westboy.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.SneakyThrows;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author pengbo
 * @since 2020/9/21
 */
public class Demo01 {

    @SneakyThrows
    public static void main(String[] args) {
        // Document document = new Document();
        // PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
        //
        // document.open();
        // Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        // Chunk chunk = new Chunk("Hello World", font);
        //
        // document.add(chunk);
        // document.close();


        // Document document = new Document();
        // PdfWriter.getInstance(document, new FileOutputStream("iTextTable.pdf"));
        //
        // document.open();
        //
        // PdfPTable table = new PdfPTable(3);
        // addTableHeader(table);
        // addRows(table);
        // addCustomRows(table);
        //
        // document.add(table);
        // document.close();

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<body style='font-size:20px;font-family:SimSun;'>");
        html.append("<table width='19cm'border='1' cellpadding='0' cellspacing='0'>");
        html.append("<tr>");
        html.append("<td colspan='2'>凉州词</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>黄河远上白云间，一片孤城万仞山。</td>");
        html.append("<td>羌笛何须怨杨柳，春风不度玉门关。</td>");
        html.append("</tr>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        InputStream is = new ByteArrayInputStream(html.toString().getBytes());

        OutputStream os = new FileOutputStream("demo4.pdf");
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document,os);

        document.open();

        // 将html转pdf
        XMLWorkerHelper.getInstance().parseXHtml(writer,document, is);

        document.close();
    }

    private static void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    private static void addCustomRows(PdfPTable table)
            throws URISyntaxException, BadElementException, IOException {
        // Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
        // Image img = Image.getInstance(path.toAbsolutePath().toString());
        // img.scalePercent(10);
        //
        // PdfPCell imageCell = new PdfPCell(img);
        // table.addCell(imageCell);

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }

}
