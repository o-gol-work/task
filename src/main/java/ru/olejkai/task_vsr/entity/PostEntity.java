package ru.olejkai.task_vsr.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "post", schema = "task_vsr", catalog = "")
public class PostEntity {
    private Long id;
    private String title;
    private Long parentId;
    private PostEntity postByParentId;
    private Collection<PostEntity> postsById;
    private Collection<PostHasDepartmentEntity> postHasDepartmentsById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public PostEntity getPostByParentId() {
        return postByParentId;
    }

    public void setPostByParentId(PostEntity postByParentId) {
        this.postByParentId = postByParentId;
    }

    @OneToMany(mappedBy = "postByParentId")
    public Collection<PostEntity> getPostsById() {
        return postsById;
    }

    public void setPostsById(Collection<PostEntity> postsById) {
        this.postsById = postsById;
    }

    @OneToMany(mappedBy = "postByPostId")
    public Collection<PostHasDepartmentEntity> getPostHasDepartmentsById() {
        return postHasDepartmentsById;
    }

    public void setPostHasDepartmentsById(Collection<PostHasDepartmentEntity> postHasDepartmentsById) {
        this.postHasDepartmentsById = postHasDepartmentsById;
    }
}
