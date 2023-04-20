package com.rain.test.tool.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel表格读取工具类
 * 88537983
 */
public class POIUtils {

    private static final Logger logger = LoggerFactory.getLogger(POIUtils.class);

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    private final static String DATE_FORMAT = "yyyy/MM/dd";

    /**
     * 读入excel文件，解析后返回
     *
     * @param file
     */
    public static List<List> readExcel(MultipartFile file) {
        //检查文件
        String fileName = checkFile(file);
        //获取文件输入流对象
        InputStream is = null;
        try {
            is = file.getInputStream();
            //获得Workbook工作薄对象
            Workbook workbook = getWorkBook(fileName, is);
            //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
            List<List> list = new ArrayList<>();
            if (workbook != null) {
                for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                    //获得当前sheet工作表
                    Sheet sheet = workbook.getSheetAt(sheetNum);
                    if (sheet == null) {
                        continue;
                    }
                    //获得当前sheet的开始行
                    int firstRowNum = sheet.getFirstRowNum();
                    //获得当前sheet的结束行
                    int lastRowNum = sheet.getLastRowNum();
                    // 模板中的最大固定列数
                    int maxRowSize = 0;
                    //循环除了第一行的所有行

                    for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                        //获得当前行
                        Row row = sheet.getRow(rowNum);
                        if (row == null) {
                            continue;
                        }
                        //获得当前行的开始列
                        int firstCellNum = row.getFirstCellNum();
                        //获得当前行的列数
                        int lastCellNum = row.getLastCellNum();
                        if (rowNum == 0) {
                            maxRowSize = lastCellNum;
                        }
                        List cells = new ArrayList(lastCellNum);
                        //循环当前行
                        for (int cellNum = firstCellNum; cellNum < maxRowSize; cellNum++) {
                            Cell cell = row.getCell(cellNum);
                            cells.add(getCellValue(cell));
                        }
                        list.add(cells);
                    }
                }
            }
            return list;
        } catch (IOException e) {
            logger.error("POIUtils readExcel method is fail, e:{}", e);
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("POIUtils close fileInputStream is fail, e:{}", e);
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //校验文件是否合法
    public static String checkFile(MultipartFile file) {
        //判断文件是否存在
        if (null == file) {
            logger.info("POIUtils checkFile fail, because file is null");
            return null;
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.info("POIUtils checkFile fail, because file is not excel file, fileName:{}", fileName);
            return null;
        }
        return fileName;
    }

    public static Workbook getWorkBook(String fileName, InputStream is) {
        if (fileName == null) {
            return null;
        }
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                //2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(xlsx)) {
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            logger.error("POIUtils getWorkBook method is fail, because e:{}", e);
            e.printStackTrace();
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }

        //处理日期格式
        //特殊处理当 excel为 日期格式时的处理
        if ("yyyy/mm;@".equals(cell.getCellStyle().getDataFormatString())
                || "m/d/yy".equals(cell.getCellStyle().getDataFormatString())
                || "yy/m/d".equals(cell.getCellStyle().getDataFormatString())
                || "mm/dd/yy".equals(cell.getCellStyle().getDataFormatString())
                || "dd-mmm-yy".equals(cell.getCellStyle().getDataFormatString())
                || "yyyy/m/d".equals(cell.getCellStyle().getDataFormatString())
                || "yyyy/mm/dd".equals(cell.getCellStyle().getDataFormatString())) {
            if (DateUtil.isCellDateFormatted(cell)) {
                // 用于转化为日期格式
                Date d = cell.getDateCellValue();
                cellValue = new SimpleDateFormat(DATE_FORMAT).format(d);
                return cellValue;
            } else {
                return cell.toString();
            }
        }

        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    //org.apache.commons.lang.StringUtils
    public static boolean isValidIpAddress(String ipAddress) {
        if (StringUtils.isEmpty(ipAddress)) return false;
        String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        return ipAddress.matches(regex);
    }

    public static void writeExcel(List<List> list, String filepath) throws IOException {
        XSSFWorkbook xwb = new XSSFWorkbook();
        // 创建sheet
        XSSFSheet sheet = xwb.createSheet("导入结果");

        for (int i = 0; i < list.size(); i++) {
            // 创建行
            XSSFRow row = sheet.createRow(i);
            List rowInfo = list.get(i);
            for (int j = 0; j < rowInfo.size(); j++) {
                XSSFCell cell = row.createCell(j);
                if (StringUtils.isEmpty(rowInfo.get(j))) {
                    cell.setCellValue(" ");
                } else {
                    cell.setCellValue(String.valueOf(rowInfo.get(j)));
                }
//                if(i ==0){
//                    XSSFCellStyle style = xwb.createCellStyle();
//                    style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
//                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//
//                    cell.setCellStyle(style);
//                }
            }
        }
        // 生成Excel
        FileOutputStream out = new FileOutputStream(filepath);
        xwb.write(out);
        out.close();
    }

}





