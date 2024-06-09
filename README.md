Nazwa aplikacji: 
Cracjam2030


Opis aplikacji:
Aplikacja jest aplikacją konsolową wykonaną w języku java, ma na celu odczyt danych z kart pracy developerów w celu generowania zadanych raportów.
Generuje na podstawie plików xls oraz xlsx jeden z trzech raportów. 

Aplikacja wymaga podania ścieźki do katalogu źródłowego. Przeszukuje katalog źródłowy oraz katalogi podrzędne i wyszukuje pliki *.xls oraz *.xlsx.
Aplikacja wymaga podania argumentu -r (--raport) z wartością od 1 do 3 (np. -r=1).
Uruchomienie aplikacji z argumentem -h(--help) wyświetla pomoc.
Aplikacja może zostać urchomiona z fakultatywnymi argumentami:
-d - aplikacja dodatkowo generuje diagram przedstawiający wynik raportu
-s=PATH - aplikacja dodatkowo tworzy plik, w ktorym zapisuje raport. 

Raport 1 - Dane o ilości godzin poświęconych na każdy z projektów

Raport 2 - Dane o ilości godzin spędzonych przez danego dev'a w projekcie

Raport 3 - Dane o ilości godzin spędzonych przy określonych zadaniach we wszstkich projektach (posortowane malejąco)

Przykłady użycia:

 -r=1 c:\Documents\User\ExcelFiles
 
 -r=2 -d c:\Documents\User\ExcelFiles
 
 -r=3 c:\Documents\User\ExcelFiles -s=c:\Documents\User\Saves
