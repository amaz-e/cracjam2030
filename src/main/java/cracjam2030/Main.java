package cracjam2030;

public class Main {
    public static MessageLogger logger = new MessageLogger();
    public static void main(String[] args) {

        Main.logger.addError("Pzykład dodania błędu");
        Main.logger.addLine("Przykład dodania linii raportu");

        System.out.printf("Hello and welcome!");


    }
}