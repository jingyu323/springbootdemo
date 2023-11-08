package com.rain.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class MergePDF4 {

    public static void main(String[] args) {
        String[] files = {
                "E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\871高等代数.pdf",
                "E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\931信号与线性系统.pdf"
        }; // 待合并的PDF文件路径
        String resultFileName = "result.pdf"; // 合并后的PDF文件名及路径

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
