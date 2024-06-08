package cracjam2030;

import java.util.*;

public class MessageLogger {
    private List<String> reportLines;
    private  List<String> reportErrors;

    private TreeMap<String, Double> raport1Data;

    public void addLine(String message){
        reportLines.add(message);
    }
    public void addError(String errorMessage){
        reportErrors.add(errorMessage);
    }

    public void addProjectWorkinghours(String project, double workingHours){
        raport1Data.put(project, workingHours);
    }

    public MessageLogger() {
        reportLines = new ArrayList<>();
        reportErrors = new ArrayList<>();
        raport1Data = new TreeMap<>();
    }

    public List<String> getReportLines() {
        return reportLines;
    }

    public void setReportLines(List<String> reportLines) {
        this.reportLines = reportLines;
    }

    public List<String> getReportErrors() {
        return reportErrors;
    }

    public void setReportErrors(List<String> reportErrors) {
        this.reportErrors = reportErrors;
    }

    public TreeMap<String, Double> getRaport1Data() {
        return raport1Data;
    }

    public void setRaport1Data(TreeMap<String, Double> raport1Data) {
        this.raport1Data = raport1Data;
    }
}