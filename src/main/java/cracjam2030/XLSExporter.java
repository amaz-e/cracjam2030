package cracjam2030;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XLSExporter {

    public static void createXLSfile ()
    {
        try {
            FileOutputStream fileOut = new FileOutputStream("report.xlx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}


