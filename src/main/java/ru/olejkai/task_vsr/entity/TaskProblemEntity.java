package ru.olejkai.task_vsr.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "task_problem", schema = "task_vsr", catalog = "")
public class TaskProblemEntity {
    private Long parentId;
    private Long id;
    private String title;
    private Collection<DepartmentProblemEntity> departmentProblemsById;
    private Collection<TaskEntity> tasksById;
    private TaskProblemEntity taskProblemByParentId;
    private Collection<TaskProblemEntity> taskProblemsById;

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskProblemEntity that = (TaskProblemEntity) o;

        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parentId != null ? parentId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
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
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
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
}
