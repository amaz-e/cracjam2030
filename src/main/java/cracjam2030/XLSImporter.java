package cracjam2030;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Date;
import java.util.TreeMap;


public class XLSImporter {
    public static void importToXLS(String reportType) {

        int index = switch (reportType) {
            case "1" -> 2;
            case "2" -> 3;
            case "3" -> 2;
            default -> 4;
        };

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
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, index));
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setUnderline(Font.U_SINGLE);
        font.setColor(HSSFColor.HSSFColorPredefined.ROYAL_BLUE.getIndex());
        font.setFontHeight((short) 300);
        style.setFont(font);

        CellStyle style2 = workbook.createCellStyle();
        Font font2 = workbook.createFont();
        font2.setFontHeight((short) 250);
        style2.setFont(font2);


        Row headerRow = sheet.createRow(0);
        headerRow.setHeight((short) 500);
        headerRow.createCell(0).setCellValue("Raport " + reportType);
        headerRow.getCell(0).setCellStyle(style);


        TreeMap<String, Double> reportData = new TreeMap<>();
        if (reportType.equals("1")) {
            reportData = Main.logger.getRaport1Data();
        } else if (reportType.equals("2")) {
            reportData = Main.logger.getRaport2Data();

        } else if (reportType.equals("3")) {
            reportData = Main.logger.getRaport3Data();
        }

        int i = 1;
        if (reportType.equals("2")) {
            for (var record : reportData.keySet()) {
                Row row1 = sheet.createRow(i);
                row1.setHeight((short) 330);
                String ProjectName = record.split(";")[1];
                String DeveloperName = record.split(";")[0];
                row1.createCell(0).setCellValue(DeveloperName);
                row1.getCell(0).setCellStyle(style2);
                row1.createCell(1).setCellValue(ProjectName);
                row1.getCell(1).setCellStyle(style2);
                Double ProjectHours = reportData.get(record);
                if (ProjectHours == null) {
                    Main.logger.addError(record + " null hours!");
                    ProjectHours = -1.0;
                }
                row1.createCell(2).setCellValue(ProjectHours);
                row1.getCell(2).setCellStyle(style2);
                row1.createCell(3).setCellValue("hours");
                row1.getCell(3).setCellStyle(style2);
                i++;
            }
        } else {
            for (var record : reportData.keySet()) {
                System.out.println("*** " + i + "  ");
                Row row1 = sheet.createRow(i);
                row1.setHeight((short) 330);
                String ProjectName = record;
                row1.createCell(0).setCellValue(ProjectName);
                row1.getCell(0).setCellStyle(style2);
                Double ProjectHours = reportData.get(record);
                if (ProjectHours == null) {
                    Main.logger.addError(record + " null hours!");
                    ProjectHours = -1.0;
                }
                row1.createCell(1).setCellValue(ProjectHours);
                row1.getCell(1).setCellStyle(style2);
                row1.createCell(2).setCellValue("hours");
                row1.getCell(2).setCellStyle(style2);
                i++;
            }
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        try (FileOutputStream fileOut = new FileOutputStream("output/report" + reportType + "_genOn" + new Date().toString() + ".xls")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

