package cracjam2030;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//        -h -- help Wyświetla wszystkie opcje programu.
//        -R1 [path] [start date][end date] Generuje Raport 1. Czas wykorzystany na projekt. Możliwość filtrowanie po start i end date. Należy podać ścieżkę całkowitą do pliku xls
//        -R2 [path] [start date][end date] Generuje Raport 2. Czas wykorzystany przez dev na projekt. Możliwość filtrowanie po start i end date. Należy podać ścieżkę całkowitą do pliku xls
//        -R3 [path] [start date][end date] Generuje Raport 1. Czas wykorzystany na zadania na projekt. Możliwość filtrowanie po start i end date. Należy podać ścieżkę całkowitą do pliku xls
//        -d Generowanie diagramu
public class Executor {

    public static void main(String[] args) {

        ExcelFileFinder fileFinder = new ExcelFileFinder();

        if (args.length ==0) {
            System.out.printf("Program called without params. Add some parameters, for help use parameter -h");
        }
            System.out.printf("Function called with params");

            if (Arrays.stream(args).anyMatch(s -> s.contains("-h"))){
                System.out.printf("\n -h -help \n -R1 - Report for Report 1 \n -R2 - Report for Report 2 \n -R3 - Report for Report 3");
            }

            if (Arrays.stream(args).anyMatch(s -> s.contains("-R1"))){
                String path = args[1];
                System.out.printf("\nRunning Report1 with path: " + path);
//                prepareReport();
            }
            if (Arrays.stream(args).anyMatch(s -> s.contains("-R2"))){
                String path = args[1];
                System.out.printf("Running Report2 with path: " + path);

            }

            if (Arrays.stream(args).anyMatch(s -> s.contains("-R3"))){
                String path = args[1];
                System.out.printf("Running Report2 with path: " + path);

            }
        }
//    private static void prepareReport(String path) throws IOException {
//        XLSLoader loader = new XLSLoader();
//        ExcelFileFinder fileFinder = new ExcelFileFinder();
//        List<String> spreadsheetpathList = fileFinder.findExcelFiles(path);
//
//        List<TaskRecord> recordData = new ArrayList<>();
//        for (String s: spreadsheetpathList) {
//            System.out.println(s);
//            loader.loadXLS(s);
//            recordData.addAll(loader.getRecords());
//            System.out.println();
//        }
//
//        System.out.println(recordData.size());
//        for (TaskRecord t: recordData) {
//            System.out.println(t);
//        }
//
//        Report1.createReport(recordData);
//        ReportPrinter.printReport(logger);
//    }
}


