package ru.olejkai.task_vsr.entity;

import javax.persistence.*;

@Entity
@Table(name = "task_comments", schema = "task_vsr", catalog = "")
public class TaskCommentsEntity {
    private Long id;
    private Long taskId;
    private Long commentId;
    private TaskEntity taskByTaskId;
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
    @Column(name = "task_id", nullable = false)
    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "comment_id", nullable = false)
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

        TaskCommentsEntity that = (TaskCommentsEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (commentId != null ? !commentId.equals(that.commentId) : that.commentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (commentId != null ? commentId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false)
    public TaskEntity getTaskByTaskId() {
        return taskByTaskId;
    }

    public void setTaskByTaskId(TaskEntity taskByTaskId) {
        this.taskByTaskId = taskByTaskId;
    }

    @ManyToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id", nullable = false)
    public CommentsEntity getCommentsByCommentId() {
        return commentsByCommentId;
    }

    public void setCommentsByCommentId(CommentsEntity commentsByCommentId) {
        this.commentsByCommentId = commentsByCommentId;
    }
}
