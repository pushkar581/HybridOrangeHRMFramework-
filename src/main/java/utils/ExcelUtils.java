package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtils {
    public static Object[][] getTableArray(String filePath, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        int totalRows = sheet.getPhysicalNumberOfRows();
        int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] tabArray = new Object[totalRows - 1][totalCols];
        for (int i = 1; i < totalRows; i++) { // skip header row
            Row row = sheet.getRow(i);
            for (int j = 0; j < totalCols; j++) {
                tabArray[i - 1][j] = row.getCell(j).toString();
            }
        }
        workbook.close();
        fis.close();
        return tabArray;
    }
}
