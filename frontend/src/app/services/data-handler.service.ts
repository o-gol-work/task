import { Injectable } from '@angular/core';
import {TestData} from "../data/TestData";
import {Task} from "../models/Task";
import {TaskDecor} from "../models/TaskDecor";

@Injectable({
  providedIn: 'root'
})
export class DataHandlerService {

  private taskDecors : TaskDecor[] =[];



  constructor() { }

  getMenuItems(): string[]{
    return TestData.menuItems
  }

  getTaskItems(): Task[]{
    return TestData.tasks;
  }

  // constructor(parentId: number | null,
  // id: number,
  // dateBegin: string,
  // dataFinish: string | null,
  // status: number,
  // statusExec: number,
  // employeeTasker: string,
  // taskProblem: string,
  // employeeExecuter: string | null,
  // departmentExecuter: string)

  getTaskDecFromTask(): TaskDecor[] {
        for (const task of TestData.tasks) {
      this.taskDecors.push(new TaskDecor(
        task.parentId,
        task.id,
        task.dateBegin.toLocaleString(),
        task.dataFinish?.toLocaleString(),
        task.status,
        task.statusExec,
        task.employeeTasker.name,
        task.taskProblem.title,
        task.employeeExecuter?.name,
        task.departmentExecuter.title
        ))
    }
    return this.taskDecors;
  }
}
