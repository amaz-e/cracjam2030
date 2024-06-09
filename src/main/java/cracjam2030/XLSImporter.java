package cracjam2030;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;


public class XLSImporter{
    public static void importToXLS () {

        String directoryPath = "output";

        Path path = Paths.get(directoryPath);

        try {
            Path newDir = Files.createDirectory(path);
            System.out.println("Katalog został utworzony pomyślnie: " + newDir);
        } catch (IOException e) {
            System.err.println("Nie udało się utworzyć katalogu, bądź katalog już istnieje: " + e.getMessage());
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Raport");
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);



        // Utwórz nagłówki kolumn
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Raport1");
        headerRow.setRowStyle(style);


        // Dodaj przykładowe dane
        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue(1);
        row1.createCell(1).setCellValue("John Doe");
        row1.createCell(2).setCellValue(30);

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue(2);
        row2.createCell(1).setCellValue("Jane Smith");
        row2.createCell(2).setCellValue(25);

        // Zapisz plik
        try (FileOutputStream fileOut = new FileOutputStream("output/example.xls")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Zamknij workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

