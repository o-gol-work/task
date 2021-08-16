package ru.olejkai.task_vsr.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "department", schema = "task_vsr", catalog = "")
public class DepartmentEntity {
    private Long parentId;
    private Long id;
    private String title;
    private String telephoneNumber;
    private Long departmentTypeId;
    private Long departmentParentStructer;
    private DepartmentEntity departmentByParentId;
    private Collection<DepartmentEntity> departmentsById;
    private DepartmentTypeEntity departmentTypeByDepartmentTypeId;
    private DepartmentEntity departmentByDepartmentParentStructer;
    private Collection<DepartmentEntity> departmentsById_0;
    private Collection<DepartmentProblemEntity> departmentProblemsById;
    private DepartmentTaskStatEntity departmentTaskStatById;
    private Collection<DepartmentTaskerToDepartmentsExecutersEntity> departmentTaskerToDepartmentsExecutersById;
    private Collection<DepartmentTaskerToDepartmentsExecutersEntity> departmentTaskerToDepartmentsExecutersById_0;
    private Collection<PostHasDepartmentEntity> postHasDepartmentsById;
    private Collection<TaskEntity> tasksById;

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
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "department_type_id", nullable = true)
    public Long getDepartmentTypeId() {
        return departmentTypeId;
    }

    public void setDepartmentTypeId(Long departmentTypeId) {
        this.departmentTypeId = departmentTypeId;
    }

    @Basic
    @Column(name = "department_parent_structer", nullable = false)
    public Long getDepartmentParentStructer() {
        return departmentParentStructer;
    }

    public void setDepartmentParentStructer(Long departmentParentStructer) {
        this.departmentParentStructer = departmentParentStructer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (telephoneNumber != null ? !telephoneNumber.equals(that.telephoneNumber) : that.telephoneNumber != null)
            return false;
        if (departmentTypeId != null ? !departmentTypeId.equals(that.departmentTypeId) : that.departmentTypeId != null)
            return false;
        if (departmentParentStructer != null ? !departmentParentStructer.equals(that.departmentParentStructer) : that.departmentParentStructer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parentId != null ? parentId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (telephoneNumber != null ? telephoneNumber.hashCode() : 0);
        result = 31 * result + (departmentTypeId != null ? departmentTypeId.hashCode() : 0);
        result = 31 * result + (departmentParentStructer != null ? departmentParentStructer.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public DepartmentEntity getDepartmentByParentId() {
        return departmentByParentId;
    }

    public void setDepartmentByParentId(DepartmentEntity departmentByParentId) {
        this.departmentByParentId = departmentByParentId;
    }

    @OneToMany(mappedBy = "departmentByParentId")
    public Collection<DepartmentEntity> getDepartmentsById() {
        return departmentsById;
    }

    public void setDepartmentsById(Collection<DepartmentEntity> departmentsById) {
        this.departmentsById = departmentsById;
    }

    @ManyToOne
    @JoinColumn(name = "department_type_id", referencedColumnName = "id")
    public DepartmentTypeEntity getDepartmentTypeByDepartmentTypeId() {
        return departmentTypeByDepartmentTypeId;
    }

    public void setDepartmentTypeByDepartmentTypeId(DepartmentTypeEntity departmentTypeByDepartmentTypeId) {
        this.departmentTypeByDepartmentTypeId = departmentTypeByDepartmentTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "department_parent_structer", referencedColumnName = "id", nullable = false)
    public DepartmentEntity getDepartmentByDepartmentParentStructer() {
        return departmentByDepartmentParentStructer;
    }

    public void setDepartmentByDepartmentParentStructer(DepartmentEntity departmentByDepartmentParentStructer) {
        this.departmentByDepartmentParentStructer = departmentByDepartmentParentStructer;
    }

    @OneToMany(mappedBy = "departmentByDepartmentParentStructer")
    public Collection<DepartmentEntity> getDepartmentsById_0() {
        return departmentsById_0;
    }

    public void setDepartmentsById_0(Collection<DepartmentEntity> departmentsById_0) {
        this.departmentsById_0 = departmentsById_0;
    }

    @OneToMany(mappedBy = "departmentByDepartmentId")
    public Collection<DepartmentProblemEntity> getDepartmentProblemsById() {
        return departmentProblemsById;
    }

    public void setDepartmentProblemsById(Collection<DepartmentProblemEntity> departmentProblemsById) {
        this.departmentProblemsById = departmentProblemsById;
    }

    @OneToOne(mappedBy = "departmentById")
    public DepartmentTaskStatEntity getDepartmentTaskStatById() {
        return departmentTaskStatById;
    }

    public void setDepartmentTaskStatById(DepartmentTaskStatEntity departmentTaskStatById) {
        this.departmentTaskStatById = departmentTaskStatById;
    }

    @OneToMany(mappedBy = "departmentByDepartmentTaskerId")
    public Collection<DepartmentTaskerToDepartmentsExecutersEntity> getDepartmentTaskerToDepartmentsExecutersById() {
        return departmentTaskerToDepartmentsExecutersById;
    }

    public void setDepartmentTaskerToDepartmentsExecutersById(Collection<DepartmentTaskerToDepartmentsExecutersEntity> departmentTaskerToDepartmentsExecutersById) {
        this.departmentTaskerToDepartmentsExecutersById = departmentTaskerToDepartmentsExecutersById;
    }

    @OneToMany(mappedBy = "departmentByDepartmentsExecutersIds")
    public Collection<DepartmentTaskerToDepartmentsExecutersEntity> getDepartmentTaskerToDepartmentsExecutersById_0() {
        return departmentTaskerToDepartmentsExecutersById_0;
    }

    public void setDepartmentTaskerToDepartmentsExecutersById_0(Collection<DepartmentTaskerToDepartmentsExecutersEntity> departmentTaskerToDepartmentsExecutersById_0) {
        this.departmentTaskerToDepartmentsExecutersById_0 = departmentTaskerToDepartmentsExecutersById_0;
    }

    @OneToMany(mappedBy = "departmentByDepartmentId")
    public Collection<PostHasDepartmentEntity> getPostHasDepartmentsById() {
        return postHasDepartmentsById;
    }

    public void setPostHasDepartmentsById(Collection<PostHasDepartmentEntity> postHasDepartmentsById) {
        this.postHasDepartmentsById = postHasDepartmentsById;
    }

    @OneToMany(mappedBy = "departmentByDepartmentIdExecuter")
    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }
}
