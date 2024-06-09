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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TreeMap;


public class XLSImporter {
    public static void importToXLS(String reportType) {

        String directoryPath = "output";

        Path path = Paths.get(directoryPath);

        try {
            Path newDir = Files.createDirectory(path);
            //System.out.println("Katalog został utworzony pomyślnie: " + newDir);
        } catch (IOException e) {
            //System.err.println("Nie udało się utworzyć katalogu, bądź katalog już istnieje: " + e.getMessage());
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Raport");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);


        // Utwórz nagłówki kolumn
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Raport " + reportType);
        headerRow.getCell(0).setCellStyle(style);

        // Dodaj przykładowe dane

        TreeMap<String, Double> reportData = new TreeMap<>();
        LinkedHashMap<String, Double> sortedReportData = new LinkedHashMap<>();
        if (reportType.equals("1")) {
            reportData = Main.logger.getRaport1Data();
        } else if (reportType.equals("2")) {
            reportData = Main.logger.getRaport2Data();

        } else if (reportType.equals("3")) {
            sortedReportData = Main.logger.getRaport3Data();
        }

        int i = 1;
        if (reportType.equals("2")) {
            for (var record : reportData.keySet()) {
                //System.out.println("*** " + i + "  ");
                Row row1 = sheet.createRow(i);
                String ProjectName = record.split(";")[1];
                String DeveloperName = record.split(";")[0];
                row1.createCell(0).setCellValue(DeveloperName);
                row1.createCell(1).setCellValue(ProjectName);
                Double ProjectHours = reportData.get(record);
                if (ProjectHours == null) {
                    Main.logger.addError(record + " null hours!");
                    ProjectHours = -1.0;
                }
                row1.createCell(2).setCellValue(ProjectHours);
                i++;
            }
        } else if (reportType.equals("3")) {
            for (var record : sortedReportData.keySet()) {
                //System.out.println("*** " + i + "  ");
                Row row1 = sheet.createRow(i);
                String ProjectName = record;
                row1.createCell(0).setCellValue(ProjectName);
                Double ProjectHours = sortedReportData.get(record);
                if (ProjectHours == null) {
                    Main.logger.addError(record + " null hours!");
                    ProjectHours = -1.0;
                }
                row1.createCell(1).setCellValue(ProjectHours);
                i++;
            }
        } else {
            for (var record : reportData.keySet()) {
                //System.out.println("*** " + i + "  ");
                Row row1 = sheet.createRow(i);
                String ProjectName = record;
                row1.createCell(0).setCellValue(ProjectName);
                Double ProjectHours = reportData.get(record);
                if (ProjectHours == null) {
                    Main.logger.addError(record + " null hours!");
                    ProjectHours = -1.0;
                }
                row1.createCell(1).setCellValue(ProjectHours);
                i++;
            }
        }
//        row1.createCell(0).setCellValue("Project 1");
//        row1.createCell(1).setCellValue("John Doe");
//        row1.createCell(2).setCellValue("hours");

//        Row row2 = sheet.createRow(2);
//        row2.createCell(0).setCellValue("Project 2");
//        row2.createCell(1).setCellValue("coś tam");
//        row2.createCell(2).setCellValue("hours");

        // Zapisz plik

        try (FileOutputStream fileOut = new FileOutputStream("output/report" + reportType + "_genOn" + new Date().toString() + ".xls")) {
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

