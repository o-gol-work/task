package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "employee_comment", schema = "task_vsr")
@Data
@NoArgsConstructor
public class EmployeeCommentEntity {
    private Long id;
    private Long employeeId;
    private Long commentId;
    private EmployeeEntity employeeByEmployeeId;
    private CommentsEntity commentsByCommentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "employee_id", nullable = false)
    public Long getEmployeeId() {
        return employeeId;
    }


    @Basic
    @Column(name = "comment_id", nullable = true)
    public Long getCommentId() {
        return commentId;
    }



    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public EmployeeEntity getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeEntity employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id",insertable = false, updatable = false)
    public CommentsEntity getCommentsByCommentId() {
        return commentsByCommentId;
    }

    public void setCommentsByCommentId(CommentsEntity commentsByCommentId) {
        this.commentsByCommentId = commentsByCommentId;
    }
}
