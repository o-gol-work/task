package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "employee", schema = "task_vsr")
@Data
@NoArgsConstructor
public class EmployeeEntity {
    private Long id;
    private Integer tabelNumber;
    private String name;
    private String surname;
    private String telephoneNumber;
    private String password;
    private Byte worked;
    private Long postHasDepartmentId;
    private PostHasDepartmentEntity postHasDepartmentByPostHasDepartmentId;
    /*private Collection<EmployeeCommentEntity> employeeCommentsById;
    private Collection<EmployeeRoleEntity> employeeRolesById;
    private Collection<TaskEntity> tasksById;
    private Collection<TaskEntity> tasksById_0;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "tabel_number", nullable = false)
    public Integer getTabelNumber() {
        return tabelNumber;
    }


    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }


    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    public String getSurname() {
        return surname;
    }


    @Basic
    @Column(name = "telephone_number", nullable = true, length = 15)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }


    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }


    @Basic
    @Column(name = "worked", nullable = false)
    public Byte getWorked() {
        return worked;
    }


    @Basic
    @Column(name = "post_has_department_id", nullable = false)
    public Long getPostHasDepartmentId() {
        return postHasDepartmentId;
    }



    @ManyToOne
    @JoinColumn(name = "post_has_department_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public PostHasDepartmentEntity getPostHasDepartmentByPostHasDepartmentId() {
        return postHasDepartmentByPostHasDepartmentId;
    }

    public void setPostHasDepartmentByPostHasDepartmentId(PostHasDepartmentEntity postHasDepartmentByPostHasDepartmentId) {
        this.postHasDepartmentByPostHasDepartmentId = postHasDepartmentByPostHasDepartmentId;
    }


    /*@OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<EmployeeCommentEntity> getEmployeeCommentsById() {
        return employeeCommentsById;
    }

    public void setEmployeeCommentsById(Collection<EmployeeCommentEntity> employeeCommentsById) {
        this.employeeCommentsById = employeeCommentsById;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<EmployeeRoleEntity> getEmployeeRolesById() {
        return employeeRolesById;
    }

    public void setEmployeeRolesById(Collection<EmployeeRoleEntity> employeeRolesById) {
        this.employeeRolesById = employeeRolesById;
    }

    @OneToMany(mappedBy = "employeeByEmployeeIdTasker")
    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }

    @OneToMany(mappedBy = "employeeByEmployeeIdExecuter")
    public Collection<TaskEntity> getTasksById_0() {
        return tasksById_0;
    }

    public void setTasksById_0(Collection<TaskEntity> tasksById_0) {
        this.tasksById_0 = tasksById_0;
    }*/
}
