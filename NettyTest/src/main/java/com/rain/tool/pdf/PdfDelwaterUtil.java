package com.rain.tool.pdf;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

public class PdfDelwaterUtil {
    private static final String KEY_WORD = "QQ:294712221";
    private static final String KEY_WORD_NEW = "";

    public static void main(String[] args) throws Exception {
        String source = "C:\\Users\\Administrator\\Desktop\\teds\\22223.pdf";
        String filename ="E:\\study\\git\\springbootdemo\\NettyTest\\src\\main\\java\\com\\rain\\tool\\mycat2映射关系.pdf";
        String target = "C:\\Users\\Administrator\\Desktop\\teds\\22223445.pdf";
        System.out.println("开始");
        pdfReplace(filename, target, KEY_WORD, KEY_WORD_NEW);
        System.out.println("结束");
    }

    /**
     * 根据关键字和pdf路径，全文搜索关键字
     * @param filePath pdf目标路径
     * @param keyword  关键字
     * @return
     * @throws Exception
     */
    public static List<MatchItem> matchAll(String filePath, String keyword) throws Exception {
        List<MatchItem> items = new ArrayList<MatchItem>();
        PdfReader reader = new PdfReader(filePath);
        //获取pdf页数
        int pageSize = reader.getNumberOfPages();
        //逐页匹配关键字
        for (int page = 1; page <= pageSize; page++) {
            items.addAll(matchPage(reader, page, keyword));
        }
        return items;
    }

    /**
     * 根据关键字、文档路径、pdf页数寻找特定的文件内容
     * @param reader
     * @param pageNumber 页数
     * @param keyword    关键字
     * @return
     * @throws Exception
     */
    public static List<MatchItem> matchPage(PdfReader reader, Integer pageNumber, String keyword) throws Exception {
        PdfReaderContentParser parse = new PdfReaderContentParser(reader);
        Rectangle rectangle = reader.getPageSize(pageNumber);
        //匹配监听
        KeyWordPositionListener renderListener = new KeyWordPositionListener();
        renderListener.setKeyword(keyword);
        renderListener.setPageNumber(pageNumber);
        renderListener.setCurPageSize(rectangle);
        parse.processContent(pageNumber, renderListener);
        return findKeywordItems(renderListener, keyword);
    }

    /**
     * 找到匹配的关键词块
     * @param renderListener
     * @param keyword
     * @return
     */
    public static List<MatchItem> findKeywordItems(KeyWordPositionListener renderListener, String keyword) {
        //先判断本页中是否存在关键词，所有块LIST
        List<MatchItem> allItems = renderListener.getAllItems();
        StringBuffer sbtemp = new StringBuffer("");
        //将一页中所有的块内容连接起来组成一个字符串。
        for (MatchItem item : allItems) {
            sbtemp.append(item.getContent());
        }
        List<MatchItem> matches = renderListener.getMatches();
        //一页组成的字符串没有关键词，直接return
        //第一种情况：关键词与块内容完全匹配的项,直接返回
        if (sbtemp.toString().indexOf(keyword) == -1 || matches.size() > 0) {
            return matches;
        }
        //第二种情况：多个块内容拼成一个关键词，则一个一个来匹配，组装成一个关键词
        sbtemp = new StringBuffer("");
        List<MatchItem> tempItems = new ArrayList();
        for (MatchItem item : allItems) {
            if (keyword.indexOf(item.getContent()) != -1) {
                tempItems.add(item);
                sbtemp.append(item.getContent());
                //如果暂存的字符串和关键词 不再匹配时
                if (keyword.indexOf(sbtemp.toString()) == -1) {
                    sbtemp = new StringBuffer(item.getContent());
                    tempItems.clear();
                    tempItems.add(item);
                }
                //暂存的字符串正好匹配到关键词时
                if (sbtemp.toString().equalsIgnoreCase(keyword)) {
                    //得到匹配的项
                    matches.add(tempItems.get(0));
                    //清空暂存的字符串
                    sbtemp = new StringBuffer("");
                    //清空暂存的LIST
                    tempItems.clear();
                    //继续查找
                    continue;
                }
            } else {
                //如果找不到则清空
                sbtemp = new StringBuffer("");
                tempItems.clear();
            }
        }
        return matches;
    }

    /**
     * 替换目标文字，生成新的pdf文件
     * @param src  目标pdf路径
     * @param dest 新pdf的路径
     * @throws Exception
     */
    public static void manipulatePdf(String src, String dest, List<MatchItem> matchItems, String keyWord, String keyWordNew) throws Exception {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfContentByte canvas = null;
        Map<Integer, List<MatchItem>> mapItem = new HashMap<Integer, List<MatchItem>>();
        List<MatchItem> itemList = null;
        for (MatchItem item : matchItems) {
            Integer pageNum = item.getPageNum();
            if (mapItem.containsKey(pageNum)) {
                itemList = mapItem.get(pageNum);
                itemList.add(item);
                mapItem.put(pageNum, itemList);
            } else {
                itemList = new ArrayList<MatchItem>();
                itemList.add(item);
                mapItem.put(pageNum, itemList);
            }
        }
        //遍历每一页去修改
        for (Integer page : mapItem.keySet()) {
            List<MatchItem> items = mapItem.get(page);
            //遍历每一页中的匹配项
            for (MatchItem item : items) {
                canvas = stamper.getOverContent(page);
                BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
                float fontWidth = item.getFontWidth();
                Font font = new Font(bf, fontWidth, Font.BOLD);
                //设置字体和大小
                canvas.setFontAndSize(font.getBaseFont(), fontWidth);


                float x = item.getX();
                float y = item.getY();
                canvas.showTextAligned(Element.ALIGN_LEFT, "eeee", x, y, -6);


                canvas.saveState();
                canvas.setColorFill(BaseColor.WHITE);

//                canvas.rotate(30);

                canvas.rectangle(x, y, fontWidth * keyWord.length()*2+10, fontWidth +24);



                canvas.fill();
                canvas.restoreState();
                //开始写入文本
                canvas.beginText();


                //设置字体的输出位置
                canvas.setTextMatrix(x, y + fontWidth / 6 + 4.8f);
                //要输出的text
                canvas.showText(" ");
                canvas.endText();
                canvas.saveState();
            }
        }
        stamper.close();
        reader.close();
    }

    /**
     * 替换pdf中指定文字
     * @param src        目标pdf路径
     * @param dest       新pdf的路径
     * @param keyWord    替换的文字
     * @param keyWordNew 替换后的文字
     * @throws Exception
     */
    public static void pdfReplace(String src, String dest, String keyWord, String keyWordNew) throws Exception {
        manipulatePdf(src, dest, matchAll(src, keyWord), keyWord, keyWordNew);
    }
}
