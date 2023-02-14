package com.iot.data_server.exporters;

import com.iot.data_server.entities.DataEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SensorDataExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DataEntity> dataList;

    public SensorDataExcelExporter(List<DataEntity> dataList) {
        this.dataList = dataList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(11);
        style.setFont(font);

        createCell(row, 0, "Data ID", style);
        createCell(row, 1, "Sent at", style);
        createCell(row, 2, "x_accel", style);
        createCell(row, 3, "x_gyro", style);
        createCell(row, 4, "y_accel", style);
        createCell(row, 5, "y_gyro", style);
        createCell(row, 6, "z_accel", style);
        createCell(row, 7, "z_gyro", style);
        createCell(row, 8, "temperature", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(11);
        style.setFont(font);

        for (DataEntity data : dataList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, data.getId().toString(), style);
            createCell(row, columnCount++, data.getSentAt().toString(), style);
            createCell(row, columnCount++, String.valueOf(data.getX_accel()), style);
            createCell(row, columnCount++,String.valueOf(data.getX_gyro()), style);
            createCell(row, columnCount++,String.valueOf(data.getY_accel()), style);
            createCell(row, columnCount++,String.valueOf(data.getY_gyro()), style);
            createCell(row, columnCount++,String.valueOf(data.getZ_accel()), style);
            createCell(row, columnCount++,String.valueOf(data.getZ_gyro()), style);
            createCell(row, columnCount,String.valueOf(data.getTempr()), style);
        }
    }

    public void export() throws IOException {
        writeHeaderLine();
        writeDataLines();

        FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx");
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
