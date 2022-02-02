import { Component, OnInit } from '@angular/core';
import {DataHandlerService} from "../../services/data-handler.service";
import {Task} from "../../models/Task";
import {TaskDecor} from "../../models/TaskDecor";
import {TaskService} from "../../services/task.service";
import {Employee} from "../../models/Employee";
import {TaskSearchValues} from "../../models/TaskSearchValues";
import {TokenStorageService} from "../../services/auth/token-storage.service";


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

    private tokenStorageService:TokenStorageService,
    private taskService:TaskService
  ) { }


  private taskSearchValues:TaskSearchValues={
  pageNumber:0,
  pageSize:10,
  sortColumn:"dateBegin",
  sortDirection:"desc"

  }

  ngOnInit(): void {
    this.isLoggedIn=!!this.tokenStorageService.getToken();
    this.taskService.getTaskByCurrent(this.taskSearchValues).subscribe({next:value => {
        // console.log(value)
        this.tasks=value.content;
        // console.log(this.tasks)
        this.isDataLoading=true;
      },error:err => {
      console.log(err.message)
      }})

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

  /*isNameCheck(task:Task): boolean {
    if ( task.employeeExecuter!= null) {
      return false;
    }
    return true;
  }*/

}
