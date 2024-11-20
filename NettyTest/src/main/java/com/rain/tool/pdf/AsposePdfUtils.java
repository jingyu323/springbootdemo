package com.rain.tool.pdf;
import com.aspose.pdf.Document;
public class AsposePdfUtils {
    public static void optimize(String source, String target) {
        Document doc = new Document(source);
        //设置压缩属性
        Document.OptimizationOptions opt = new Document.OptimizationOptions();
        //删除PDF不必要的对象
        opt.setRemoveUnusedObjects(true);
        //链接重复流
        opt.setLinkDuplcateStreams(false);
        //删除未使用的流
        opt.setRemoveUnusedStreams(false);
        //删除不必要的字体
        opt.setUnembedFonts(true);
        //压缩PDF中的图片
        opt.setCompressImages(true);
        //图片压缩比， 0 到100可选，越低压缩比越大
        opt.setImageQuality(10);
        doc.optimizeResources(opt);
        //优化web的PDF文档
        doc.optimize();
        doc.save(target);
        doc.close();
    }

    public static void main(String[] args) {
        String source = "D:/data/pdf/source/1.pdf";
        String target = "D:/data/pdf/target/1.pdf";
        optimize(source, target);
        System.out.println("转换完成");
    }
}
