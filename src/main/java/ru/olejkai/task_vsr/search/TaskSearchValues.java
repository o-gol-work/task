package ru.olejkai.task_vsr.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchValues {
    String employeeIdTasker;
    String taskProblemId;
    Timestamp dateBegin;
    String employeeIdExecuter;
    String departmentIdExecuter;
    Timestamp dataFinish;
    Integer status;
}
