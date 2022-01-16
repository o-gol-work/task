export class Department{

    parentId? : number | null;
    id: number;
    title: string;
    telephoneNumber?: string|null;


  constructor( id: number, title: string, telephoneNumber?: string,parentId?: number) {
    this.parentId = parentId;
    this.id = id;
    this.title = title;
    this.telephoneNumber = telephoneNumber;
  }
}
