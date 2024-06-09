package cracjam2030;

import java.util.*;


public class MessageLogger {
    private List<String> reportLines;
    private List<String> reportErrors;


    private TreeMap<String, Double> raport1Data;
    private TreeMap<String, Double> raport2Data;
    private LinkedHashMap<String, Double> raport3Data;

    private Date dateLowestFound;
    private Date dateHighestFound;
    private Date dateFromFilter;
    private Date dateToFilter;

    public void addLine(String message) {
        reportLines.add(message);
    }

    public void addError(String errorMessage) {
        reportErrors.add(errorMessage);
    }

    public void addProjectWorkinghours(String project, double workingHours) {
        raport1Data.put(project, workingHours);
    }

    public void addDeveloperProjectWorkinghours(String project, double workingHours) {
        raport2Data.put(project, workingHours);
    }

    public void addTaskWorkingHours(String task, double workingHours) {

        raport3Data.put(task, workingHours);
    }

    public MessageLogger() {
        reportLines = new ArrayList<>();
        reportErrors = new ArrayList<>();
        raport1Data = new TreeMap<>();
        raport2Data = new TreeMap<>();
        raport3Data = new LinkedHashMap<>();
        dateFromFilter = new Date(Long.MIN_VALUE);
        dateToFilter = new Date(Long.MAX_VALUE);
        dateLowestFound = new Date(Long.MAX_VALUE);
        dateHighestFound =new Date(Long.MIN_VALUE);
    }

    public List<String> getReportLines() {
        return reportLines;
    }

    public boolean haveErrors() {
        return !reportErrors.isEmpty();
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

    public TreeMap<String, Double> getRaport2Data() {
        return raport2Data;
    }

    public LinkedHashMap<String, Double> getRaport3Data() {
        return raport3Data;
    }

    public void setRaport1Data(TreeMap<String, Double> raport1Data) {

        this.raport1Data = raport1Data;
    }

    public void setRaport2Data(TreeMap<String, Double> raport2Data) {
        this.raport2Data = raport2Data;
    }

    public void setRaport3Data(LinkedHashMap<String, Double> raport3Data) {

        this.raport3Data = raport3Data;
    }

    public Date getDateLowestFound() {
        return dateLowestFound;
    }

    public void setDateLowestFound(Date dateLowestFound) {
        this.dateLowestFound = dateLowestFound;
    }

    public Date getDateHighestFound() {
        return dateHighestFound;
    }

    public void setDateHighestFound(Date dateHighestFound) {
        this.dateHighestFound = dateHighestFound;
    }

    public Date getDateFromFilter() {
        return dateFromFilter;
    }

    public void setDateFromFilter(Date dateFromFilter) {
        this.dateFromFilter = dateFromFilter;
    }

    public Date getDateToFilter() {
        return dateToFilter;
    }

    public void setDateToFilter(Date dateToFilter) {
        this.dateToFilter = dateToFilter;
    }
}
