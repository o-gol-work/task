package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task_status_executer", schema = "task_vsr")
@Data
@NoArgsConstructor
public class TaskStatusExecuterEntity {
    private Long id;
    private Integer taskStatusExecuter;
    private TaskEntity taskById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "task_status_executer", nullable = false)
    public Integer getTaskStatusExecuter() {
        return taskStatusExecuter;
    }




    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public TaskEntity getTaskById() {
        return taskById;
    }

    public void setTaskById(TaskEntity taskById) {
        this.taskById = taskById;
    }
}
