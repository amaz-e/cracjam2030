package cracjam2030;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockDataset {

    public List<TaskRecord> GetMockTaskRecordList() {
        List<TaskRecord> taskRecords = new ArrayList<>();

        Date date = new Date();
        for (int i = 0; i < 30; i++) {
            TaskRecord taskRecord = new TaskRecord(
                    "Zadanie" + (i % 5 + 1),
                    "Developer" + (i % 3) + 1,
                    "Projekt" + (i % 2 + 1),
                    date,
                    (i + 1) * 2,
                    6,
                    2020
            );
            taskRecords.add(taskRecord);
        }
        return taskRecords;
    }

}
