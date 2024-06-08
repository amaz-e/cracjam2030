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
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;


public class XmlLoader {

    List<TaskRecord> records = new ArrayList<>();

    public void loadXml(String path) throws IOException {

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

                Iterator<Cell> cellIterator = row.cellIterator();

                TaskRecord record = new TaskRecord();

                record.setDevelopername(getDeveloperNameFromFile(path));

                record.setFolderMonth(getFolderMonthFromFile(path));

                record.setGetFolderYear(getFolderYearFromFile(path));

                record.setProjectName(sheet.getSheetName());

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            if(DateUtil.isCellDateFormatted(cell)) {
                                Date date = cell.getDateCellValue();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                record.setProjectDate(date);
                                System.out.print(dateFormat.format(date));
                            } else {
                                record.setWorkHours(cell.getNumericCellValue());
                                System.out.print(cell.getNumericCellValue());
                            }
                            break;
                        case STRING:
                            record.setTaskName(cell.getStringCellValue());
                            System.out.print(cell.getStringCellValue());
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

