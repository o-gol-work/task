package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "employee_role", schema = "task_vsr")
@Data
@NoArgsConstructor
public class EmployeeRoleEntity implements GrantedAuthority {
    private Long id;
    private String role;
    private Long employeeId;
    private String authority;
//    private EmployeeEntity employeeByEmployeeId;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return role;
    }



    @Override
    public String getAuthority() {
        return role;
    }


    @Basic
    @Column(name = "employee_id", nullable = false)
    public Long getEmployeeId() {
        return employeeId;
    }

    public EmployeeRoleEntity(String role, Long employeeId) {
        this.role = role;
        this.employeeId = employeeId;
    }

    public EmployeeRoleEntity(String role) {
        this.role = role;
    }

    /* @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public EmployeeEntity getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeEntity employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }*/
}
