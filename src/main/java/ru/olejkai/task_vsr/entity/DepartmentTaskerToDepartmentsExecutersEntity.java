package ru.olejkai.task_vsr.entity;

import javax.persistence.*;

@Entity
@Table(name = "department_tasker_to_departments_executers", schema = "task_vsr", catalog = "")
public class DepartmentTaskerToDepartmentsExecutersEntity {
    private Long id;
    private Long departmentTaskerId;
    private Long departmentsExecutersIds;
    private DepartmentEntity departmentByDepartmentTaskerId;
    private DepartmentEntity departmentByDepartmentsExecutersIds;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_tasker_id", nullable = true)
    public Long getDepartmentTaskerId() {
        return departmentTaskerId;
    }

    public void setDepartmentTaskerId(Long departmentTaskerId) {
        this.departmentTaskerId = departmentTaskerId;
    }

    @Basic
    @Column(name = "departments_executers_ids", nullable = true)
    public Long getDepartmentsExecutersIds() {
        return departmentsExecutersIds;
    }

    public void setDepartmentsExecutersIds(Long departmentsExecutersIds) {
        this.departmentsExecutersIds = departmentsExecutersIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentTaskerToDepartmentsExecutersEntity that = (DepartmentTaskerToDepartmentsExecutersEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (departmentTaskerId != null ? !departmentTaskerId.equals(that.departmentTaskerId) : that.departmentTaskerId != null)
            return false;
        if (departmentsExecutersIds != null ? !departmentsExecutersIds.equals(that.departmentsExecutersIds) : that.departmentsExecutersIds != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (departmentTaskerId != null ? departmentTaskerId.hashCode() : 0);
        result = 31 * result + (departmentsExecutersIds != null ? departmentsExecutersIds.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "department_tasker_id", referencedColumnName = "id")
    public DepartmentEntity getDepartmentByDepartmentTaskerId() {
        return departmentByDepartmentTaskerId;
    }

    public void setDepartmentByDepartmentTaskerId(DepartmentEntity departmentByDepartmentTaskerId) {
        this.departmentByDepartmentTaskerId = departmentByDepartmentTaskerId;
    }

    @ManyToOne
    @JoinColumn(name = "departments_executers_ids", referencedColumnName = "id")
    public DepartmentEntity getDepartmentByDepartmentsExecutersIds() {
        return departmentByDepartmentsExecutersIds;
    }

    public void setDepartmentByDepartmentsExecutersIds(DepartmentEntity departmentByDepartmentsExecutersIds) {
        this.departmentByDepartmentsExecutersIds = departmentByDepartmentsExecutersIds;
    }
}
