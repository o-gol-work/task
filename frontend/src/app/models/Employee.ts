import {Department} from "./Department";
import {Post} from "./Post";

export class Employee{

  id: number;
  tabelNumber: number;
  name: string;
  surname:string;
  telephoneNumber?:string;
  worked: number;
  post: Post;
  department: Department;


  constructor(id: number, tabelNumber: number, name: string, surname: string,  worked: number, post: Post, department: Department,telephoneNumber?: string) {
    this.id = id;
    this.tabelNumber = tabelNumber;
    this.name = name;
    this.surname = surname;
    this.telephoneNumber = telephoneNumber;
    this.worked = worked;
    this.post = post;
    this.department = department;
  }
}





