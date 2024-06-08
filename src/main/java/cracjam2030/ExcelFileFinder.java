package cracjam2030;

        import java.io.File;
        import java.util.ArrayList;
        import java.util.List;

    public class ExcelFileFinder {

        public List<String> findExcelFiles(String directoryPath) {
            List<String> excelFilePaths = new ArrayList<>();
            searchDirectory(new File(directoryPath), excelFilePaths);
            return excelFilePaths;
        }

        private void searchDirectory(File directory, List<String> excelFilePaths) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        searchDirectory(file, excelFilePaths);  // REKURENCJA
                    } else if (file.getName().endsWith(".xls") || file.getName().endsWith(".xlsx")) {
                        excelFilePaths.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }



