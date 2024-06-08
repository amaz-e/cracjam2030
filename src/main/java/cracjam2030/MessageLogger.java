package cracjam2030;

import java.util.ArrayList;
import java.util.List;

public class MessageLogger {
    private List<String> reportLines;
    private  List<String> reportErrors;

    public void addLine(String message){
        reportLines.add(message);
    }
    public void addError(String errorMessage){
        reportErrors.add(errorMessage);
    }

    public MessageLogger() {
        reportLines = new ArrayList<>();
        reportErrors = new ArrayList<>();
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
}
