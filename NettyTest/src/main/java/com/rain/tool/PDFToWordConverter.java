package com.rain.tool;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFToWordConverter {
    public static void main(String[] args) throws IOException {
        // 加载PDF文件
        try (PDDocument pdfDocument = PDDocument.load(new FileInputStream("E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\2222.pdf"))) {
            // 创建一个PDFTextStripper类的对象
            PDFTextStripper pdfStripper = new PDFTextStripper();
            // 从PDF文档提取文本
            String text = pdfStripper.getText(pdfDocument);

            // 使用Aspose.Words或其他库将文本转换为Word文档
            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph para = doc.createParagraph();
            para.createRun().setText(text);

            // 将文档保存为Word文件
            try (FileOutputStream out = new FileOutputStream("example.docx")) {
                doc.write(out);
            }
        }
    }
}
