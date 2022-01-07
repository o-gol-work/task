import { Component, OnInit } from '@angular/core';
import {DataHandlerService} from "../../services/data-handler.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  menuItems: string[] | undefined ;

  constructor(private dataHandler:DataHandlerService) { }

  ngOnInit(): void {
    this.menuItems=this.dataHandler.getMenuItems();
    console.log(this.menuItems)
  }

}
