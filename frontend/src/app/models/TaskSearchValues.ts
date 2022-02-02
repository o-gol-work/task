


export class TaskSearchValues{

  //Parameter of search
  employeeIdTasker?:number;
  taskProblemId?:number;
  dateBegin?:Date;
  employeeIdExecuter?:number;
  departmentIdExecuter?:number;
  dataFinish?:Date;
  status?:number;
  //pageable
  pageNumber?:number;
  pageSize?:number;
  //sorting
  sortColumn?:string;
  sortDirection?:string;


  constructor(employeeIdTasker: number, taskProblemId: number, dateBegin: Date, employeeIdExecuter: number, departmentIdExecuter: number, dataFinish: Date, status: number, pageNumber: number, pageSize: number, sortColumn: string, sortDirection: string) {
    this.employeeIdTasker = employeeIdTasker;
    this.taskProblemId = taskProblemId;
    this.dateBegin = dateBegin;
    this.employeeIdExecuter = employeeIdExecuter;
    this.departmentIdExecuter = departmentIdExecuter;
    this.dataFinish = dataFinish;
    this.status = status;
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.sortColumn = sortColumn;
    this.sortDirection = sortDirection;
  }
}
