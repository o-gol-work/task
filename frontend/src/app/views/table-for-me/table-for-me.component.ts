import { Component, OnInit } from '@angular/core';
import {TaskDecor} from "../../models/TaskDecor";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {TaskService} from "../../services/task.service";
import {TaskSearchValues} from "../../models/TaskSearchValues";

@Component({
  selector: 'app-table-for-me',
  templateUrl: './table-for-me.component.html',
  styleUrls: ['./table-for-me.component.css']
})
export class TableForMeComponent implements OnInit {

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
    this.taskService.getTaskByCurrentDep(this.taskSearchValues).subscribe({next:value => {
        // console.log(value)
        this.tasks=value.content;
        // console.log(this.tasks)
        this.isDataLoading=true;
      },error:err => {
        console.log(err.message)
      }})
  }

}
