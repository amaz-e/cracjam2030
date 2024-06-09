package cracjam2030;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Map.Entry;

public class Report3 {
    public static void createReport(List<TaskRecord> records) {
        try {
            for (TaskRecord t : records
            ) {
                if (t.getTaskName() == null) {
                    t.setTaskName("null");
                    Main.logger.addError(t.getDevelopername() + " " + t.getProjectName() + " task is null. Set task name");
                }
            }
            Map<String, Double> ActivityHoursDictionary = records.stream()
                    .collect(Collectors.groupingBy(TaskRecord::getTaskName, Collectors.summingDouble(TaskRecord::getWorkHours)));
            ActivityHoursDictionary.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).forEach(Report3::addToLogger);

        } catch (Exception e) {
            Main.logger.addError(e.getMessage());
        }
    }

    private static void addToLogger(Entry<String, Double> entry) {
        System.out.println(" " + entry.getKey() + " " + entry.getValue());
        Main.logger.addLine(entry.getKey() + " " + entry.getValue());
        Main.logger.addTaskWorkingHours(entry.getKey(), entry.getValue());
    }
}
