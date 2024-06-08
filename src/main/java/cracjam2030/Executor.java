package cracjam2030;

//        -h -- help Wyświetla wszystkie opcje programu.
//        -R1 [start date][end date] [path] Generuje Raport 1. Czas wykorzystany na projekt. Możliwość filtrowanie po start i end date. Należy podać ścieżkę całkowitą do pliku xls
//        -R2 [start date][end date] [path] Generuje Raport 2. Czas wykorzystany przez dev na projekt. Możliwość filtrowanie po start i end date. Należy podać ścieżkę całkowitą do pliku xls
//        -R3 [start date][end date] [path] Generuje Raport 1. Czas wykorzystany na zadania na projekt. Możliwość filtrowanie po start i end date. Należy podać ścieżkę całkowitą do pliku xls
//        -d Generowanie diagramu
public class Executor {

    public static void main(String[] args) {

        if (args.length > 0) {
            System.out.printf("Function called with params");
        }
        System.out.printf("Function called without params");

        }
    }


