package com.fragma.service;

import com.fragma.dto.DueBills;

import com.fragma.dto.MainDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Map;

@Service
public class ExcelFileCreator {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelFileCreator.class);

    XSSFWorkbook workbook = new XSSFWorkbook();

    public void createAllSheets(String excelFileLocation, MainDto mainDto) throws Exception {

        createConfirmedDataSheet(mainDto, "Confirmed");
        createUnconfirmedDataSheet(mainDto, "UnConfirmed");

        FileOutputStream out = new FileOutputStream(excelFileLocation);
        this.workbook.write(out);
        out.close();
        LOG.info(" Excel file written successfully on disk at :" + excelFileLocation);
    }

    private void createConfirmedDataSheet(MainDto mainDto, String sheetName) throws Exception {

        LOG.info("***** executing createConfirmedDataSheet ****** ");


        Font headingFont = workbook.createFont();
        headingFont.setBold(true);

        XSSFColor orange = new XSSFColor(new java.awt.Color(182, 207, 242));

        XSSFCellStyle headingCellStyle = workbook.createCellStyle();

        headingCellStyle.setFont(headingFont);
        headingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headingCellStyle.setFillForegroundColor(orange);
        headingCellStyle.setBorderBottom(BorderStyle.THIN);
        headingCellStyle.setBorderLeft(BorderStyle.THIN);
        headingCellStyle.setBorderRight(BorderStyle.THIN);
        headingCellStyle.setBorderTop(BorderStyle.THIN);
        headingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headingCellStyle.setWrapText(true);

        XSSFColor lightOrange = new XSSFColor(new java.awt.Color(255, 216, 151));

        XSSFCellStyle MainHeadingCellStyle = workbook.createCellStyle();

        MainHeadingCellStyle.setFont(headingFont);
        MainHeadingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        MainHeadingCellStyle.setFillForegroundColor(lightOrange);
        MainHeadingCellStyle.setBorderBottom(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderLeft(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderRight(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderTop(BorderStyle.THIN);
        MainHeadingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        MainHeadingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        MainHeadingCellStyle.setWrapText(true);

        CellStyle bordersOnly = workbook.createCellStyle();
        bordersOnly.setBorderBottom(BorderStyle.THIN);
        bordersOnly.setBorderLeft(BorderStyle.THIN);
        bordersOnly.setBorderRight(BorderStyle.THIN);
        bordersOnly.setBorderTop(BorderStyle.THIN);
        bordersOnly.setAlignment(HorizontalAlignment.CENTER);
        bordersOnly.setVerticalAlignment(VerticalAlignment.CENTER);


        Sheet sheet = workbook.createSheet(sheetName);


        int rowNum=0;

        Row headingRow = sheet.createRow(rowNum++);
        headingRow.setHeight((short) 900);

        int headingColmIndx = 0;


        createCellAddData(headingRow, headingColmIndx++, "LC Number", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Operation Code", headingCellStyle);

        createCellAddData(headingRow, headingColmIndx++, "Ext Reference Number", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Product Code", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Cif ID", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Customer Name", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Bill Number", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Maturity Date", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Currency", headingCellStyle);

        createCellAddData(headingRow, headingColmIndx++, "Amount ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Type of LC", headingCellStyle);

        for (Map.Entry<Integer, DueBills> tdEntry : mainDto.getConfirmedMap().entrySet()) {

            Row row = sheet.createRow(rowNum++);
            int cell = 0;


            createCellAddData(row, cell++, tdEntry.getValue().getContractRefNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getOperationCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getExtRefNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getProductCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getCifId(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getCustName(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBcrefno(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getMaturityDate(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBillCcy(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBillAmt(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getTypeOfLc(), bordersOnly);


        }

        for (int i = 0; i <= sheet.getRow(1).getLastCellNum(); i++) {

            sheet.autoSizeColumn(i);
            int columnWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, columnWidth + 1000);
        }
    }

    private void createUnconfirmedDataSheet(MainDto mainDto, String sheetName) throws Exception {

        LOG.info("***** executing createUnconfirmedDataSheet ****** ");


        Font headingFont = workbook.createFont();
        headingFont.setBold(true);

        XSSFColor orange = new XSSFColor(new java.awt.Color(182, 207, 242));

        XSSFCellStyle headingCellStyle = workbook.createCellStyle();

        headingCellStyle.setFont(headingFont);
        headingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headingCellStyle.setFillForegroundColor(orange);
        headingCellStyle.setBorderBottom(BorderStyle.THIN);
        headingCellStyle.setBorderLeft(BorderStyle.THIN);
        headingCellStyle.setBorderRight(BorderStyle.THIN);
        headingCellStyle.setBorderTop(BorderStyle.THIN);
        headingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headingCellStyle.setWrapText(true);

        XSSFColor lightOrange = new XSSFColor(new java.awt.Color(255, 216, 151));

        XSSFCellStyle MainHeadingCellStyle = workbook.createCellStyle();

        MainHeadingCellStyle.setFont(headingFont);
        MainHeadingCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        MainHeadingCellStyle.setFillForegroundColor(lightOrange);
        MainHeadingCellStyle.setBorderBottom(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderLeft(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderRight(BorderStyle.THIN);
        MainHeadingCellStyle.setBorderTop(BorderStyle.THIN);
        MainHeadingCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        MainHeadingCellStyle.setAlignment(HorizontalAlignment.CENTER);
        MainHeadingCellStyle.setWrapText(true);

        CellStyle bordersOnly = workbook.createCellStyle();
        bordersOnly.setBorderBottom(BorderStyle.THIN);
        bordersOnly.setBorderLeft(BorderStyle.THIN);
        bordersOnly.setBorderRight(BorderStyle.THIN);
        bordersOnly.setBorderTop(BorderStyle.THIN);
        bordersOnly.setAlignment(HorizontalAlignment.CENTER);
        bordersOnly.setVerticalAlignment(VerticalAlignment.CENTER);


        Sheet sheet = workbook.createSheet(sheetName);


        int rowNum=0;

        Row headingRow = sheet.createRow(rowNum++);
        headingRow.setHeight((short) 900);

        int headingColmIndx = 0;


        createCellAddData(headingRow, headingColmIndx++, "LC Number", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Operation Code", headingCellStyle);

        createCellAddData(headingRow, headingColmIndx++, "Ext Reference Number", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Product Code", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Cif ID", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Customer Name", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Bill Number", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Maturity Date", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Currency", headingCellStyle);

        createCellAddData(headingRow, headingColmIndx++, "Amount ", headingCellStyle);
        createCellAddData(headingRow, headingColmIndx++, "Type of LC", headingCellStyle);

        for (Map.Entry<Integer, DueBills> tdEntry : mainDto.getUnconfirmedMap().entrySet()) {

            Row row = sheet.createRow(rowNum++);
            int cell = 0;


            createCellAddData(row, cell++, tdEntry.getValue().getContractRefNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getOperationCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getExtRefNo(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getProductCode(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getCifId(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getCustName(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBcrefno(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getMaturityDate(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBillCcy(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getBillAmt(), bordersOnly);
            createCellAddData(row, cell++, tdEntry.getValue().getTypeOfLc(), bordersOnly);


        }

        for (int i = 0; i <= sheet.getRow(1).getLastCellNum(); i++) {

            sheet.autoSizeColumn(i);
            int columnWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, columnWidth + 1000);
        }
    }




    public void createCellAddData(Row row, int cellNo, String cellValue, CellStyle cellStyle) {
        Cell cell = row.createCell(cellNo);
        cell.setCellValue(cellValue);
        cell.setCellStyle(cellStyle);
    }

}