package cracjam2030;

import java.text.MessageFormat;
import java.util.Date;

public class TaskRecord {
    private String taskName; //done
   private String developername; //done
   private String projectName; //done
   private Date projectDate; //done
   private double workHours; //done
   private int folderMonth; //
   private int getFolderYear; //

    public TaskRecord() {
    }
    public TaskRecord(String taskName, String developername, String projectName, Date projectDate, double workHours, int folderMonth, int getFolderYear) {
        this.taskName = taskName;
        this.developername = developername;
        this.projectName = projectName;
        this.projectDate = projectDate;
        this.workHours = workHours;
        this.folderMonth = folderMonth;
        this.getFolderYear = getFolderYear;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Projekt {0} zadanie {1} developer {2} godziny {3} data {4} folderYM {5} / {6}",
                projectName, taskName, developername, workHours, projectDate, getFolderYear, folderMonth);
    }

    public String getDevelopername() {
        return developername;
    }

    public void setDevelopername(String developername) {
        this.developername = developername;

    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate;
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        this.workHours = workHours;
    }

    public int getFolderMonth() {
        return folderMonth;
    }

    public void setFolderMonth(int folderMonth) {
        this.folderMonth = folderMonth;
    }

    public int getGetFolderYear() {
        return getFolderYear;
    }

    public void setGetFolderYear(int getFolderYear) {
        this.getFolderYear = getFolderYear;
    }
}
