package cracjam2030;

import java.util.*;
import java.util.stream.Collectors;

public class Report2 {
    public static void createReport(List<TaskRecord> records){

        var projectDeveloperDictionary = records.stream()
                .collect(Collectors.groupingBy(x -> x.getDevelopername(), Collectors.summarizingDouble(x -> x.getWorkHours())));

        List sortedKeys = new ArrayList(projectDeveloperDictionary.keySet());
        Collections.sort(sortedKeys);

        for (var t : sortedKeys) {

            var projectNameDictionary = records.stream()
                    .filter(devname -> devname.getDevelopername().equals(t))
                    .collect(Collectors.groupingBy(x->x.getProjectName(), Collectors.summarizingDouble (x->x.getWorkHours())));
            List sortedProjectKeys = new ArrayList(projectNameDictionary.keySet());
            Collections.sort(sortedProjectKeys);
            for(var i : sortedProjectKeys){
                // TODO: formatowanie wyswietlania danych
                Main.logger.addLine(t+ ";" + i + " " + projectNameDictionary.get(i).getSum());
                Main.logger.addDeveloperProjectWorkinghours(t+ ";" + i,projectNameDictionary.get(i).getSum());
            }
        }

    }
}

