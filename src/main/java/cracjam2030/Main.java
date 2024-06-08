package cracjam2030;

import java.io.IOException;

public class Main {
    public static MessageLogger logger = new MessageLogger();

    public static void main(String[] args) throws IOException {


//
//
//        // Press Alt+Enter with your caret at the highlighted text to see how
//        // IntelliJ IDEA suggests fixing it.
//        System.out.printf("Hello and welcome!");
//
//        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
//        for (int i = 1; i <= 5; i++) {
//
//            // Press Shift+F9 to start debugging your code. We have set one breakpoint
//            // for you, but you can always add more by pressing Ctrl+F8.
//            System.out.println("i = " + i);
//        }

        XmlLoader loader = new XmlLoader();


        Raport1.Generate(MockDataset.GetMockTaskRecordList());


        loader.loadXml("/var/home/student/Documents/reporter-dane/reporter-dane/2012/01/Kowalski_Jan.xls");

        for (TaskRecord record : loader.getRecords()) {
            System.out.println(record.getDevelopername());
            System.out.println(record.getFolderMonth());
            System.out.println(record.getGetFolderYear());
            System.out.println(record.getTaskName());
            System.out.println(record.getProjectName());
            System.out.println(record.getProjectDate());
            System.out.println(record.getWorkHours());

            System.out.println();
            System.out.println();
        }

        System.out.println("Błędy:");
        for (String e: logger.getReportErrors()             ) {
            System.out.println(e);
        }
        System.out.println("Raport:");
        for (String l: logger.getReportLines()             ) {
            System.out.println(l);
        }





    }
}