package ru.olejkai.task_vsr.entity;

import javax.persistence.*;

@Entity
@Table(name = "task_status_tasker", schema = "task_vsr", catalog = "")
public class TaskStatusTaskerEntity {
    private Long id;
    private Integer taskerStatus;
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
    @Column(name = "tasker_status", nullable = false)
    public Integer getTaskerStatus() {
        return taskerStatus;
    }

    public void setTaskerStatus(Integer taskerStatus) {
        this.taskerStatus = taskerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskStatusTaskerEntity that = (TaskStatusTaskerEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (taskerStatus != null ? !taskerStatus.equals(that.taskerStatus) : that.taskerStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taskerStatus != null ? taskerStatus.hashCode() : 0);
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
