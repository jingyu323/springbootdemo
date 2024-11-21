package com.rain.tool.pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.SaveFormat;


public class Pdf2Word {
    public static void main(String[] args) throws IOException {
        pdf2doc("E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\2222.pdf");
    }

    //pdf转doc
    public static void pdf2doc(String pdfPath) {
        long old = System.currentTimeMillis();
        try {
            //新建一个word文档
            String wordPath=pdfPath.substring(0,pdfPath.lastIndexOf("."))+".docx";
            FileOutputStream os = new FileOutputStream(wordPath);
            //doc是将要被转化的word文档
            Document doc = new Document(pdfPath);
            for (int i = 1; i <= doc.getPages().size(); i++)
            {
                Page page  = doc.getPages().get_Item(i);
                System.out.println(page);
            }


            System.out.println(doc.getPages().size());
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.DocX);
            os.close();
            //转化用时
            long now = System.currentTimeMillis();
            System.out.println("Pdf 转 Word 共耗时：" + ((now - old) / 1000.0) + "秒");
        } catch (Exception e) {
            System.out.println("Pdf 转 Word 失败...");
            e.printStackTrace();
        }
    }
}
