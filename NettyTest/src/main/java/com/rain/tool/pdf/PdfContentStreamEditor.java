package com.rain.tool.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.exceptions.UnsupportedPdfException;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PdfContentStreamEditor extends PdfContentStreamProcessor {
    public static void main(String[] args) {

            String filename ="E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\mycat2映射关系.pdf";
//            extractImage(filename);
        removeWaterMarks(filename);


    }

    public static void removeWaterMark(String filename )   {
        PdfReader reader = null;
        try {
            reader = new PdfReader(  filename );
            OutputStream result = new FileOutputStream(new File("out.pdf"));
            PdfStamper pdfStamper = new PdfStamper(reader, result);
            PdfContentStreamEditor identityEditor = new PdfContentStreamEditor();
            for(int i = 1;i <= reader.getNumberOfPages();i++){
                identityEditor.editPage(pdfStamper, i);
            }
            pdfStamper.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }


    }
    /**
     * This method edits the immediate contents of a page, i.e. its content stream.
     * It explicitly does not descent into form xobjects, patterns, or annotations.
     */
    public void editPage(PdfStamper pdfStamper, int pageNum) throws IOException
    {
        PdfReader pdfReader = pdfStamper.getReader();
        PdfDictionary page = pdfReader.getPageN(pageNum);
        byte[] pageContentInput = ContentByteUtils.getContentBytesForPage(pdfReader, pageNum);
        page.remove(PdfName.CONTENTS);
        editContent(pageContentInput, page.getAsDict(PdfName.RESOURCES), pdfStamper.getUnderContent(pageNum));
    }

    /**
     * This method processes the content bytes and outputs to the given canvas.
     * It explicitly does not descent into form xobjects, patterns, or annotations.
     */
    public void editContent(byte[] contentBytes, PdfDictionary resources, PdfContentByte canvas)
    {
        this.canvas = canvas;
        processContent(contentBytes, resources);
        this.canvas = null;
    }
    /**
     * <p>
     * This method writes content stream operations to the target canvas. The default
     * implementation writes them as they come, so it essentially generates identical
     * copies of the original instructions the {@link ContentOperatorWrapper} instances
     * forward to it.
     * </p>
     * <p>
     * Override this method to achieve some fancy editing effect.
     * </p>
     */
    protected void write(PdfContentStreamProcessor processor, PdfLiteral operator, List<PdfObject> operands) throws IOException
    {
        int index = 0;

        for (PdfObject object : operands)
        {
            object.toPdf(canvas.getPdfWriter(), canvas.getInternalBuffer());
            canvas.getInternalBuffer().append(operands.size() > ++index ? (byte) ' ' : (byte) '\n');
        }
    }

    //
    // constructor giving the parent a dummy listener to talk to
    //
    public PdfContentStreamEditor()
    {
        super(new DummyRenderListener());
    }

    //
    // Overrides of PdfContentStreamProcessor methods
    //
    @Override
    public ContentOperator registerContentOperator(String operatorString, ContentOperator operator)
    {
        ContentOperatorWrapper wrapper = new ContentOperatorWrapper();
        wrapper.setOriginalOperator(operator);
        ContentOperator formerOperator = super.registerContentOperator(operatorString, wrapper);
        return formerOperator instanceof ContentOperatorWrapper ? ((ContentOperatorWrapper)formerOperator).getOriginalOperator() : formerOperator;
    }

    @Override
    public void processContent(byte[] contentBytes, PdfDictionary resources)
    {
        this.resources = resources;
        super.processContent(contentBytes, resources);
        this.resources = null;
    }

    //
    // members holding the output canvas and the resources
    //
    protected PdfContentByte canvas = null;
    protected PdfDictionary resources = null;

    //
    // A content operator class to wrap all content operators to forward the invocation to the editor
    //
    class ContentOperatorWrapper implements ContentOperator
    {
        public ContentOperator getOriginalOperator()
        {
            return originalOperator;
        }

        public void setOriginalOperator(ContentOperator originalOperator)
        {
            this.originalOperator = originalOperator;
        }

        @Override
        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList<PdfObject> operands) throws Exception
        {
            if (originalOperator != null && !"Do".equals(operator.toString()))
            {
                originalOperator.invoke(processor, operator, operands);
            }
            write(processor, operator, operands);
        }

        private ContentOperator originalOperator = null;
    }

    //
    // A dummy render listener to give to the underlying content stream processor to feed events to
    //
    static class DummyRenderListener implements RenderListener
    {
        @Override
        public void beginTextBlock() { }

        @Override
        public void renderText(TextRenderInfo renderInfo) { }

        @Override
        public void endTextBlock() { }

        @Override
        public void renderImage(ImageRenderInfo renderInfo) { }
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
                    for(Iterator it = xobj.getKeys().iterator(); it.hasNext();){
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

    public static void  removeWaterMarks(String filename){
        try {
            PdfReader pdfReader = new PdfReader(filename);
            FileOutputStream os = new FileOutputStream("d:/reader.pdf");
            PdfStamper stamper = new PdfStamper(pdfReader,os);
            PdfContentStreamEditor editor = new PdfContentStreamEditor(){
                @Override
                protected void write(PdfContentStreamProcessor processor, PdfLiteral operator, List<PdfObject> operands)
                        throws IOException {
                    String operatorString = operator.toString();
                    //Tj 操作通过当前的字体和其他文字相关的图形状态参数来取走一串操作和绘制相应的字形
                    //Tr操作设置的文本渲染模式
                    //一个文本对象开始于BT，结束于ET
                    final List<String> TEXT_SHOWING_OPERATORS = Arrays.asList("Tj","'","\\","TJ","Q");
                    System.out.println(operatorString);
                    if(TEXT_SHOWING_OPERATORS.contains(operatorString)){

                        PdfDictionary dic = gs().getFont().getFontDictionary();
                        if(gs().getFont().getPostscriptFontName().endsWith("BoldMT")){//BoldMT字体的名称
                            return;
                        }
                    }
                    super.write(processor, operator, operands);
                }
            };
            for(int i = 1;i <= pdfReader.getNumberOfPages();i++){
                editor.editPage(stamper, i);
            }
            stamper.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
