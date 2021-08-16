package ru.olejkai.task_vsr.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "employee", schema = "task_vsr", catalog = "")
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
    private Collection<EmployeeCommentEntity> employeeCommentsById;
    private Collection<EmployeeRoleEntity> employeeRolesById;
    private Collection<TaskEntity> tasksById;
    private Collection<TaskEntity> tasksById_0;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tabel_number", nullable = false)
    public Integer getTabelNumber() {
        return tabelNumber;
    }

    public void setTabelNumber(Integer tabelNumber) {
        this.tabelNumber = tabelNumber;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "telephone_number", nullable = true, length = 15)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "worked", nullable = false)
    public Byte getWorked() {
        return worked;
    }

    public void setWorked(Byte worked) {
        this.worked = worked;
    }

    @Basic
    @Column(name = "post_has_department_id", nullable = false)
    public Long getPostHasDepartmentId() {
        return postHasDepartmentId;
    }

    public void setPostHasDepartmentId(Long postHasDepartmentId) {
        this.postHasDepartmentId = postHasDepartmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tabelNumber != null ? !tabelNumber.equals(that.tabelNumber) : that.tabelNumber != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (telephoneNumber != null ? !telephoneNumber.equals(that.telephoneNumber) : that.telephoneNumber != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (worked != null ? !worked.equals(that.worked) : that.worked != null) return false;
        if (postHasDepartmentId != null ? !postHasDepartmentId.equals(that.postHasDepartmentId) : that.postHasDepartmentId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tabelNumber != null ? tabelNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (worked != null ? worked.hashCode() : 0);
        result = 31 * result + (postHasDepartmentId != null ? postHasDepartmentId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "post_has_department_id", referencedColumnName = "id", nullable = false)
    public PostHasDepartmentEntity getPostHasDepartmentByPostHasDepartmentId() {
        return postHasDepartmentByPostHasDepartmentId;
    }

    public void setPostHasDepartmentByPostHasDepartmentId(PostHasDepartmentEntity postHasDepartmentByPostHasDepartmentId) {
        this.postHasDepartmentByPostHasDepartmentId = postHasDepartmentByPostHasDepartmentId;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
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
    }
}
