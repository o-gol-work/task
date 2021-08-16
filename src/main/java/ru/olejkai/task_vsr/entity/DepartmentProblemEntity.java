package ru.olejkai.task_vsr.entity;

import javax.persistence.*;

@Entity
@Table(name = "department_problem", schema = "task_vsr", catalog = "")
public class DepartmentProblemEntity {
    private Long id;
    private Long departmentId;
    private Long problemId;
    private DepartmentEntity departmentByDepartmentId;
    private TaskProblemEntity taskProblemByProblemId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_id", nullable = true)
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "problem_id", nullable = true)
    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentProblemEntity that = (DepartmentProblemEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (problemId != null ? !problemId.equals(that.problemId) : that.problemId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (problemId != null ? problemId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    public DepartmentEntity getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(DepartmentEntity departmentByDepartmentId) {
        this.departmentByDepartmentId = departmentByDepartmentId;
    }

    @ManyToOne
    @JoinColumn(name = "problem_id", referencedColumnName = "id")
    public TaskProblemEntity getTaskProblemByProblemId() {
        return taskProblemByProblemId;
    }

    public void setTaskProblemByProblemId(TaskProblemEntity taskProblemByProblemId) {
        this.taskProblemByProblemId = taskProblemByProblemId;
    }
}
