package cracjam2030;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Report3 {
    public static void createReport(List<TaskRecord> records){
        try {
            for (TaskRecord t: records
                 ) {
                if(t.getTaskName() == null){
                    t.setTaskName("null");
                    Main.logger.addError(t.getDevelopername() + " " + t.getProjectName() + " task is null. Set task name");
                }
            }
            var ActivityHoursDictionary = records.stream()
                    .collect(Collectors.groupingBy(TaskRecord::getTaskName, Collectors.summarizingDouble(x -> x.getWorkHours())));

            List sortedKeys = new ArrayList(ActivityHoursDictionary.keySet());
            Collections.sort(sortedKeys);

            for (var t : sortedKeys) {
                Main.logger.addLine(t + " " + ActivityHoursDictionary.get(t).getSum());
                Main.logger.addProjectWorkinghours(t.toString(), ActivityHoursDictionary.get(t).getSum());
            }
        } catch (Exception e) {
            Main.logger.addError(e.getMessage());
        }
    }
}
