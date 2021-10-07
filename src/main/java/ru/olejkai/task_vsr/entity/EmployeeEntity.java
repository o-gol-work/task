package ru.olejkai.task_vsr.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "employee", schema = "task_vsr")
@Data
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer","postHasDepartmentByPostHasDepartmentId"})
//@JsonIgnoreProperties({"hibernateLazyInitializer","employeeRolesById"})
/*@JsonIgnoreProperties({
        "hibernateLazyInitializer",
        "password",
        "username",
        "accountNonLocked",
        "accountNonExpired",
        "credentialsNonExpired",
        "enabled",
        "authorities"
})*/
public class EmployeeEntity
        implements UserDetails
{
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
//    private Collection<EmployeeRoleEntity> authorities;
    private Collection<EmployeeRoleEntity> employeeRolesById;
    private Collection<GrantedAuthority> authorities;

    /*private Collection<EmployeeCommentEntity> employeeCommentsById;
    private Collection<TaskEntity> tasksById;
    private Collection<TaskEntity> tasksById_0;*/


    public EmployeeEntity(Long id, String username, String name, String surname, String password, Collection<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.authorities = authorities;
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
    @JoinColumn(name = "post_has_department_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public PostHasDepartmentEntity getPostHasDepartmentByPostHasDepartmentId() {
        return postHasDepartmentByPostHasDepartmentId;
    }




//    @OneToMany(mappedBy = "employeeByEmployeeId")
    @OneToMany(
            fetch = FetchType.EAGER
//            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "employee_id")
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
        return getEmployeeRolesById();
    }

    @Override
    @Transient
    public String getUsername() {
        return getTabelNumber().toString();
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
