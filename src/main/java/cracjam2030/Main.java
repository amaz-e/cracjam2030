package cracjam2030;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static MessageLogger logger = new MessageLogger();

    public static void main(String[] args) throws IOException {

        XmlLoader loader = new XmlLoader();

        ExcelFileFinder fileFinder = new ExcelFileFinder();

        List<String> stringList = fileFinder.findExcelFiles("/var/home/student/Downloads/");
        List<TaskRecord> recordData = new ArrayList<>();
        for (String s: stringList) {
            System.out.println(s);
            loader.loadXml(s);
            recordData.addAll(loader.getRecords());
            System.out.println();

        }
        System.out.println(recordData.size());
        for (TaskRecord t: recordData) {
            System.out.println(t);
        }

        Raport1.Generate(recordData);

        System.out.println("Błędy:");
        for (String e: logger.getReportErrors()) {
            System.out.println(e);
        }
        System.out.println("Raport:");
        for (String l: logger.getReportLines()) {
            System.out.println(l);
        }

    }
}