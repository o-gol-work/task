import { Injectable } from '@angular/core';
import {TestData} from "../data/TestData";

@Injectable({
  providedIn: 'root'
})
export class DataHandlerService {

  constructor() { }

  getMenuItems(): string[]{
    return TestData.menuItems
  }
}
