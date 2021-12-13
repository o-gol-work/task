package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "department_problem", schema = "task_vsr")
@Data
@NoArgsConstructor
public class DepartmentProblemEntity {
    private Long id;
    private Long departmentId;
    private Long problemId;
    private DepartmentEntity departmentByDepartmentId;
    private TaskProblemEntity taskProblemByProblemId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "department_id", nullable = true)
    public Long getDepartmentId() {
        return departmentId;
    }


    @Basic
    @Column(name = "problem_id", nullable = true)
    public Long getProblemId() {
        return problemId;
    }



    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id",insertable = false, updatable = false)
    public DepartmentEntity getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(DepartmentEntity departmentByDepartmentId) {
        this.departmentByDepartmentId = departmentByDepartmentId;
    }

    @ManyToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "id",insertable = false, updatable = false)
    public TaskProblemEntity getTaskProblemByProblemId() {
        return taskProblemByProblemId;
    }

    public void setTaskProblemByProblemId(TaskProblemEntity taskProblemByProblemId) {
        this.taskProblemByProblemId = taskProblemByProblemId;
    }
}
