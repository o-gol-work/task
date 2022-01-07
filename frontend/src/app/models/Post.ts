export class Post {
  parentId?: number;
  id: number;
  title: string;


  constructor( id: number, title: string,parentId?: number) {
    this.parentId = parentId;
    this.id = id;
    this.title = title;
  }
}
