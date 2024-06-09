package cracjam2030;
import org.apache.commons.cli.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {
    public static void main(String[] args) {
        Options options = new Options();

        Option report = Option.builder("r")
                .required(false) //wymagane ale nie da się tego sensownie zrobić inaczej
                .longOpt("raport")
                .hasArg(true)
                .desc("Generuje wybrany raport. Wymaga podania wartości: 1, 2 albo 3")
                .build();

        Option diagram = Option.builder("d")
                .required(false)
                .longOpt("diagram")
                .desc("Dodatkowo (obok raportu w konsoli) generuje diagram")
                .build();

        Option save = Option.builder("s")
                .required(false)
                .hasArg(true)
                .desc("Zapisuje raport do pliku. Wymaga podania ścieżki wraz z nazwą pliku np. s=c:\\plik.xls")
                .longOpt("save")
                .build();

        Option help = Option.builder("h")
                .required(false)
                .longOpt("help")
                .desc("Wyświetla pomoc")
                .build();

        options.addOption(report);
        options.addOption(diagram);
        options.addOption(save);
        options.addOption(help);

        CommandLineParser cliParser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmdLine = null;


        //sprawdza czy jest argumement help
        try {
            CommandLine prelimCmd = cliParser.parse(options, args, true);
            if (prelimCmd.hasOption("h")) {
                formatter.printHelp("pomoc:", options);
                System.exit(0);
            }
        } catch (ParseException e) {

        }

        try {
            cmdLine = cliParser.parse(options, args);

        } catch (ParseException e) {
            System.err.println("Błąd: " + e.getMessage());
            formatter.printHelp("pomoc:", options);
            System.exit(1);
        }


        String reportValue = cmdLine.getOptionValue("r");
        int reportNumber;

        try {
            reportNumber = Integer.parseInt(reportValue);
            if (reportNumber < 1 || reportNumber > 3) {
                throw new IllegalArgumentException("Podaj, który raport wygenerować np r=1 lub r=2 lub r=3. Program nie obsługuje raportu: " + reportValue);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Argument raport (-r) może przybierać wartości od 1 do 3. Program wymaga podania parametru r z wartością od 1 do 3 np. r=1. Program nie działa bez podania tego argumentu.");
            formatter.printHelp("pomoc:", options);
            System.exit(1);
            return;
        }

        //Sprawdza poprawność ścieżki za pomocą funkcji isAbsoluteDirectoryPath
        boolean validPathProvided = false;
        for (String pathString : cmdLine.getArgs()) {
            if (isAbsoluteDirectoryPath(pathString)) {
                System.out.println("Ścieżka jest absolutną ścieżką do katalogu. Przeszukuje katalog: " + pathString);
                validPathProvided = true;
                break;
            }
        }

        if (!validPathProvided) {
            System.err.println("Nie podano jako argumentu poprawnej ścieżki do katalogu źródłowego.");
            System.exit(1);
        }


        if ((cmdLine.hasOption("r"))&&(cmdLine.getOptionValue("r"))=="1") {





        }




        //Sprawdza czy ścieżka zawiera argument diagramu i wywołuje funkcję do wykonania diagramu
        if (cmdLine.hasOption("d")) {
            System.out.println("Rysujmey diagram");

        }

        //Sprawdza czy ścieżka zawiera argument zapisu do pliku i wywołuje funkcję do zapisania raportu do pliku
        if (cmdLine.hasOption("s")) {
            String savePath = cmdLine.getOptionValue(save);
            Path path = Paths.get(savePath);
            Path catalogPaths = path.getParent();
            if (isAbsoluteDirectoryPath(String.valueOf(catalogPaths))) {
                System.out.println("zapisujemy plik");}

            else System.out.println("Niepoprawna ścieżka zapisu");



        }


    }

    public static boolean isAbsoluteDirectoryPath(String pathString) {
        Path path = Paths.get(pathString);
        return path.isAbsolute() && Files.exists(path) && Files.isDirectory(path);
    }
}


