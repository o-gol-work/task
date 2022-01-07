import { Component, OnInit } from '@angular/core';
import {DataHandlerService} from "../../services/data-handler.service";
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menuItems: string[] | undefined ;

  constructor(private dataHandler:DataHandlerService) { }

  ngOnInit(): void {
    this.menuItems=this.dataHandler.getMenuItems();
    console.log(this.menuItems)
  }

}
