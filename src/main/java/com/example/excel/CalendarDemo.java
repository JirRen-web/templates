package com.example.excel;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CalendarDemo {
	private static final String[] titles = {
        "序号", "项目", "楼栋",
        "单元", "房间", "姓名", "费项",
        "金额","期间","缴费日期","支付方式"};

private static final String[]  months = {
        "January", "February", "March","April", "May", "June","July", "August",
        "September","October", "November", "December"};

@RequestMapping("/xml/test")
public  void xmltest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	
    Calendar calendar = Calendar.getInstance();
    boolean xlsx = true;
    calendar.set(Calendar.YEAR, 10);
    /*for (int i = 0; i < args.length; i++) {
        if(args[i].charAt(0) == '-'){
            xlsx = args[i].equals("-xlsx");
        } else {
          calendar.set(Calendar.YEAR, Integer.parseInt(args[i]));
        }
    }*/
    int year = calendar.get(Calendar.YEAR);

    Workbook wb = xlsx ? new HSSFWorkbook() : new HSSFWorkbook();

    Map<String, CellStyle> styles = createStyles(wb);

        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //create a sheet for each month
        Sheet sheet = wb.createSheet(months[1]);

        //turn off gridlines
        sheet.setDisplayGridlines(true);
        sheet.setPrintGridlines(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);

        //the following three statements are required only for HSSF
        sheet.setAutobreaks(true);
        printSetup.setFitHeight((short)1);
        printSetup.setFitWidth((short)1);

        //the header row: centered text in 48pt font
/*        Row headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(80);
        Cell titleCell = headerRow.createCell(0);
        titleCell.setCellValue(months[month] + " " + year);
        titleCell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$N$1"));*/

        //header with month titles
        Row titleRow = sheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            //set column widths, the width is measured in units of 1/256th of a character width
//            sheet.setColumnWidth(i*2, 5*256); //the column is 5 characters wide
//            sheet.setColumnWidth(i*2 + 1, 13*256); //the column is 13 characters wide
//            sheet.addMergedRegion(new CellRangeAddress(1, 1, i*2, i*2+1));
            Cell monthCell = titleRow.createCell(i);
            monthCell.setCellValue(titles[i]);
            /*monthCell.setCellStyle(styles.get("normal"));*/
        }

        int cnt = 1, day=1;
        int allRow = 6;
        int rownum = 1;
        for (int j = 0; j < allRow; j++) {
            Row row = sheet.createRow(rownum++);
            row.setHeightInPoints(15);
            for (int i = 0; i < titles.length; i++) {
                Cell dayCell_1 = row.createCell(i);
                dayCell_1.setCellValue(day);
                calendar.set(Calendar.DAY_OF_MONTH, ++day);
                
                dayCell_1.setCellStyle(styles.get("normal"));
                cnt++;
            }
           
        }
    

    // Write the output to a file
    response.setContentType("application/vnd.ms-excel");
    ServletOutputStream output = response.getOutputStream();
    String file = "calendar.xls";
    response.addHeader("Content-Disposition", "attachment;filename=" + file);
    if(wb instanceof HSSFWorkbook) file += "x";
    wb.write(output);
    output.flush();
    output.close();
    
}

/**
 * cell styles used for formatting calendar sheets
 */
private static Map<String, CellStyle> createStyles(Workbook wb){
    Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

    short borderColor = IndexedColors.GREY_50_PERCENT.getIndex();
    short normalBorderColor = IndexedColors.GREY_25_PERCENT.getIndex();
    
    CellStyle style;
    Font titleFont = wb.createFont();
    titleFont.setFontHeightInPoints((short)48);
    titleFont.setColor(IndexedColors.DARK_BLUE.getIndex());
    style = wb.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    style.setFont(titleFont);
    styles.put("title", style);

    Font monthFont = wb.createFont();
    monthFont.setFontHeightInPoints((short)12);
    monthFont.setColor(IndexedColors.WHITE.getIndex());
    monthFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    style = wb.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    style.setFont(monthFont);
    styles.put("month", style);

    Font dayFont = wb.createFont();
    dayFont.setFontHeightInPoints((short)14);
    dayFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    style = wb.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_LEFT);
    style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
    style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    style.setBorderLeft(CellStyle.BORDER_THIN);
    style.setLeftBorderColor(borderColor);
    style.setBorderBottom(CellStyle.BORDER_THIN);
    style.setBottomBorderColor(borderColor);
    style.setFont(dayFont);
    styles.put("weekend_left", style);

    style = wb.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
    style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    style.setBorderRight(CellStyle.BORDER_THIN);
    style.setRightBorderColor(borderColor);
    style.setBorderBottom(CellStyle.BORDER_THIN);
    style.setBottomBorderColor(borderColor);
    styles.put("weekend_right", style);

    style = wb.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_LEFT);
    style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
    style.setBorderLeft(CellStyle.BORDER_THIN);
    style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    style.setLeftBorderColor(borderColor);
    style.setBorderBottom(CellStyle.BORDER_THIN);
    style.setBottomBorderColor(borderColor);
    style.setFont(dayFont);
    styles.put("workday_left", style);

    style = wb.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_CENTER);
    style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
    style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    style.setBorderRight(CellStyle.BORDER_THIN);
    style.setRightBorderColor(borderColor);
    style.setBorderBottom(CellStyle.BORDER_THIN);
    style.setBottomBorderColor(borderColor);
    styles.put("workday_right", style);

    style = wb.createCellStyle();
    style.setBorderLeft(CellStyle.BORDER_THIN);
    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    style.setBorderBottom(CellStyle.BORDER_THIN);
    style.setBottomBorderColor(borderColor);
    styles.put("grey_left", style);

    style = wb.createCellStyle();
    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
    style.setBorderRight(CellStyle.BORDER_THIN);
    style.setRightBorderColor(borderColor);
    style.setBorderBottom(CellStyle.BORDER_THIN);
    style.setBottomBorderColor(borderColor);
    styles.put("grey_right", style);

    style = wb.createCellStyle();
/*    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
    style.setFillPattern(CellStyle.SOLID_FOREGROUND);*/
    /*style.setRightBorderColor(borderColor);*/
    /*style.setBottomBorderColor(borderColor);*/
   /* style.setBorderTop(CellStyle.BORDER_THIN);*/
    style.setBorderRight(CellStyle.BORDER_THIN);
    style.setBorderBottom(CellStyle.BORDER_THIN);
    /*style.setBorderLeft(CellStyle.BORDER_THIN);*/
    
    /*style.setTopBorderColor(normalBorderColor);*/
/*    style.setRightBorderColor(normalBorderColor);
    style.setBorderBottom(normalBorderColor);*/
    /*style.setLeftBorderColor(normalBorderColor);*/
    
    styles.put("normal", style);
    
    return styles;
}
}
