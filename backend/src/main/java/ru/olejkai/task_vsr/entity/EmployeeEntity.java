package ru.olejkai.task_vsr.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


@Entity
@Table(name = "employee", schema = "task_vsr")
@Data
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer","postHasDepartmentByPostHasDepartmentId"})
//@JsonIgnoreProperties({"hibernateLazyInitializer","employeeRolesById"})
@JsonIgnoreProperties({
        "hibernateLazyInitializer",
        "password",
        "username",
        "accountNonLocked",
        "accountNonExpired",
        "credentialsNonExpired",
        "enabled",
//        "authorities",
//        "employeeRolesById",
        "postHasDepartmentId"

})
public class EmployeeEntity
        implements UserDetails, Serializable {
    private Long id;
    private Integer tabelNumber;
    private String username;
    private String name;
    private String surname;
    private String telephoneNumber;
    private String password;
    private Byte worked;
    private Long postHasDepartmentId;
    private PostHasDepartmentEntity postHasDepartmentByPostHasDepartmentId;



    //    private Collection<EmployeeRoleEntity> authoritiesRole;
    private Collection<EmployeeRoleEntity> employeeRolesById = new ArrayList<>();
    //    private Collection<GrantedAuthority> authorities;
    @Transient
    private Collection<? extends GrantedAuthority> authorities;
    /*@Transient
    private PostEntity post;
    @Transient
    private DepartmentEntity department;*/

    /*private Collection<EmployeeCommentEntity> employeeCommentsById;
    private Collection<TaskEntity> tasksById;
    private Collection<TaskEntity> tasksById_0;*/


    public EmployeeEntity(Long id, String username, String name, String surname, String password, Collection<GrantedAuthority> authorities,Collection<EmployeeRoleEntity> employeeRolesById) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.authorities = authorities;
        this.employeeRolesById=employeeRolesById;
    }

    public EmployeeEntity(Long id, Integer tabelNumber, String name, String surname, String password) {
        this.id = id;
        this.tabelNumber = tabelNumber;
        this.name = name;
        this.surname = surname;
        this.password = password;

    }



    public EmployeeEntity(Long id, Integer tabelNumber, String name, String surname) {
        this.id = id;
        this.tabelNumber = tabelNumber;
        this.name = name;
        this.surname = surname;
    }

    public EmployeeEntity(Long id, Integer tabelNumber, String name, String surname, String telephoneNumber, Byte worked
            ,Long idHasPostDep
            ,Long idPost,String titlePost,Long parentIdPost
            ,Long parentIdDepartment, Long idDepartment, String titleDepartment, String telephoneNumberDepartment

    ) {
        this.id = id;
        this.tabelNumber = tabelNumber;
        this.name = name;
        this.surname = surname;
        if (telephoneNumber != null)
            this.telephoneNumber = telephoneNumber;
        this.worked = worked;
        this.postHasDepartmentByPostHasDepartmentId=new PostHasDepartmentEntity( idHasPostDep
                , idPost, titlePost, parentIdPost
                , parentIdDepartment,  idDepartment,  titleDepartment,  telephoneNumberDepartment);

    }

    public EmployeeEntity(Long id, Integer tabelNumber, String name, String surname, String telephoneNumber,String password, Byte worked
            ,Long idHasPostDep
            ,Long idPost,String titlePost,Long parentIdPost
            ,Long parentIdDepartment, Long idDepartment, String titleDepartment, String telephoneNumberDepartment

    ) {
        this.id = id;
        this.tabelNumber = tabelNumber;
        this.name = name;
        this.surname = surname;
        if (telephoneNumber != null)
            this.telephoneNumber = telephoneNumber;
        this.password=password;
        this.worked = worked;
        this.postHasDepartmentByPostHasDepartmentId=new PostHasDepartmentEntity( idHasPostDep
                , idPost, titlePost, parentIdPost
                , parentIdDepartment,  idDepartment,  titleDepartment,  telephoneNumberDepartment);

    }






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
    @Column(name = "worked", nullable = false)
    public Byte getWorked() {
        return worked;
    }


    @Basic
    @Column(name = "post_has_department_id", nullable = false)
    public Long getPostHasDepartmentId() {
        return postHasDepartmentId;
    }


    @ManyToOne(
//            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "post_has_department_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public PostHasDepartmentEntity getPostHasDepartmentByPostHasDepartmentId() {
        return postHasDepartmentByPostHasDepartmentId;
    }


    //    @OneToMany(mappedBy = "employeeByEmployeeId")
    @OneToMany(
            fetch = FetchType.EAGER
//            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    public Collection<EmployeeRoleEntity> getEmployeeRolesById() {
        return employeeRolesById;
    }










    /*
     * SECURiTY
     *
     * */

    @Override
    @Basic
    @Column(name = "password", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getEmployeeRolesById().stream().map(role->new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    @Transient
    public String getUsername() {
        return username;
    }


    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient()
    public boolean isEnabled() {
        return true;
    }




    /*public void setPostHasDepartmentByPostHasDepartmentId(PostHasDepartmentEntity postHasDepartmentByPostHasDepartmentId) {
        this.postHasDepartmentByPostHasDepartmentId = postHasDepartmentByPostHasDepartmentId;
    }*/

    /*@OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<EmployeeCommentEntity> getEmployeeCommentsById() {
        return employeeCommentsById;
    }

    public void setEmployeeCommentsById(Collection<EmployeeCommentEntity> employeeCommentsById) {
        this.employeeCommentsById = employeeCommentsById;
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
