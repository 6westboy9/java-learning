package com.westboy.pdf;

import cn.hutool.core.io.FileUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.SneakyThrows;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static cn.hutool.core.util.CharsetUtil.UTF_8;

/**
 * @author pengbo
 * @since 2020/9/21
 */
public class FreemarkerPDFDemo {


    private static final String path = "templates";

    @SneakyThrows
    public static void main(String[] args) {
        // Map<String, String> data = new HashMap<>();
        // data.put("name", "xiaowang");
        //
        // String htmlString = getHtmlContent("demo", data);
        // System.out.println(htmlString);
        // convertHtmlToPdf(htmlString, "demo.pdf");

        System.out.println("demo.pddf".lastIndexOf(".pdf"));
    }

    @SneakyThrows
    private static void convertHtmlToPdf(String htmlString, String pdfName) {
        BufferedOutputStream outputStream = FileUtil.getOutputStream(FileUtil.newFile(pdfName));
        //设置文档大小
        Rectangle rectangle = new Rectangle(PageSize.A5);
        Document document = new Document(rectangle.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        //输出为PDF文件
        document.open();
        try {
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(htmlString.getBytes()));
        } finally {
            document.close();
        }
    }

    @SneakyThrows
    public static String getHtmlContent(String fileName, Object data) {
        String templateFileName = getTemplateName(fileName);
        String templateFilePath = getTemplatePath(templateFileName);
        Configuration config = new Configuration(Configuration.VERSION_2_3_25);
        config.setDefaultEncoding(UTF_8);
        config.setDirectoryForTemplateLoading(new File(templateFilePath));
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        config.setLogTemplateExceptions(false);
        Template template = config.getTemplate(templateFileName);
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        writer.flush();
        return writer.toString();
    }

    private static String getTemplateName(String fileName) {
        return fileName + ".ftl";
    }

    private static String getTemplatePath(String fileName) {
        if ( FileUtil.exist(path + File.separator + fileName)){

        }
        return "不存在";
    }
}
