package ru.olejkai.task_vsr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDtoCreate {

    Long parentId;
    Long idProblem;

    public TaskDtoCreate(Long parentId, Long idProblem) {

        this.parentId = parentId;
        this.idProblem =idProblem;

    }
}
