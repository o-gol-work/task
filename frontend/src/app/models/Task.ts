import {Employee} from "./Employee";
import {Department} from "./Department";
import {TaskProblem} from "./TaskProblem";

export class Task{
  parentId?: number|null;
  id:number;
  dateBegin: Date;
  dataFinish?: Date|null;
  status:number;
  statusExec: number;
  employeeTasker: Employee;
  taskProblem:TaskProblem;
  employeeExecuter?:Employee|null;
  departmentExecuter:Department;

  constructor( id: number, dateBegin: Date,  status: number, statusExec: number, employeeTasker: Employee, taskProblem: TaskProblem, departmentExecuter: Department
               ,parentId?: number,dataFinish?: Date, employeeExecuter?: Employee) {
    this.parentId = parentId;
    this.id = id;
    this.dateBegin = dateBegin;
    this.dataFinish = dataFinish;
    this.status = status;
    this.statusExec = statusExec;
    this.employeeTasker = employeeTasker;
    this.taskProblem = taskProblem;
    this.employeeExecuter = employeeExecuter;
    this.departmentExecuter = departmentExecuter;
  }
}
