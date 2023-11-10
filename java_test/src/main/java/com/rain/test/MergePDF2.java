package com.rain.test;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfDocumentBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MergePDF2 {

    public static void main(String[] args) throws FileNotFoundException {

        String[] files = new String[] {
                "E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\871高等代数.pdf",
                "E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\931信号与线性系统.pdf"};
        //Merge documents and return an object of PdfDocumentBase
        FileInputStream stream1 = new FileInputStream(new File("E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\871高等代数.pdf"));
        FileInputStream stream2 = new FileInputStream(new File("E:\\git_project\\java\\springbootdemo\\java_test\\src\\main\\resources\\pdf\\931信号与线性系统.pdf"));
        InputStream[] streams = new FileInputStream[]{stream1, stream2};
        //Merge these documents and return an object of PdfDocumentBase
        PdfDocumentBase pdf = PdfDocument.mergeFiles(streams);
        //Save the result to a PDF file
        pdf.save("MergedPDF2.pdf", FileFormat.PDF);

    }
}
