package cracjam2030;

public class Main {
    public static MessageLogger logger = new MessageLogger();

    public static void main(String[] args) {

        Main.logger.addError("Pzykład dodania błędu");
        Main.logger.addLine("Przykład dodania linii raportu");


        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        //System.out.printf("Hello and welcome!");

        Raport1.Generate(MockDataset.GetMockTaskRecordList());

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