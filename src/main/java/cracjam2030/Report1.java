package cracjam2030;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Report1 {
    public static void createReport(List<TaskRecord> records) {
        try {
            var projectHoursDictionary = records.stream().collect(Collectors.groupingBy(x -> x.getProjectName(), Collectors.summarizingDouble(x -> x.getWorkHours())));

            List sortedKeys = new ArrayList(projectHoursDictionary.keySet());
            Collections.sort(sortedKeys);

            for (var t : sortedKeys) {
                Main.logger.addLine(t + " " + projectHoursDictionary.get(t).getSum());
                Main.logger.addProjectWorkinghours(t.toString(), projectHoursDictionary.get(t).getSum());
            }
        } catch (Exception e) {
            Main.logger.addError(e.getMessage());
        }
    }
}
