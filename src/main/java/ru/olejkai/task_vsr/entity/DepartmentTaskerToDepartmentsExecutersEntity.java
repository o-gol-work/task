package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "department_tasker_to_departments_executers", schema = "task_vsr")
@Data
@NoArgsConstructor
public class DepartmentTaskerToDepartmentsExecutersEntity {
    private Long id;
    private Long departmentTaskerId;
    private Long departmentsExecutersIds;
    private DepartmentEntity departmentByDepartmentTaskerId;
    private DepartmentEntity departmentByDepartmentsExecutersIds;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "department_tasker_id", nullable = true)
    public Long getDepartmentTaskerId() {
        return departmentTaskerId;
    }


    @Basic
    @Column(name = "departments_executers_ids", nullable = true)
    public Long getDepartmentsExecutersIds() {
        return departmentsExecutersIds;
    }




    @ManyToOne
    @JoinColumn(name = "department_tasker_id", referencedColumnName = "id",insertable = false, updatable = false)
    public DepartmentEntity getDepartmentByDepartmentTaskerId() {
        return departmentByDepartmentTaskerId;
    }

    public void setDepartmentByDepartmentTaskerId(DepartmentEntity departmentByDepartmentTaskerId) {
        this.departmentByDepartmentTaskerId = departmentByDepartmentTaskerId;
    }

    @ManyToOne
    @JoinColumn(name = "departments_executers_ids", referencedColumnName = "id",insertable = false, updatable = false)
    public DepartmentEntity getDepartmentByDepartmentsExecutersIds() {
        return departmentByDepartmentsExecutersIds;
    }

    public void setDepartmentByDepartmentsExecutersIds(DepartmentEntity departmentByDepartmentsExecutersIds) {
        this.departmentByDepartmentsExecutersIds = departmentByDepartmentsExecutersIds;
    }
}
