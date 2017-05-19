package com.neuq.flight.grab.utils.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;


/**
 * author: xiaoliufu
 * date:   2017/5/18.
 * description:
 */
@Slf4j
public class ExcelReaderHandler {
    private Workbook workbook;

    public void config(String path) {
        try {
            InputStream resourceAsStream = this.getClass().getResourceAsStream(path);
            workbook = new XSSFWorkbook(resourceAsStream);
        } catch (FileNotFoundException e) {
            log.error("file not found error,path={}", path, e);
        } catch (IOException e) {
            log.error("io exception,path={}", path, e);
        }
    }

    /**
     * 获取实际内容
     *
     * @param sheetNum
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public String getContent(int sheetNum, int rowIndex, int columnIndex) {
        Sheet targetSheet = workbook.getSheetAt(sheetNum);
        Row row = targetSheet.getRow(rowIndex);
        Cell cell = row.getCell(columnIndex);
        return cell.getStringCellValue();
    }

    /**
     * 获取总行数
     *
     * @return
     */
    public int getTotalRowNum(int sheetNum) {
        Sheet targetSheet = workbook.getSheetAt(sheetNum);
        return targetSheet.getLastRowNum();
    }
}
