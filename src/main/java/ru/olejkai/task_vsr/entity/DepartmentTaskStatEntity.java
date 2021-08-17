package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "department_task_stat", schema = "task_vsr")
@Data
@NoArgsConstructor
public class DepartmentTaskStatEntity {
    private Long id;
    private Long completed;
    private Long uncompleted;
    private Long stoped;
    private Long total;
    private DepartmentEntity departmentById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "completed", nullable = false)
    public Long getCompleted() {
        return completed;
    }


    @Basic
    @Column(name = "uncompleted", nullable = false)
    public Long getUncompleted() {
        return uncompleted;
    }


    @Basic
    @Column(name = "stoped", nullable = false)
    public Long getStoped() {
        return stoped;
    }


    @Basic
    @Column(name = "total", nullable = false)
    public Long getTotal() {
        return total;
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
