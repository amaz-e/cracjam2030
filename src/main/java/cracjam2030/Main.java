package cracjam2030;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static MessageLogger logger = new MessageLogger();
    public static void main(String[] args) throws IOException {


        Runner.executeProgram(args);
//     example command: -r1 /var/home/student/IdeaProjects/cracjam2030/src/main/resources/testy/Poprawne_dane/2012/01

        XLSImporter.importToXLS("3");

    }

}