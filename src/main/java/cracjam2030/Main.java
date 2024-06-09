package cracjam2030;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static MessageLogger logger = new MessageLogger();

    public static void main(String[] args) throws IOException {

        Run("/var/home/student/IdeaProjects/cracjam2030/src/main/resources/testy/Poprawne_dane/2012/01");

    }

    private static void Run(String path) throws IOException {
        XLSLoader loader = new XLSLoader();
        ExcelFileFinder fileFinder = new ExcelFileFinder();
        List<String> spreadsheetpathList = fileFinder.findExcelFiles(path);

        List<TaskRecord> recordData = new ArrayList<>();
        for (String s: spreadsheetpathList) {
            loader.loadXLS(s);
            recordData.addAll(loader.getRecords());
        }

//        System.out.println(recordData.size());
//        for (TaskRecord t: recordData) {
//            //wyświetlanie wszystkich rekordów
//            System.out.println(t);
//        }
        logger.addLine("\nRaport 1:");
        Report1.createReport(recordData);
        logger.addLine("\nRaport 2:");
        Report2.createReport(recordData);
        logger.addLine("\nRaport 3:");
        Report3.createReport(recordData);
        ReportPrinter.printReport(logger);
    }
}