package cracjam2030;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Runner {

    public static void executeProgram(String[] args) throws IOException {
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

        Option from = Option.builder("f")
                .required(false)
                .hasArg(true)
                .longOpt("from")
                .desc("Data początkowa przeszukiwania")
                .build();

        Option to = Option.builder("t")
                .required(false)
                .hasArg(true)
                .longOpt("to")
                .desc("Data końcowa przeszukiwania")
                .build();

        options.addOption(report);
        options.addOption(diagram);
        options.addOption(save);
        options.addOption(help);
        options.addOption(from);
        options.addOption(to);


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
            Main.logger.addError("Błąd: " + e.getMessage());
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
            Main.logger.addError("Argument raport (-r) może przybierać wartości od 1 do 3. Program wymaga podania parametru r z wartością od 1 do 3 np. r=1. Program nie działa bez podania tego argumentu.");
            formatter.printHelp("pomoc:", options);
            System.exit(1);
            return;
        }

        //Sprawdza poprawność ścieżki za pomocą funkcji isAbsoluteDirectoryPath
        boolean validPathProvided = false;
        String validPath = "";
        for (String pathString : cmdLine.getArgs()) {
            if (isAbsoluteDirectoryPath(pathString)) {
                //System.out.println("Ścieżka jest absolutną ścieżką do katalogu. Przeszukuje katalog: " + pathString);
                validPathProvided = true;
                validPath = pathString;
                break;
            }
        }

        if (!validPathProvided) {
            Main.logger.addError("Nie podano jako argumentu poprawnej ścieżki do katalogu źródłowego.");
            System.exit(1);
        }


        if (cmdLine.hasOption("r")){

            switch (cmdLine.getOptionValue("r")) {
                case "1":
                    executeReport(validPath, "1");
                    break;
                case "2":
                    executeReport(validPath, "2");
                    break;
                case "3":
                    executeReport(validPath, "3");
                    break;
                default:
                    System.out.println("Argument raport (-r) może przybierać wartości od 1 do 3. Program wymaga podania parametru r z wartością od 1 do 3 np. r1. Program nie obsługuje raportu: " + cmdLine.getOptionValue("r"));
                    break;
            }
        }



        //Sprawdza czy ścieżka zawiera argument zapisu do pliku i wywołuje funkcję do zapisania raportu do pliku
        if (cmdLine.hasOption("s")) {
            String savePath = cmdLine.getOptionValue(save);
            Path path = Paths.get(savePath);
            Path catalogPaths = path.getParent();
            if (isAbsoluteDirectoryPath(String.valueOf(catalogPaths))) {
                System.out.println("zapisujemy plik");
                XLSImporter.importToXLS(cmdLine.getOptionValue("r"));
            }

            else System.out.println("Niepoprawna ścieżka zapisu");
        }

        //Sprawdza czy ścieżka zawiera argument diagramu i wywołuje funkcję do wykonania diagramu
        if (cmdLine.hasOption("d")) {
            System.out.println("Rysujemy diagram");
            Chart.generateChart(cmdLine.getOptionValue("r"));
        }


    }



    public static boolean isAbsoluteDirectoryPath(String pathString) {
        Path path = Paths.get(pathString);
        return path.isAbsolute() && Files.exists(path) && Files.isDirectory(path);
    }

    public static void executeReport(String path, String reportType) throws IOException {
        XLSLoader loader = new XLSLoader();
        ExcelFileFinder fileFinder = new ExcelFileFinder();
        List<String> spreadsheetpathList = new ArrayList<>();
        try {
            spreadsheetpathList = fileFinder.findExcelFiles(path);
        }  catch (ExcelFilesNotFoundException e) {}

            List<TaskRecord> recordData = new ArrayList<>();

            for (String s : spreadsheetpathList) {
                loader.loadXLS(s);
                recordData.addAll(loader.getRecords());
            }



        switch (reportType) {
            case "1":
                Main.logger.addLine("\nRaport 1:");
                Report1.createReport(recordData);
                ReportPrinter.printReport(Main.logger);
                break;
            case "2":
                Main.logger.addLine("\nRaport 2:");
                Report2.createReport(recordData);
                ReportPrinter.printReport(Main.logger);
                break;
            case "3":
                Main.logger.addLine("\nRaport 3:");
                Report3.createReport(recordData);
                ReportPrinter.printReport(Main.logger);
                break;

        }
    }
}


