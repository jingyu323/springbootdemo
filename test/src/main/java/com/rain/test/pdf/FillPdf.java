package com.rain.test.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class FillPdf {

    public static void main(String[] args) {

        Map<String, String>  data=new HashMap<>();
        data.put("name","嘉靖");
        data.put("age","18");
        data.put("gender","男");
        data.put("result","轻微感冒");
        data.put("testtxt","testtxtsssssss");
        data.put("toggle_1","On");
        data.put("toggle_2","On");
        data.put("toggle_17","On");
        data.put("toggle_17","On");
        data.put("test","是");
        data.put("suggestion","（1）　\t 收缩压≥180mmHg 和 / 或舒张压≥110mmHg，出现身体不适的症状。\n" +
                "（2）　\t 意识改变、剧烈头痛或头晕、恶心呕吐、视物模糊、眼痛、心悸、胸闷、喘憋不能平卧，建议使用急救车转诊。\n" +
                "（3）　\t 其他严重情况");

        generatePDF("E:\\study\\git\\springbootdemo\\test\\src\\main\\java\\com\\rain\\test\\pdf\\1.高血压患者健康教育处方1.pdf", "E:/study/git/springbootdemo/1.高血压患者健康教育处方1.pdf",  data);

    }


    /**
     * @param fields
     * @param data
     * @throws IOException
     * @throws DocumentException
     */
    private static void fillData(AcroFields fields, Map<String, String> data) throws IOException, DocumentException {
        List<String> keys = new ArrayList<String>();
        Map<String, AcroFields.Item> formFields = fields.getFields();
        for (String key : data.keySet()) {
            if (formFields.containsKey(key)) {
                String value = data.get(key);
                fields.setField(key, value,true); // 为字段赋值,注意字段名称是区分大小写的
                keys.add(key);
            }
        }
        Iterator<String> itemsKey = formFields.keySet().iterator();
        while (itemsKey.hasNext()) {
            String itemKey = itemsKey.next();
            if (!keys.contains(itemKey)) {
                fields.setField(itemKey, " ");
            }
        }
    }
    /**
     * @param templatePdfPath 模板pdf路径
     * @param generatePdfPath 生成pdf路径
     * @param data            数据
     */
    public static String generatePDF(String templatePdfPath, String generatePdfPath, Map<String, String> data) {
        FileOutputStream fos = null;
        ByteArrayOutputStream bos = null;
        try {
            PdfReader reader = new PdfReader(templatePdfPath);
            bos = new ByteArrayOutputStream();
            /* 将要生成的目标PDF文件名称 */
            PdfStamper ps = new PdfStamper(reader, bos);

            BaseFont bf = BaseFont.createFont("C:\\Windows\\Fonts\\simkai.ttf",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            AcroFields s = ps.getAcroFields();

            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
            fontList.add(bf);
            /* 取出报表模板中的所有字段 */
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            

            fillData(fields, data);
            /* 必须要调用这个，否则文档不会生成的  如果为false那么生成的PDF文件还能编辑，一定要设为true*/
            ps.setFormFlattening(true);

            ps.close();
            fos = new FileOutputStream(generatePdfPath);
            fos.write(bos.toByteArray());
            fos.flush();
            return generatePdfPath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
