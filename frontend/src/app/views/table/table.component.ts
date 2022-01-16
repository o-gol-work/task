import { Component, OnInit } from '@angular/core';
import {DataHandlerService} from "../../services/data-handler.service";
import {Task} from "../../models/Task";
import {TaskDecor} from "../../models/TaskDecor";
import {TaskService} from "../../services/task.service";
import {Employee} from "../../models/Employee";


@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  // tasks: TaskDecor[] | undefined ;
  tasks: Task[] | undefined ;
   private employees:Employee[] | undefined;


  constructor(private dataHandler:DataHandlerService,
              private taskService:TaskService) { }

  ngOnInit(): void {
    // this.tasks=this.dataHandler.getTaskDecFromTask();
    this.taskService.getTask().subscribe(data=> {
      console.log(data);
      this.tasks=data;
      /*let empl=JSON.stringify(data);
      this.employees=empl;*/
      console.log(this.tasks);
    });
    // this.taskService.getTask().subscribe(data=>this.employees=JSON.stringify(data));
    console.log(this.employees)
  }

  isNameCheck(task:Task): boolean {
    if ( task.employeeExecuter!= null) {
      return false;
    }
    return true;
  }

}
