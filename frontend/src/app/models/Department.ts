export class Department {

    parentId?: number ;
    id: number;
    title: string;
    telephoneNumber?: string;


  constructor( id: number, title: string, telephoneNumber?: string, parentId?: number) {
    this.parentId = parentId;
    this.id = id;
    this.title = title;
    this.telephoneNumber = telephoneNumber;
  }
}
