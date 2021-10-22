package ru.olejkai.task_vsr.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskSearchValues {
    //Parameter of search
    String employeeIdTasker;
    String taskProblemId;
    Timestamp dateBegin;
    String employeeIdExecuter;
    String departmentIdExecuter;
    Timestamp dataFinish;
    Integer status;
    //pageable
    private Integer pageNumber;
    private Integer pageSize;
    //sorting
    private String sortColumn;
    private String sortDirection;
}
