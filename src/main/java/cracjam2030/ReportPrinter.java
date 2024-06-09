package cracjam2030;

import java.io.IOException;

public class ReportPrinter {

    public static void printReport(MessageLogger logger) {

        System.out.println("Błędy:");
        for (String e : logger.getReportErrors()) {
            System.out.println("  " + e);
        }

        for (String l : logger.getReportLines()) {
            System.out.println("  " + l);
        }

    }
}
