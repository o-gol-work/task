package ru.olejkai.task_vsr.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "task", schema = "task_vsr")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "parent", "children"
        ,"employeeIdTasker"
        ,"taskProblemId"
        ,"employeeIdExecuter"
        ,"departmentIdExecuter"

})
public class TaskEntity implements Serializable {
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
    //    private Integer statusExec;
    private EmployeeEntity employeeByEmployeeIdTasker;
    private TaskProblemEntity taskProblemByTaskProblemId;
    private EmployeeEntity employeeByEmployeeIdExecuter;
    private DepartmentEntity departmentByDepartmentIdExecuter;
    @Transient
    private TaskEntity parent;
    @Transient
    private Collection<TaskEntity> children;

    public TaskEntity(
//            long id, Date dataFinish, Date dateBegin, long parentId, int status
//            , long idDepartment, String titleDepartment
//            , long idEmployeeExec, String nameExec, String surnameExec, int tabelNumberExec
//            , long idEmployeeTasker, String nameTasker, String surnameTasker, int tabelNumberTasker
//            , long idProblem, String titelProblem)
            Long id, Date dataFinish, Date dateBegin, Long parentId, Integer status
            , Long idDepartment, String titleDepartment
            , Long idEmployeeExec, String nameExec, String surnameExec, Integer tabelNumberExec
            , Long idEmployeeTasker, String nameTasker, String surnameTasker, Integer tabelNumberTasker
            , Long idProblem, String titelProblem) {
        if (parentId != null)
            this.parentId = parentId;
        this.id = id;
        this.dateBegin = new Timestamp(dateBegin.getTime());
        if (dataFinish != null)
            this.dataFinish = new Timestamp(dataFinish.getTime());
        this.status = status;
        this.departmentByDepartmentIdExecuter = new DepartmentEntity(idDepartment, titleDepartment);
        if (idEmployeeExec != null && tabelNumberExec!=null && nameExec!=null && surnameExec!=null)
            this.employeeByEmployeeIdExecuter = new EmployeeEntity(idEmployeeExec, tabelNumberExec, nameExec, surnameExec);
        this.employeeByEmployeeIdTasker = new EmployeeEntity(idEmployeeTasker, tabelNumberTasker, nameTasker, surnameTasker);
        this.taskProblemByTaskProblemId = new TaskProblemEntity(idProblem, titelProblem);
    }



    public TaskEntity(
            Long id, Date dataFinish, Date dateBegin, Long parentId, Integer status

            ,Long parentIdDepartment, Long idDepartment, String titleDepartment, String telephoneNumberDepartment

            , Long idEmployeeExec, String nameExec, String surnameExec, Integer tabelNumberExec, String telephoneNumberExec, Byte workedExec
            ,Long idHasPostDepExec
                    ,Long idPostExec,String titlePostExec,Long parentIdPostExec
                    ,Long parentIdDepartmentExec, Long idDepartmentExec, String titleDepartmentExec, String telephoneNumberDepartmentExec

            , Long idEmployeeTasker, String nameTasker, String surnameTasker, Integer tabelNumberTasker, String telephoneNumberTasker, Byte workedExecTasker
            ,Long idHasPostDepTask
                    ,Long idPostTasker,String titlePosTasker,Long parentIdTasker
                    ,Long parentIdDepartmentTasker, Long idDepartmentTasker, String titleDepartmentTasker, String telephoneNumberDepartmentTasker

            , Long idProblem, String titelProblem) {

        if (parentId != null)
            this.parentId = parentId;
        this.id = id;
        this.dateBegin = new Timestamp(dateBegin.getTime());
        if (dataFinish != null)
            this.dataFinish = new Timestamp(dataFinish.getTime());
        this.status = status;
        this.departmentByDepartmentIdExecuter = new DepartmentEntity(parentIdDepartment,idDepartment, titleDepartment,telephoneNumberDepartment);
        if (idEmployeeExec != null && tabelNumberExec!=null && nameExec!=null && surnameExec!=null) {
            this.employeeByEmployeeIdExecuter = new EmployeeEntity(idEmployeeExec, tabelNumberExec, nameExec, surnameExec, telephoneNumberExec, workedExec
                    ,idHasPostDepExec
                    , idPostExec, titlePostExec, parentIdPostExec
                    , parentIdDepartmentExec, idDepartmentExec, titleDepartmentExec, telephoneNumberDepartmentExec

            );
        }
        this.employeeByEmployeeIdTasker = new EmployeeEntity(idEmployeeTasker, tabelNumberTasker, nameTasker, surnameTasker,telephoneNumberTasker,workedExecTasker
                ,idHasPostDepTask
                , idPostTasker, titlePosTasker, parentIdTasker
                , parentIdDepartmentTasker,  idDepartmentTasker,  titleDepartmentTasker,  telephoneNumberDepartmentTasker

        );
        this.taskProblemByTaskProblemId = new TaskProblemEntity(idProblem, titelProblem);
    }
















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


    /*@Basic
    @Column(name = "status_exec", nullable = true)
    public Integer getStatusExec() {
        return statusExec;
    }*/


    /*@ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id",insertable = false, updatable = false)
    public TaskEntity getTaskByParentId() {
        return taskByParentId;
    }

    public void setTaskByParentId(TaskEntity taskByParentId) {
        this.taskByParentId = taskByParentId;
    }*/


    @ManyToOne
    @JoinColumn(name = "employee_id_tasker", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public EmployeeEntity getEmployeeByEmployeeIdTasker() {
        return employeeByEmployeeIdTasker;
    }

    /*public void setEmployeeByEmployeeIdTasker(EmployeeEntity employeeByEmployeeIdTasker) {
        this.employeeByEmployeeIdTasker = employeeByEmployeeIdTasker;
    }*/

    @ManyToOne
    @JoinColumn(name = "task_problem_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public TaskProblemEntity getTaskProblemByTaskProblemId() {
        return taskProblemByTaskProblemId;
    }

    /*public void setTaskProblemByTaskProblemId(TaskProblemEntity taskProblemByTaskProblemId) {
        this.taskProblemByTaskProblemId = taskProblemByTaskProblemId;
    }*/

    @ManyToOne
    @JoinColumn(name = "employee_id_executer", referencedColumnName = "id", insertable = false, updatable = false)
    public EmployeeEntity getEmployeeByEmployeeIdExecuter() {
        return employeeByEmployeeIdExecuter;
    }

    /*public void setEmployeeByEmployeeIdExecuter(EmployeeEntity employeeByEmployeeIdExecuter) {
        this.employeeByEmployeeIdExecuter = employeeByEmployeeIdExecuter;
    }*/

    @ManyToOne
    @JoinColumn(name = "department_id_executer", referencedColumnName = "id", insertable = false, updatable = false)
    public DepartmentEntity getDepartmentByDepartmentIdExecuter() {
        return departmentByDepartmentIdExecuter;
    }

    /*public void setDepartmentByDepartmentIdExecuter(DepartmentEntity departmentByDepartmentIdExecuter) {
        this.departmentByDepartmentIdExecuter = departmentByDepartmentIdExecuter;
    }*/


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_task_task_parent"), insertable = false, updatable = false)
    public TaskEntity getParent() {
        return parent;
    }


    @OneToMany(fetch = FetchType.LAZY
            , cascade = CascadeType.ALL
    )
    @JoinColumn(name = "parent_id")
    public Collection<TaskEntity> getChildren() {
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
