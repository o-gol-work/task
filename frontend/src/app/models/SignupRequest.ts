export class SignupRequest{
  tabelNumber:string;
  name:string;
  surname:string;
  telephoneNumber:string;
  postHasDepartmentId:string;
  password:string;
  confirmPassword:string;


  constructor(tabelNumber: string, name: string, surname: string, telephoneNumber: string, postHasDepartmentId: string, password: string, confirmPassword: string) {
    this.tabelNumber = tabelNumber;
    this.name = name;
    this.surname = surname;
    this.telephoneNumber = telephoneNumber;
    this.postHasDepartmentId = postHasDepartmentId;
    this.password = password;
    this.confirmPassword = confirmPassword;
  }
}

