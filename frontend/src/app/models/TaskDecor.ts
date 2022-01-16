import {Employee} from "./Employee";
import {TaskProblem} from "./TaskProblem";
import {Department} from "./Department";

export class TaskDecor{
  parentId?: number|null;
  id:number;
  dateBegin: string;
  dataFinish?: string | null;
  status:number;
  statusExec: number;
  employeeTasker: string;
  taskProblem:string;
  employeeExecuter?:string|null;
  departmentExecuter:string;


  constructor(parentId: number | null | undefined,
              id: number,
              dateBegin: string,
              dataFinish: string | undefined,
              status: number,
              statusExec: number,
              employeeTasker: string,
              taskProblem: string,
              employeeExecuter: string | undefined,
              departmentExecuter: string) {
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
