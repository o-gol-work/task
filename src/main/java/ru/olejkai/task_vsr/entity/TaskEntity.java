package ru.olejkai.task_vsr.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "task", schema = "task_vsr", catalog = "")
public class TaskEntity {
    private Long parentId;
    private Long id;
    private Long employeeIdTasker;
    private Long taskProblemId;
    private String taskCommentId;
    private Timestamp dateBegin;
    private Long employeeIdExecuter;
    private Long departmentIdExecuter;
    private Timestamp dataFinish;
    private Integer status;
    private Integer statusExec;
    private TaskEntity taskByParentId;
    private Collection<TaskEntity> tasksById;
    private EmployeeEntity employeeByEmployeeIdTasker;
    private TaskProblemEntity taskProblemByTaskProblemId;
    private EmployeeEntity employeeByEmployeeIdExecuter;
    private DepartmentEntity departmentByDepartmentIdExecuter;
    private Collection<TaskCommentsEntity> taskCommentsById;
    private TaskStatusExecuterEntity taskStatusExecuterById;
    private TaskStatusTaskerEntity taskStatusTaskerById;

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "employee_id_tasker", nullable = false)
    public Long getEmployeeIdTasker() {
        return employeeIdTasker;
    }

    public void setEmployeeIdTasker(Long employeeIdTasker) {
        this.employeeIdTasker = employeeIdTasker;
    }

    @Basic
    @Column(name = "task_problem_id", nullable = false)
    public Long getTaskProblemId() {
        return taskProblemId;
    }

    public void setTaskProblemId(Long taskProblemId) {
        this.taskProblemId = taskProblemId;
    }

    @Basic
    @Column(name = "task_comment_id", nullable = true, length = 45)
    public String getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(String taskCommentId) {
        this.taskCommentId = taskCommentId;
    }

    @Basic
    @Column(name = "date_begin", nullable = false)
    public Timestamp getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Timestamp dateBegin) {
        this.dateBegin = dateBegin;
    }

    @Basic
    @Column(name = "employee_id_executer", nullable = true)
    public Long getEmployeeIdExecuter() {
        return employeeIdExecuter;
    }

    public void setEmployeeIdExecuter(Long employeeIdExecuter) {
        this.employeeIdExecuter = employeeIdExecuter;
    }

    @Basic
    @Column(name = "department_id_executer", nullable = true)
    public Long getDepartmentIdExecuter() {
        return departmentIdExecuter;
    }

    public void setDepartmentIdExecuter(Long departmentIdExecuter) {
        this.departmentIdExecuter = departmentIdExecuter;
    }

    @Basic
    @Column(name = "data_finish", nullable = true)
    public Timestamp getDataFinish() {
        return dataFinish;
    }

    public void setDataFinish(Timestamp dataFinish) {
        this.dataFinish = dataFinish;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "status_exec", nullable = true)
    public Integer getStatusExec() {
        return statusExec;
    }

    public void setStatusExec(Integer statusExec) {
        this.statusExec = statusExec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (employeeIdTasker != null ? !employeeIdTasker.equals(that.employeeIdTasker) : that.employeeIdTasker != null)
            return false;
        if (taskProblemId != null ? !taskProblemId.equals(that.taskProblemId) : that.taskProblemId != null)
            return false;
        if (taskCommentId != null ? !taskCommentId.equals(that.taskCommentId) : that.taskCommentId != null)
            return false;
        if (dateBegin != null ? !dateBegin.equals(that.dateBegin) : that.dateBegin != null) return false;
        if (employeeIdExecuter != null ? !employeeIdExecuter.equals(that.employeeIdExecuter) : that.employeeIdExecuter != null)
            return false;
        if (departmentIdExecuter != null ? !departmentIdExecuter.equals(that.departmentIdExecuter) : that.departmentIdExecuter != null)
            return false;
        if (dataFinish != null ? !dataFinish.equals(that.dataFinish) : that.dataFinish != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (statusExec != null ? !statusExec.equals(that.statusExec) : that.statusExec != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parentId != null ? parentId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (employeeIdTasker != null ? employeeIdTasker.hashCode() : 0);
        result = 31 * result + (taskProblemId != null ? taskProblemId.hashCode() : 0);
        result = 31 * result + (taskCommentId != null ? taskCommentId.hashCode() : 0);
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + (employeeIdExecuter != null ? employeeIdExecuter.hashCode() : 0);
        result = 31 * result + (departmentIdExecuter != null ? departmentIdExecuter.hashCode() : 0);
        result = 31 * result + (dataFinish != null ? dataFinish.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (statusExec != null ? statusExec.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public TaskEntity getTaskByParentId() {
        return taskByParentId;
    }

    public void setTaskByParentId(TaskEntity taskByParentId) {
        this.taskByParentId = taskByParentId;
    }

    @OneToMany(mappedBy = "taskByParentId")
    public Collection<TaskEntity> getTasksById() {
        return tasksById;
    }

    public void setTasksById(Collection<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id_tasker", referencedColumnName = "id", nullable = false)
    public EmployeeEntity getEmployeeByEmployeeIdTasker() {
        return employeeByEmployeeIdTasker;
    }

    public void setEmployeeByEmployeeIdTasker(EmployeeEntity employeeByEmployeeIdTasker) {
        this.employeeByEmployeeIdTasker = employeeByEmployeeIdTasker;
    }

    @ManyToOne
    @JoinColumn(name = "task_problem_id", referencedColumnName = "id", nullable = false)
    public TaskProblemEntity getTaskProblemByTaskProblemId() {
        return taskProblemByTaskProblemId;
    }

    public void setTaskProblemByTaskProblemId(TaskProblemEntity taskProblemByTaskProblemId) {
        this.taskProblemByTaskProblemId = taskProblemByTaskProblemId;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id_executer", referencedColumnName = "id")
    public EmployeeEntity getEmployeeByEmployeeIdExecuter() {
        return employeeByEmployeeIdExecuter;
    }

    public void setEmployeeByEmployeeIdExecuter(EmployeeEntity employeeByEmployeeIdExecuter) {
        this.employeeByEmployeeIdExecuter = employeeByEmployeeIdExecuter;
    }

    @ManyToOne
    @JoinColumn(name = "department_id_executer", referencedColumnName = "id")
    public DepartmentEntity getDepartmentByDepartmentIdExecuter() {
        return departmentByDepartmentIdExecuter;
    }

    public void setDepartmentByDepartmentIdExecuter(DepartmentEntity departmentByDepartmentIdExecuter) {
        this.departmentByDepartmentIdExecuter = departmentByDepartmentIdExecuter;
    }

    @OneToMany(mappedBy = "taskByTaskId")
    public Collection<TaskCommentsEntity> getTaskCommentsById() {
        return taskCommentsById;
    }

    public void setTaskCommentsById(Collection<TaskCommentsEntity> taskCommentsById) {
        this.taskCommentsById = taskCommentsById;
    }

    @OneToOne(mappedBy = "taskById")
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
    }
}
