package com.rain.tool.pdf;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.SaveFormat;
import com.aspose.pdf.devices.PngDevice;
import com.aspose.pdf.devices.Resolution;
import com.itextpdf.text.exceptions.UnsupportedPdfException;
import com.itextpdf.text.pdf.*;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;


/***
 * 能保留格式 不添加额外水印  但是文档中有水印不会去除
 * 下一步考虑怎去除文档中的水印
 */
public class Pdf2Word {
    public static void main(String[] args) throws IOException {
//        pdf2doc("E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\mycat2映射关系.pdf");
        removeWaterMark("E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\mycat2映射关系.pdf");
//        pdf2ppt("E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\mycat2映射关系.pdf");
//        pdf2excel("E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\mycat2映射关系.pdf");
//        pdf2html("E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\mycat2映射关系.pdf");
//        pdf2imges( "E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\mycat2映射关系.pdf");

    }


    public static void extractImage(String filename){

        PdfReader reader = null;
        try {
            //读取pdf文件
            reader = new PdfReader(filename);
            //获得pdf文件的页数
            int sumPage = reader.getNumberOfPages();
            //读取pdf文件中的每一页
            for(int i = 1;i <= sumPage;i++){
                //得到pdf每一页的字典对象
                PdfDictionary dictionary = reader.getPageN(i);
                //通过RESOURCES得到对应的字典对象
                PdfDictionary res = (PdfDictionary) PdfReader.getPdfObject(dictionary.get(PdfName.RESOURCES));
                //得到XOBJECT图片对象
                PdfDictionary xobj = (PdfDictionary) PdfReader.getPdfObject(res.get(PdfName.XOBJECT));
                if(xobj != null){
                    for(Iterator it = xobj.getKeys().iterator();it.hasNext();){
                        PdfObject obj = xobj.get((PdfName)it.next());
                        if(obj.isIndirect()){
                            PdfDictionary tg = (PdfDictionary) PdfReader.getPdfObject(obj);
                            PdfName type = (PdfName) PdfReader.getPdfObject(tg.get(PdfName.SUBTYPE));
                            if(PdfName.IMAGE.equals(type)){
                                PdfObject object =  reader.getPdfObject(obj);
                                if(object.isStream()){
                                    PRStream prstream = (PRStream)object;
                                    byte[] b;
                                    try{
                                        b = reader.getStreamBytes(prstream);
                                    }catch(UnsupportedPdfException e){
                                        b = reader.getStreamBytesRaw(prstream);
                                    }
                                    FileOutputStream output = new FileOutputStream(String.format("d:/pdf/output%d.jpg",i));
                                    output.write(b);
                                    output.flush();
                                    output.close();
                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            File file = new File(pdfPath);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            //1 先把文本获取到
            PDDocument document = PDDocument.load(file);
            String text= pdfStripper.getText(document);
            pdfStripper.setParagraphStart("\r\n");
            pdfStripper.setSortByPosition(true);
            //2 按行分割出文本
            String[] split = text.split(pdfStripper.getParagraphStart());
            document.close();//及时关闭流
            List<String> list = Arrays.asList(split);
            StringBuilder chineseBuilder = new StringBuilder();
            //3 正则匹配中文 目前适合去匹配中文文件。测试过，如果不取中文的话，一行可能由....组成的话，一行能装的字符串的长度就比较大，目标是获取一行能做最大的中文多少个
            Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]+");
            //Matcher matcher = null;
            List<String> cNStrList = list.stream().map(v -> {
                Matcher matcher = pattern.matcher(v);
                while (matcher.find()) {
                    chineseBuilder.append(matcher.group());
                }
                String s = chineseBuilder.toString();
                chineseBuilder.setLength(0);
                return s;
            }).collect(Collectors.toList());
            // 4 取出最长中文行的长度
            Optional<String> longest = cNStrList.stream().max(Comparator.comparingInt(String::length));
            // 输出最长的字符串
            String maxStr = longest.get();
            int length = maxStr.length();
            double bl = 0.8; //参数可以自己优化设定！！！
            List<String> duanList = new ArrayList<>();
            String duan = "";
            // 按行处理原文
// 目前设定为，一行中，如果大于最大中文数量的0.8，则认为这段落没结束，如果下行还是大于，就继续认为属于这段，否则就认为这段要结束了。
            //这种设定依然不是很准确，但是已经可以分割出一些段落
            //这样处理是，因为目前（2024年04）合适的对PDF文件进行分割
            for (int i =0 ; i < split.length ; i++)
            {
                //System.out.println("line----" + i  + "------" + split[i]);
                if(split[i].length() >= length * bl){
                    duan += split[i];
                    if(i < split.length){
                        if(split[i+1].length() >= length * bl){

                        }else{
                            duan += split[i+1];
                            duanList.add(duan);
                            duan = "";
                            i++;//已经处理过i+1了，所以手动+1
                        }
                    }
                }else {
                    duan = split[i];
                    duanList.add(duan);
                    duan = "";
                }
            }
            for (String s : duanList) {
                System.out.println("duan-------" +  s);
            }

//            removeWaterMark();
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
    public static void  removeWaterMark(String pdfPath){

        try {
            PDDocument document = PDDocument.load(new File(pdfPath));

            for (PDPage page : document.getPages()) {
                PDResources resources = page.getResources();
                // 检查是否存在水印
                if (resources != null) {
                    for (COSName key : resources.getXObjectNames()) {
                        PDXObject xObject = resources.getXObject(key);
                        if (xObject instanceof PDImageXObject) {
                            PDImageXObject imageXObject = (PDImageXObject) xObject;
                            // 检查是否为水印
//                            if (isWatermarkImage(imageXObject)) {
//                                // 移除水印
//                                resources.removeXObject(key);
//                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeWatermark(PDDocument document) {
        for (PDPage page : document.getPages()) {
            // 创建内容流，以便修改页面内容
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true)) {

                // 这里需要编写删除水印文本的相关逻辑
                // 假设水印是 "Sample Watermark"
                // ... 实现逻辑 ...
            } catch (IOException e) {
                e.printStackTrace(); // 输出异常信息
            }
        }
    }

    public static void pdf2ppt(String pdfPath){
        long old = System.currentTimeMillis();
        String wordPath=pdfPath.substring(0,pdfPath.lastIndexOf("."))+".ppt";
        try {
            FileOutputStream os = new FileOutputStream(wordPath);
            Document doc  = new Document(pdfPath);
            doc.save(os, SaveFormat.Pptx);
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("Pdf 转 ppt 共耗时：" + ((now - old) / 1000.0) + "秒");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void pdf2excel(String pdfPath){
        long old = System.currentTimeMillis();
        String wordPath=pdfPath.substring(0,pdfPath.lastIndexOf("."))+".xlsx";
        try {
            FileOutputStream os = new FileOutputStream(wordPath);
            Document doc  = new Document(pdfPath);
            doc.save(os, SaveFormat.Excel);
            os.close();
            long now = System.currentTimeMillis();
            System.out.println("Pdf 转 excel 共耗时：" + ((now - old) / 1000.0) + "秒");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void pdf2html(String pdfPath){
        long old = System.currentTimeMillis();
        String wordPath=pdfPath.substring(0,pdfPath.lastIndexOf("."))+".html";

        Document doc  = new Document(pdfPath);
        doc.save(wordPath, SaveFormat.Html);

        long now = System.currentTimeMillis();
        System.out.println("Pdf 转 html 共耗时：" + ((now - old) / 1000.0) + "秒");

    }
    public static void pdf2imges(String pdfPath){
        long old = System.currentTimeMillis();
        Resolution resolution = new Resolution(300);
        String wordPath=pdfPath.substring(0,pdfPath.lastIndexOf("."));
        File imageDir = new File(wordPath+"_images");
        imageDir.mkdirs();
        try {

            Document doc  = new Document(pdfPath);
            PngDevice pngDevice  = new PngDevice(resolution);
            for (int pageCount = 1; pageCount <= doc.getPages().size(); pageCount++){
                OutputStream imageStream  = new FileOutputStream(imageDir+"/"+pageCount+".png");
                pngDevice.process(doc.getPages().get_Item(pageCount), imageStream);
                imageStream.close();
            }

            long now = System.currentTimeMillis();
            System.out.println("Pdf 转 png 共耗时：" + ((now - old) / 1000.0) + "秒");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
