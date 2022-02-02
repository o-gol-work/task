import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TaskSearchValues} from "../models/TaskSearchValues";
import {TaskDto} from "../models/TaskDto";



// const TASK_API='http://localhost:8080/start/employees/all_employees';
// const TASK_API='https://jsonplaceholder.typicode.com/posts';
const TASK_API='http://localhost:8080/tasks/';
// const TASK_API='http://localhost:8080/start/tasks/task/275';

@Injectable({
  providedIn: 'root'
})

export class TaskService {

  constructor(private httpClient: HttpClient) { }

  public getTask(id?:number):Observable<any>{

    // console.log(tasks);
    return this.httpClient.get(TASK_API+'all_tasks');
  }

  public getTaskByCurrent(taskSearchValues:TaskSearchValues):Observable<any>{
    return this.httpClient.post(TASK_API+'search',taskSearchValues)
  }

  public createNewTask(taskDto:TaskDto):Observable<any>{
    return this.httpClient.post(TASK_API+'create',taskDto)
  }




}
