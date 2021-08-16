package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "comments", schema = "task_vsr")
@Data
@NoArgsConstructor
public class CommentsEntity {
    private Long id;
    private Long taskId;
    private Timestamp dateComment;
    private Long employeeId;
    private String comment;

    /*private Collection<EmployeeCommentEntity> employeeCommentsById;
    private Collection<TaskCommentsEntity> taskCommentsById;*/

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }



    @Basic
    @Column(name = "task_id", nullable = false)
    public Long getTaskId() {
        return taskId;
    }


    @Basic
    @Column(name = "date_comment", nullable = false)
    public Timestamp getDateComment() {
        return dateComment;
    }


    @Basic
    @Column(name = "employee_id", nullable = false)
    public Long getEmployeeId() {
        return employeeId;
    }


    @Basic
    @Column(name = "comment", nullable = false, length = 255)
    public String getComment() {
        return comment;
    }


   /*
   @OneToMany(mappedBy = "commentsByCommentId")
    public Collection<EmployeeCommentEntity> getEmployeeCommentsById() {
        return employeeCommentsById;
    }

    public void setEmployeeCommentsById(Collection<EmployeeCommentEntity> employeeCommentsById) {
        this.employeeCommentsById = employeeCommentsById;
    }

    @OneToMany(mappedBy = "commentsByCommentId")
    public Collection<TaskCommentsEntity> getTaskCommentsById() {
        return taskCommentsById;
    }

    public void setTaskCommentsById(Collection<TaskCommentsEntity> taskCommentsById) {
        this.taskCommentsById = taskCommentsById;
    }*/


}
