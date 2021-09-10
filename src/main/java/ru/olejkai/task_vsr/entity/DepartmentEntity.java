package ru.olejkai.task_vsr.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collection;





@Entity
@Table(name = "department", schema = "task_vsr")
@Data
@NoArgsConstructor
public class DepartmentEntity {
    private Long parentId;
    private Long id;
    private String title;
    private String telephoneNumber;
    private DepartmentEntity DepartmentEntityById;
//    private Collection<DepartmentEntity> departmentsById;
    /*private Long departmentTypeId;
    private Long departmentParentStructer;
    private DepartmentEntity departmentByParentId;
    private DepartmentTypeEntity departmentTypeByDepartmentTypeId;
    private DepartmentEntity departmentByDepartmentParentStructer;
    private Collection<DepartmentEntity> departmentsById_0;
    private Collection<DepartmentProblemEntity> departmentProblemsById;
    private DepartmentTaskStatEntity departmentTaskStatById;
    private Collection<DepartmentTaskerToDepartmentsExecutersEntity> departmentTaskerToDepartmentsExecutersById;
    private Collection<DepartmentTaskerToDepartmentsExecutersEntity> departmentTaskerToDepartmentsExecutersById_0;
    private Collection<PostHasDepartmentEntity> postHasDepartmentsById;
    private Collection<TaskEntity> tasksById;*/

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }


    @Basic
    @Column(name = "telephone_number", nullable = true, length = 15)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "parent_id", foreignKey=@ForeignKey(name="fk_department_parent"), insertable = false, updatable = false)
    public DepartmentEntity getDepartmentEntityById() {
        return DepartmentEntityById;
    }

    /*@ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id",insertable = false, updatable = false)
    public DepartmentEntity getDepartmentByParentId() {
        return departmentByParentId;
    }*/


    /*@OneToMany(mappedBy = "departmentByParentId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name="parent_id")
    public Collection<DepartmentEntity> getDepartmentsById() {
        return departmentsById;
    }*/


    /*@Basic
    @Column(name = "department_type_id", nullable = true)
    public Long getDepartmentTypeId() {
        return departmentTypeId;
    }



    @Basic
    @Column(name = "department_parent_structer", nullable = false)
    public Long getDepartmentParentStructer() {
        return departmentParentStructer;
    }





    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id",insertable = false, updatable = false)
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
    @JoinColumn(name = "department_type_id", referencedColumnName = "id",insertable = false, updatable = false)
    public DepartmentTypeEntity getDepartmentTypeByDepartmentTypeId() {
        return departmentTypeByDepartmentTypeId;
    }

    public void setDepartmentTypeByDepartmentTypeId(DepartmentTypeEntity departmentTypeByDepartmentTypeId) {
        this.departmentTypeByDepartmentTypeId = departmentTypeByDepartmentTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "department_parent_structer", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
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
    }*/
}
