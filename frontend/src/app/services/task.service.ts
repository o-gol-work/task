import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TaskSearchValues} from "../models/TaskSearchValues";
import {TaskDto} from "../models/TaskDto";



const TASK_API='http://localhost:8080/tasks/';

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
    // return this.httpClient.post(TASK_API+'search',taskSearchValues)
    return this.httpClient.post(TASK_API+'searchMyTask',taskSearchValues)
  }

  public getTaskByCurrentSocket(taskSearchValues:TaskSearchValues):Observable<any>{
    // return this.httpClient.post(TASK_API+'search',taskSearchValues)
    return this.httpClient.post(TASK_API+'createSocket',taskSearchValues)
  }

  public getTaskByCurrentDep(taskSearchValues:TaskSearchValues):Observable<any>{
    // return this.httpClient.post(TASK_API+'search',taskSearchValues)
    return this.httpClient.post(TASK_API+'searcTaskForMyDep',taskSearchValues)
  }


  public createNewTask(taskDto:TaskDto):Observable<any>{
    return this.httpClient.post(TASK_API+'create',taskDto)
  }




}
