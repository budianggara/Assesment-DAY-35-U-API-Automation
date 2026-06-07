package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class Utils {

    public static String[][] getTestData(String filePath, String sheetName) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Workbook workbook;
        try {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Sheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();
        String[][] data = new String[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = getCellValue(cell);
            }
        }

        try {
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";

        // Menggunakan switch standar (klasik) untuk menghindari error kompilasi Gradle
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return (cell.getNumericCellValue() % 1 == 0)
                        ? String.valueOf((int) cell.getNumericCellValue())
                        : String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    public static String generateRandomTitle() {
        // Ambil 8 karakter pertama dari UUID
        return "Rizki Punya Test data " + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generateRandomDescription() {
        // Ambil 8 karakter pertama dari UUID
        return "Rizki Punya Test description " + UUID.randomUUID().toString().substring(0, 8);
    }
}
