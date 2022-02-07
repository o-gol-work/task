export class TaskDto{
  parentId:number;
  idProblem:number;


  constructor(parentId: number, idProblem: number) {
    this.parentId = parentId;
    this.idProblem = idProblem;
  }
}
