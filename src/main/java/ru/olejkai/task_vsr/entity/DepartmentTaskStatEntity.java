package ru.olejkai.task_vsr.entity;

import javax.persistence.*;

@Entity
@Table(name = "department_task_stat", schema = "task_vsr", catalog = "")
public class DepartmentTaskStatEntity {
    private Long id;
    private Long completed;
    private Long uncompleted;
    private Long stoped;
    private Long total;
    private DepartmentEntity departmentById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "completed", nullable = false)
    public Long getCompleted() {
        return completed;
    }

    public void setCompleted(Long completed) {
        this.completed = completed;
    }

    @Basic
    @Column(name = "uncompleted", nullable = false)
    public Long getUncompleted() {
        return uncompleted;
    }

    public void setUncompleted(Long uncompleted) {
        this.uncompleted = uncompleted;
    }

    @Basic
    @Column(name = "stoped", nullable = false)
    public Long getStoped() {
        return stoped;
    }

    public void setStoped(Long stoped) {
        this.stoped = stoped;
    }

    @Basic
    @Column(name = "total", nullable = false)
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentTaskStatEntity that = (DepartmentTaskStatEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (completed != null ? !completed.equals(that.completed) : that.completed != null) return false;
        if (uncompleted != null ? !uncompleted.equals(that.uncompleted) : that.uncompleted != null) return false;
        if (stoped != null ? !stoped.equals(that.stoped) : that.stoped != null) return false;
        if (total != null ? !total.equals(that.total) : that.total != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        result = 31 * result + (uncompleted != null ? uncompleted.hashCode() : 0);
        result = 31 * result + (stoped != null ? stoped.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    public DepartmentEntity getDepartmentById() {
        return departmentById;
    }

    public void setDepartmentById(DepartmentEntity departmentById) {
        this.departmentById = departmentById;
    }
}
