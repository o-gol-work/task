package ru.olejkai.task_vsr.entity;

import javax.persistence.*;

@Entity
@Table(name = "task_status_executer", schema = "task_vsr", catalog = "")
public class TaskStatusExecuterEntity {
    private Long id;
    private Integer taskStatusExecuter;
    private TaskEntity taskById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "task_status_executer", nullable = false)
    public Integer getTaskStatusExecuter() {
        return taskStatusExecuter;
    }

    public void setTaskStatusExecuter(Integer taskStatusExecuter) {
        this.taskStatusExecuter = taskStatusExecuter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskStatusExecuterEntity that = (TaskStatusExecuterEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (taskStatusExecuter != null ? !taskStatusExecuter.equals(that.taskStatusExecuter) : that.taskStatusExecuter != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taskStatusExecuter != null ? taskStatusExecuter.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    public TaskEntity getTaskById() {
        return taskById;
    }

    public void setTaskById(TaskEntity taskById) {
        this.taskById = taskById;
    }
}
