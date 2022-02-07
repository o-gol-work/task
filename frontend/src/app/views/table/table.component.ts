import { Component, OnInit } from '@angular/core';
import {DataHandlerService} from "../../services/data-handler.service";
import {Task} from "../../models/Task";
import {TaskDecor} from "../../models/TaskDecor";
import {TaskService} from "../../services/task.service";
import {Employee} from "../../models/Employee";
import {TaskSearchValues} from "../../models/TaskSearchValues";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {HttpClient} from "@angular/common/http";
import {StompService} from "../../services/stomp.service";


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  // tasks: TaskDecor[] | undefined ;
  /*tasks: Task[] | undefined ;
   private employees:Employee[] | undefined;*/

  isLoggedIn! :  boolean | false;
  isDataLoading! : boolean | false;
  tasks: TaskDecor[]=[];

  constructor(
    // private dataHandler:DataHandlerService,
    // private http:HttpClient,
    private stompService:StompService,
    private tokenStorageService:TokenStorageService,
    private taskService:TaskService
  ) {
    // this.refreshTaskTable();
  }


  private taskSearchValues:TaskSearchValues={
  pageNumber:0,
  pageSize:30,
  sortColumn:"dateBegin",
  sortDirection:"desc"

  }

  ngOnInit(): void {

    this.isLoggedIn=!!this.tokenStorageService.getToken();
    this.stompService.subscribe('/topic/newTask',():any=>{
      this.refreshTaskTable()
    })
    // this.refreshTaskTable()
    /*this.taskService.getTaskByCurrent(this.taskSearchValues).subscribe({next:value => {
        // console.log(value)
        this.tasks=value.content;
        // console.log(this.tasks)
        this.isDataLoading=true;
      },error:err => {
      console.log(err.message)
      }})*/

    /*// this.tasks=this.dataHandler.getTaskDecFromTask();




    this.taskService.getTask().subscribe(data=> {
      console.log(data);
      this.tasks=data;
      /!*let empl=JSON.stringify(data);
      this.employees=empl;*!/
      console.log(this.tasks);
    });




    // this.taskService.getTask().subscribe(data=>this.employees=JSON.stringify(data));
    console.log(this.employees)*/
  }



  private refreshTaskTable():void{
    this.taskService.getTaskByCurrentSocket(this.taskSearchValues).subscribe({next:value => {
        // console.log(value)
        this.tasks=value.content;
        // console.log(this.tasks)
        this.isDataLoading=true;
      },error:err => {
        console.log(err.message)
      }})
  }

  /*isNameCheck(task:Task): boolean {
    if ( task.employeeExecuter!= null) {
      return false;
    }
    return true;
  }*/

}
