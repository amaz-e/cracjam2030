package cracjam2030;

public class ReportPrinter {

    public static void printReport(MessageLogger logger) {
        System.out.println("Błędy:");
        for (String e : logger.getReportErrors()) {
            System.out.println("  " + e);
        }
        System.out.println("Raport:");
        for (String l : logger.getReportLines()) {
            System.out.println("  " + l);
        }
    }
}
