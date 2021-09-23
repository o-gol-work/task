package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "task_problem", schema = "task_vsr")
@Data
@NoArgsConstructor
public class TaskProblemEntity {
    private Long parentId;
    private Long id;
    private String title;
    /*private TaskProblemEntity taskProblemByParentId;
    private Collection<DepartmentProblemEntity> departmentProblemsById;
    private Collection<TaskEntity> tasksById;
    private Collection<TaskProblemEntity> taskProblemsById;*/

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "title", nullable = true, length = 45)
    public String getTitle() {
        return title;
    }





/*
    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id",insertable = false, updatable = false)
    public TaskProblemEntity getTaskProblemByParentId() {
        return taskProblemByParentId;
    }

    public void setTaskProblemByParentId(TaskProblemEntity taskProblemByParentId) {
        this.taskProblemByParentId = taskProblemByParentId;
    }

    @OneToMany(mappedBy = "taskProblemByParentId")
    public Collection<TaskProblemEntity> getTaskProblemsById() {
        return taskProblemsById;
    }

    public void setTaskProblemsById(Collection<TaskProblemEntity> taskProblemsById) {
        this.taskProblemsById = taskProblemsById;
    }


    @OneToMany(mappedBy = "taskProblemByProblemId")
    public Collection<DepartmentProblemEntity> getDepartmentProblemsById() {
        return departmentProblemsById;
    }

    public void setDepartmentProblemsById(Collection<DepartmentProblemEntity> departmentProblemsById) {
        this.departmentProblemsById = departmentProblemsById;
    }

    @OneToMany(mappedBy = "taskProblemByTaskProblemId")
    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }*/
}
