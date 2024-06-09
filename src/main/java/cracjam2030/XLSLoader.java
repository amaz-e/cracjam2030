package cracjam2030;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;


public class XLSLoader {

    List<TaskRecord> records;


    public void loadXLS(String path) throws IOException {

        records = new ArrayList<>();

        FileInputStream fis = new FileInputStream(new File(path));
        HSSFWorkbook wb = new HSSFWorkbook(fis);

        for (int sheetIndex = 0; sheetIndex<wb.getNumberOfSheets();sheetIndex++) {
            HSSFSheet sheet = wb.getSheetAt(sheetIndex);

            boolean firstRow = true;
            for (Row row : sheet) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }


                if((row.getCell(0) == null || (row.getCell(0).getCellType() == CellType.BLANK)) && (row.getCell(1) == null || (row.getCell(1).getCellType() == CellType.BLANK)) && (row.getCell(2) == null || (row.getCell(2).getCellType() == CellType.BLANK))) {
                    continue;
                } else if((row.getCell(1) == null) || (row.getCell(1).getCellType() == CellType.BLANK) || (row.getCell(1).getCellType() != CellType.STRING)) {
                    String message = "Wystąpił błąd podczas dodania danych. Rząd: " + (row.getRowNum()+1) + " Komórka: "+ 2 + " Arkusz: " + sheet.getSheetName() + " Plik: " + path;
                    Main.logger.addError(message);
                     continue;
                } else if((row.getCell(0) == null) || (row.getCell(0).getCellType() == CellType.BLANK) || (row.getCell(0).getCellType() != CellType.NUMERIC) || !DateUtil.isCellDateFormatted(row.getCell(0))) {
                    String message = "Wystąpił błąd podczas dodania danych. Rząd: " + (row.getRowNum()+1) + " Komórka: " + 1 + " Arkusz: " + sheet.getSheetName() + " Plik: " + path;
                    Main.logger.addError(message);
                    continue;
                } else if ((row.getCell(2) == null) || (row.getCell(2).getCellType() == CellType.BLANK) || (row.getCell(2).getCellType() != CellType.NUMERIC)) {
                    String message = "Wystąpił błąd podczas dodania danch. Rząd: " + (row.getRowNum()+1) + " Komórka: " + 3 + " Arkusz: " + sheet.getSheetName() + " Plik: " + path;
                    Main.logger.addError(message);
                    continue;
                }



                Iterator<Cell> cellIterator = row.cellIterator();

                int stopIndex = 3;

                TaskRecord record = new TaskRecord();

                record.setDevelopername(getDeveloperNameFromFile(path));

                record.setFolderMonth(getFolderMonthFromFile(path));

                record.setGetFolderYear(getFolderYearFromFile(path));

                record.setProjectName(sheet.getSheetName());


                for (int index = 0; index< stopIndex; index++) {
                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case NUMERIC:
                            if(DateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                record.setProjectDate(date);
                            } else {
                                record.setWorkHours(cell.getNumericCellValue());
                            }
                            break;
                        case STRING:
                            record.setTaskName(cell.getStringCellValue());
                            break;
                        default:
                            System.out.println("?");
                    }
                }

                    records.add(record);

            }
        }
        fis.close();

    }

    private String getDeveloperNameFromFile (String path) {
        String[] splitedPatch = path.split("/");
        String nameXls = splitedPatch[splitedPatch.length-1];
        String name_ = nameXls.substring(0,nameXls.length()-4);

        return name_.replace("_", " ");
    }

    private int getFolderMonthFromFile (String path) {
        String[] splitedPatch = path.split("/");
        String month = splitedPatch[splitedPatch.length-2];

        return Integer.parseInt(month);
    }

    private int getFolderYearFromFile (String path) {
        String[] splitedPatch = path.split("/");
        String year = splitedPatch[splitedPatch.length-3];

        return Integer.parseInt(year);
    }

    public List<TaskRecord> getRecords() {
        return records;
    }

}

