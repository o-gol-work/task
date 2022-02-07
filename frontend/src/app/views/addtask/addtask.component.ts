import { Component, OnInit } from '@angular/core';
import {TaskService} from "../../services/task.service";
import {NotificationService} from "../../services/notification.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SignupRequest} from "../../models/SignupRequest";
import {TaskDto} from "../../models/TaskDto";

@Component({
  selector: 'app-addtask',
  templateUrl: './addtask.component.html',
  styleUrls: ['./addtask.component.css']
})
export class AddtaskComponent implements OnInit {

  addTaskForm!:FormGroup;

  constructor(private taskService:TaskService,
  private notification:NotificationService,
  private router:Router,
  private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.addTaskForm=this.createAddTaskForm();
  }

  createAddTaskForm():FormGroup{
    return this.formBuilder.group({
      parentId:[''],
      idProblem:['',Validators.compose([Validators.required,])],
    })
  }


  submit():void {
    this.taskService.createNewTask(new TaskDto(
      this.addTaskForm.value.parentId,
      this.addTaskForm.value.idProblem

    )).
    subscribe({
        next: data => {

          this.notification.showSnackbar(data.dateBegin);
          console.log(data.message)
          // this.router.navigate(["main"]);
          this.addTaskForm.reset();
          // window.location.reload();

        },
        error: err => {
          this.notification.showSnackbar(err.message);


        }
      }
    )


  }



}
