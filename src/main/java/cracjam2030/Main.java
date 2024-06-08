package cracjam2030;

import java.io.IOException;

public class Main {
    public static MessageLogger logger = new MessageLogger();
    public static void main(String[] args) throws IOException {

        Main.logger.addError("Pzykład dodania błędu");
        Main.logger.addLine("Przykład dodania linii raportu");


        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }

        XmlLoader.loadXml("Kowalski_Jan.xls");

    }
}