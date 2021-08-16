package ru.olejkai.task_vsr.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee_comment", schema = "task_vsr", catalog = "")
public class EmployeeCommentEntity {
    private Long id;
    private Long employeeId;
    private Long commentId;
    private EmployeeEntity employeeByEmployeeId;
    private CommentsEntity commentsByCommentId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "employee_id", nullable = false)
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "comment_id", nullable = true)
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeCommentEntity that = (EmployeeCommentEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (commentId != null ? !commentId.equals(that.commentId) : that.commentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (commentId != null ? commentId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    public EmployeeEntity getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(EmployeeEntity employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    public CommentsEntity getCommentsByCommentId() {
        return commentsByCommentId;
    }

    public void setCommentsByCommentId(CommentsEntity commentsByCommentId) {
        this.commentsByCommentId = commentsByCommentId;
    }
}
