package ru.olejkai.task_vsr.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "task", schema = "task_vsr")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","parent","children"})
public class TaskEntity {
    private Long parentId;
    private Long id;
    private Long employeeIdTasker;
    private Long taskProblemId;
//    private String taskCommentId;
    private Timestamp dateBegin;
    private Long employeeIdExecuter;
    private Long departmentIdExecuter;
    private Timestamp dataFinish;
    private Integer status;
    private Integer statusExec;
    private EmployeeEntity employeeByEmployeeIdTasker;
    private TaskProblemEntity taskProblemByTaskProblemId;
    private EmployeeEntity employeeByEmployeeIdExecuter;
    private DepartmentEntity departmentByDepartmentIdExecuter;
    private TaskEntity parent;
    private Collection<TaskEntity> children;




//    private Collection<TaskCommentsEntity> taskCommentsById;
//    private TaskStatusExecuterEntity taskStatusExecuterById;
//    private TaskStatusTaskerEntity taskStatusTaskerById;

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "employee_id_tasker", nullable = false)
    public Long getEmployeeIdTasker() {
        return employeeIdTasker;
    }


    @Basic
    @Column(name = "task_problem_id", nullable = false)
    public Long getTaskProblemId() {
        return taskProblemId;
    }


    /*@Basic
    @Column(name = "task_comment_id", nullable = true, length = 45)
    public String getTaskCommentId() {
        return taskCommentId;
    }*/


    @Basic
    @Column(name = "date_begin", nullable = false)
    public Timestamp getDateBegin() {
        return dateBegin;
    }


    @Basic
    @Column(name = "employee_id_executer", nullable = true)
    public Long getEmployeeIdExecuter() {
        return employeeIdExecuter;
    }


    @Basic
    @Column(name = "department_id_executer", nullable = true)
    public Long getDepartmentIdExecuter() {
        return departmentIdExecuter;
    }


    @Basic
    @Column(name = "data_finish", nullable = true)
    public Timestamp getDataFinish() {
        return dataFinish;
    }


    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }


    @Basic
    @Column(name = "status_exec", nullable = true)
    public Integer getStatusExec() {
        return statusExec;
    }


    /*@ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id",insertable = false, updatable = false)
    public TaskEntity getTaskByParentId() {
        return taskByParentId;
    }

    public void setTaskByParentId(TaskEntity taskByParentId) {
        this.taskByParentId = taskByParentId;
    }*/



    @ManyToOne
    @JoinColumn(name = "employee_id_tasker", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public EmployeeEntity getEmployeeByEmployeeIdTasker() {
        return employeeByEmployeeIdTasker;
    }

    /*public void setEmployeeByEmployeeIdTasker(EmployeeEntity employeeByEmployeeIdTasker) {
        this.employeeByEmployeeIdTasker = employeeByEmployeeIdTasker;
    }*/

    @ManyToOne
    @JoinColumn(name = "task_problem_id", referencedColumnName = "id", nullable = false,insertable = false, updatable = false)
    public TaskProblemEntity getTaskProblemByTaskProblemId() {
        return taskProblemByTaskProblemId;
    }

    /*public void setTaskProblemByTaskProblemId(TaskProblemEntity taskProblemByTaskProblemId) {
        this.taskProblemByTaskProblemId = taskProblemByTaskProblemId;
    }*/

    @ManyToOne
    @JoinColumn(name = "employee_id_executer", referencedColumnName = "id",insertable = false, updatable = false)
    public EmployeeEntity getEmployeeByEmployeeIdExecuter() {
        return employeeByEmployeeIdExecuter;
    }

    /*public void setEmployeeByEmployeeIdExecuter(EmployeeEntity employeeByEmployeeIdExecuter) {
        this.employeeByEmployeeIdExecuter = employeeByEmployeeIdExecuter;
    }*/

    @ManyToOne
    @JoinColumn(name = "department_id_executer", referencedColumnName = "id",insertable = false, updatable = false)
    public DepartmentEntity getDepartmentByDepartmentIdExecuter() {
        return departmentByDepartmentIdExecuter;
    }

    /*public void setDepartmentByDepartmentIdExecuter(DepartmentEntity departmentByDepartmentIdExecuter) {
        this.departmentByDepartmentIdExecuter = departmentByDepartmentIdExecuter;
    }*/


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "parent_id", foreignKey=@ForeignKey(name="fk_task_task_parent"), insertable = false, updatable = false)
    public TaskEntity getParent() {
        return parent;
    }




    @OneToMany(fetch = FetchType.LAZY
            , cascade = CascadeType.ALL
    )
    @JoinColumn(name = "parent_id")
    public Collection<TaskEntity> getChildren (){
        return children;
    }



   /* @OneToOne(mappedBy = "taskById")
    public TaskStatusExecuterEntity getTaskStatusExecuterById() {
        return taskStatusExecuterById;
    }

    public void setTaskStatusExecuterById(TaskStatusExecuterEntity taskStatusExecuterById) {
        this.taskStatusExecuterById = taskStatusExecuterById;
    }

    @OneToOne(mappedBy = "taskById")
    public TaskStatusTaskerEntity getTaskStatusTaskerById() {
        return taskStatusTaskerById;
    }

    public void setTaskStatusTaskerById(TaskStatusTaskerEntity taskStatusTaskerById) {
        this.taskStatusTaskerById = taskStatusTaskerById;
    }*/

    /*@OneToMany(mappedBy = "taskByParentId")
    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }*/

    /*@OneToMany(mappedBy = "taskByTaskId")
    public Collection<TaskCommentsEntity> getTaskCommentsById() {
        return taskCommentsById;
    }

    public void setTaskCommentsById(Collection<TaskCommentsEntity> taskCommentsById) {
        this.taskCommentsById = taskCommentsById;
    }*/
}
