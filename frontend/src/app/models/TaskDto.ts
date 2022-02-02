export class TaskDto{
  parentId:number;
  taskProblemId:number;


  constructor(parentId: number, taskProblemId: number) {
    this.parentId = parentId;
    this.taskProblemId = taskProblemId;
  }
}
