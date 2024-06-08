package cracjam2030;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;


public class XmlLoader {

    public static void loadXml(String path) throws IOException {

        TaskRecord record = new TaskRecord();

        FileInputStream fis = new FileInputStream(new File(path));
        HSSFWorkbook wb = new HSSFWorkbook(fis);


        HSSFSheet sheet = wb.getSheetAt(0);

        boolean firstRow = true;
        for (Row row : sheet) {
            if (firstRow) {
                firstRow = false;
                continue;
            }
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

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
            System.out.println();
        }
        fis.close();


    }
}

