export class EmployeeFromStorage{


  id:number;
  tabelNumber:number;
  name:string;
  surname:string;
  telephoneNumber:string;
  worked:number;
  postHasDepartmentByPostHasDepartmentId:number;
  employeeRolesById:[];
  authorities:[]


  constructor(id: number, tabelNumber: number, name: string, surname: string, telephoneNumber: string, worked: number, postHasDepartmentByPostHasDepartmentId: number, employeeRolesById: [], authorities: []) {
    this.id = id;
    this.tabelNumber = tabelNumber;
    this.name = name;
    this.surname = surname;
    this.telephoneNumber = telephoneNumber;
    this.worked = worked;
    this.postHasDepartmentByPostHasDepartmentId = postHasDepartmentByPostHasDepartmentId;
    this.employeeRolesById = employeeRolesById;
    this.authorities = authorities;
  }
}
