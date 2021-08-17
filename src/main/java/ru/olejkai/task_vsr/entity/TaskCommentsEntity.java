package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "task_comments", schema = "task_vsr")
@Data
@NoArgsConstructor
public class TaskCommentsEntity {
    private Long id;
    private Long taskId;
    private Long commentId;
    private TaskEntity taskByTaskId;
    private CommentsEntity commentsByCommentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "comment_id", nullable = false)
    public Long getCommentId() {
        return commentId;
    }




    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public TaskEntity getTaskByTaskId() {
        return taskByTaskId;
    }

    public void setTaskByTaskId(TaskEntity taskByTaskId) {
        this.taskByTaskId = taskByTaskId;
    }

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public CommentsEntity getCommentsByCommentId() {
        return commentsByCommentId;
    }

    public void setCommentsByCommentId(CommentsEntity commentsByCommentId) {
        this.commentsByCommentId = commentsByCommentId;
    }
}
