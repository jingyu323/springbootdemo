package com.rain.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MergePDF4 {

    public static void main(String[] args) {
        String[] files = {
                "E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\马辉-课题论文.pdf",
                "E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\1_本人论文.pdf",
                "E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\虢萍论文.pdf"
        }; // 待合并的PDF文件路径
        String resultFileName = "merge_file.pdf"; // 合并后的PDF文件名及路径

        try {

            Document document = new Document();

            FileOutputStream fos = new FileOutputStream(resultFileName);

            PdfCopy copy = new PdfCopy(document, fos);

            document.open();

            ArrayList<PdfReader> readerList = new ArrayList<PdfReader>();

            int totalPages = 0;

            for (String file : files) {
                PdfReader reader = new PdfReader(file);
                readerList.add(reader);
                totalPages += reader.getNumberOfPages();
            }

            for (PdfReader reader : readerList) {
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    copy.addPage(copy.getImportedPage(reader, i));
                }
            }

            document.close();
            System.out.println("成功合并" + files.length + "个PDF文件，共计" + totalPages + "页。");


            PdfReader reader = new PdfReader(resultFileName);
            int totalPagesNum = reader.getNumberOfPages();

            String resultFileName2 = "merge_file2.pdf";
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(resultFileName2));
            Rectangle pageSize = null;
            for (int page = 1; page <= totalPages; page++) {
                PdfContentByte content = stamper.getOverContent(page);
                PdfImportedPage importedPage = stamper.getImportedPage(reader, page);

                // 在这里添加页码

                float width = document.getPageSize().getHeight();
                float center = width / 2.0f;


                pageSize = content.getPdfDocument().getPageSize();
               float b = pageSize.getHeight();
                System.out.println(b);

                content.saveState();
                content.setFontAndSize(BaseFont.createFont(), 10);
                content.beginText();
//                content.setTextMatrix(pageSize.getWidth() - 50, pageSize.getHeight() - 170);
                content.setTextMatrix(pageSize.getWidth() /2,  8);
                content.showText(page+"");
//                content.showText("page " + page + " of " + totalPages);
                content.endText();
                content.restoreState();
            }
            stamper.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
