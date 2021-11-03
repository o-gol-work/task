package ru.olejkai.task_vsr.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "post", schema = "task_vsr")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PostEntity {
    private Long id;
    private String title;
    private Long parentId;
    /*private PostEntity postByParentId;
    private Collection<PostEntity> postsById;
    private Collection<PostHasDepartmentEntity> postHasDepartmentsById;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }


    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }




    /*@ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id",insertable = false, updatable = false)
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
    }*/
}
