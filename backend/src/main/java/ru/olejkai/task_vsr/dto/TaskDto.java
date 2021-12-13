package ru.olejkai.task_vsr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
@Data

@NoArgsConstructor

public class TaskDto {
    Long id;
    Timestamp dataFinish;
    Timestamp dateBegin;
    Long parentId;
    Integer status;
    Long idDepartment;
    String titleDepartment;
    Long idEmployeeExec;
    String nameExec;
    String surnameExec;
    Integer tabelNumberExec;
    Long idEmployeeTasker;
    String nameTasker;
    String surnameTasker;
    Integer tabelNumberTasker;
    Long idProblem;
    String titelProblem;


    public TaskDto(Long id, Timestamp dataFinish, Timestamp dateBegin, Long parentId, Integer status, Long idDepartment, String titleDepartment, Long idEmployeeExec, String nameExec, String surnameExec, Integer tabelNumberExec, Long idEmployeeTasker, String nameTasker, String surnameTasker, Integer tabelNumberTasker, Long idProblem, String titelProblem) {
        this.id = id;
        this.dataFinish = dataFinish;
        this.dateBegin = dateBegin;
        this.parentId = parentId;
        this.status = status;
        this.idDepartment = idDepartment;
        this.titleDepartment = titleDepartment;
        this.idEmployeeExec = idEmployeeExec;
        this.nameExec = nameExec;
        this.surnameExec = surnameExec;
        this.tabelNumberExec = tabelNumberExec;
        this.idEmployeeTasker = idEmployeeTasker;
        this.nameTasker = nameTasker;
        this.surnameTasker = surnameTasker;
        this.tabelNumberTasker = tabelNumberTasker;
        this.idProblem = idProblem;
        this.titelProblem = titelProblem;
    }

    public TaskDto(Long parentId, Long idEmployeeTasker, Long idProblem) {
        this.parentId = parentId;
        this.idProblem =idProblem;
        this.idEmployeeTasker = idEmployeeTasker;

    }

    /*public TaskDto(Long idEmployeeTasker, Long idProblem) {

        this.idProblem =idProblem;
        this.idEmployeeTasker = idEmployeeTasker;

    }*/

    public TaskDto(Long parentId, Long idProblem) {

        this.parentId = parentId;
        this.idProblem =idProblem;

    }

    public TaskDto( Long idProblem) {

        this.idProblem =idProblem;

    }
}
