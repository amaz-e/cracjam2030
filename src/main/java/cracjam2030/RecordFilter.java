package cracjam2030;

import java.util.ArrayList;
import java.util.List;

public class RecordFilter {
    public static List<TaskRecord> filterAndFindDatesRange(List<TaskRecord> records)
    {
        List<TaskRecord> filteredList = new ArrayList<>();

        for (TaskRecord r: records             ) {
            if (r.getProjectDate().before( Main.logger.getDateFromFilter()) || r.getProjectDate().after(Main.logger.getDateToFilter())){
                //pominięto, po za zakresem
            }
            else {
                filteredList.add(r);
                if ( r.getProjectDate().after(Main.logger.getDateHighestFound()) )
                    Main.logger.setDateHighestFound(r.getProjectDate());
                if ( r.getProjectDate().before(Main.logger.getDateLowestFound()) )
                    Main.logger.setDateLowestFound(r.getProjectDate());
            }
        }
        return filteredList;
    }
}
