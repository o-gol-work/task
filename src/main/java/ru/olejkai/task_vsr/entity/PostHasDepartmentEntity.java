package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "post_has_department", schema = "task_vsr")
@Data
@NoArgsConstructor
public class PostHasDepartmentEntity {
    private Long postId;
    private Long departmentId;
    private Long id;
//    private Collection<EmployeeEntity> employeesById;
    private PostEntity postByPostId;
    private DepartmentEntity departmentByDepartmentId;

    @Basic
    @Column(name = "post_id", nullable = false)
    public Long getPostId() {
        return postId;
    }


    @Basic
    @Column(name = "department_id", nullable = false)
    public Long getDepartmentId() {
        return departmentId;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public PostHasDepartmentEntity(Long id
            ,Long idPost,String titlePost,Long parentIdPost
            ,Long parentIdDepartment, Long idDepartment, String titleDepartment, String telephoneNumberDepartment

                                   ) {
        this.id = id;
        this.postByPostId = new PostEntity(idPost, titlePost, parentIdPost);
        this.departmentByDepartmentId = new DepartmentEntity(parentIdDepartment,idDepartment, titleDepartment,telephoneNumberDepartment);
    }


    /*@OneToMany(mappedBy = "postHasDepartmentByPostHasDepartmentId")
    public Collection<EmployeeEntity> getEmployeesById() {
        return employeesById;
    }

    public void setEmployeesById(Collection<EmployeeEntity> employeesById) {
        this.employeesById = employeesById;
    }
*/

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public PostEntity getPostByPostId() {
        return postByPostId;
    }

    public void setPostByPostId(PostEntity postByPostId) {
        this.postByPostId = postByPostId;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public DepartmentEntity getDepartmentByDepartmentId() {
        return departmentByDepartmentId;
    }

    public void setDepartmentByDepartmentId(DepartmentEntity departmentByDepartmentId) {
        this.departmentByDepartmentId = departmentByDepartmentId;
    }
}
