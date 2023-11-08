package com.rain.test;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfDocumentBase;

public class MergePDF {

    public static void main(String[] args) {

        String[] files = new String[] {
                "E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\871高等代数.pdf",
                "E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\931信号与线性系统.pdf"};
        //Merge documents and return an object of PdfDocumentBase
        PdfDocumentBase pdf = PdfDocument.mergeFiles(files);
        //Save the result to a PDF file
        pdf.save("MergedPDF.pdf", FileFormat.PDF);

    }
}
