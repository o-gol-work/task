/**
"parentId": null,
  "id": 275,
  "dateBegin": "2021-08-12T12:04:49.000+00:00",
  "dataFinish": "2021-09-16T14:51:39.000+00:00",
  "status": 1,
  "statusExec": null,
  "employeeByEmployeeIdTasker": {
  "id": 11,
    "tabelNumber": 101,
    "name": "Зус",
    "surname": "Зусович",
    "telephoneNumber": "Зусов",
    "worked": 0,
    "postHasDepartmentByPostHasDepartmentId": {
    "id": 42,
      "postByPostId": {
      "id": 2,
        "title": "Нач. Управления",
        "parentId": 1
    },
    "departmentByDepartmentId": {
      "parentId": 20,
        "id": 24,
        "title": "Управление Технолога ЦМС",
        "telephoneNumber": null
    }
  }
},
"taskProblemByTaskProblemId": {
  "parentId": null,
    "id": 4,
    "title": "Не включается компьютер",
    "parentNumber": 1,
    "level": 3
},
"employeeByEmployeeIdExecuter": {
  "id": 8,
    "tabelNumber": 8888,
    "name": "Ким",
    "surname": "Кимович",
    "telephoneNumber": "Кимов",
    "worked": 0,
    "postHasDepartmentByPostHasDepartmentId": {
    "id": 26,
      "postByPostId": {
      "id": 8,
        "title": "Инженер",
        "parentId": 4
    },
    "departmentByDepartmentId": {
      "parentId": 5,
        "id": 12,
        "title": "Бюро програмного обеспечения",
        "telephoneNumber": null
    }
  }
},
"departmentByDepartmentIdExecuter": {
  "parentId": 5,
    "id": 12,
    "title": "Бюро програмного обеспечения",
    "telephoneNumber": null
}*/
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





