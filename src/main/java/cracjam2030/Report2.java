package cracjam2030;

import java.util.ArrayList;
import java.util.List;

public class Report2 {
    public void createReport(){
        List<TaskRecord> allRecords = new MockDataset().GetMockTaskRecordList();
        for(TaskRecord i:allRecords){
            System.out.println(i.getDevelopername());
        }

    }
}
