export class TaskProblem{
  parentId?: number|null;
  id: number;
  title: string;
  parentNumber: number;
  level: number;


  constructor( id: number, title: string, parentNumber: number, level: number,parentId?: number) {
    this.parentId = parentId;
    this.id = id;
    this.title = title;
    this.parentNumber = parentNumber;
    this.level = level;
  }
}
