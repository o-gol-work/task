import {Employee} from "../models/Employee";
import {Department} from "../models/Department";
import {TaskProblem} from "../models/TaskProblem";
import {Task} from "../models/Task";
import {Post} from "../models/Post";

export class TestData{

  static departments:Department[]=[
    {parentId:null,id:1,title:'ОАО"ТАЗ"'},
    {parentId:1,id:2,title:'УИТ'},
    {parentId:1,id:3,title:'УГТ'},
    {parentId:1,id:4,title:'УГК'},
    {parentId:2,id:5,title:'Отдел програмного обеспечения'}
    ,{parentId:2,id:6,title:'Отдел ремонта технического оборудования'}
    ,{parentId:3,id:7,title:'ОУГТ1'}
    ,{parentId:3,id:8,title:'ОУГТ2'},
    {parentId:3,id:9,title:'ОУГТ3'},
    {parentId:4,id:10,title:'ОУГК1',telephoneNumber:'5566'},
    {parentId:4,id:11,title:'ОУГК2',telephoneNumber:'7766'},
    {parentId:5,id:12,title:'Бюро програмного обеспечения'},
    {parentId:5,id:13,title:'Бюро работы с сетью'},
    {parentId:6,id:14,title:'Бюро ремонта компютеров'},
    {parentId:8,id:16,title:'БОУГТ2'},
    {parentId:10,id:17,title:'БОУГК1'},
    {parentId:7,id:15,title:'БОУГТ1'},
    {parentId:10,id:18,title:'БОУГК2'},
    {parentId:11,id:19,title:'БОУГК3'},
    {parentId:1,id:20,title:'ЦМС'},
    {parentId:1,id:21,title:'ЦСС'},
    {parentId:20,id:22,title:'Служба энергЦМС'},
    {parentId:21,id:23,title:'Служба энергЦСС'},
    {parentId:20,id:24,title:'Управление Технолога ЦМС'},
    {parentId:24,id:25,title:'Бюро технолога ЦМС'},
    {parentId:6,id:26,title:'Бюро ремонта принтеров'},
    {parentId:6,id:27,title:'Группа IT обслуживания ЦМС'},
    {parentId:1,id:28,title:'ОСОЦР'},
    {parentId:28,id:29,title:'Служба энерг ОСОЦР'}
  ];


  static posts: Post[]=[
    {id:1,title:'Ген. Директор'},
    {id:2,title:'Нач. Управления',parentId:1},
    {id:3,title:'Нач. Отдела',parentId:2},
    {id:4,title:'Нач. Бюро',parentId:3},
    {id:5,title:'Нач. Цеха',parentId:1},
    {id:6,title:'Энергетик Цеха',parentId:5},
    {id:7,title:'Электрик',parentId:6},
    {id:8,title:'Инженер',parentId:4},
    {id:9,title:'Электроник',parentId:4},

  ];


  static taskProblem: TaskProblem[]=[
{parentId:null,id:1,title:'Неисправна компьютерная техника\\принтер',parentNumber:1,level:1},
{parentId:1,id:2,title:'Неисправен компьютер',parentNumber:1,level:2},
{parentId:1,id:3,title:'Неисправен принтер',parentNumber:1,level:2},
{parentId:2,id:4,title:'Не включается компьютер',parentNumber:1,level:3},
{parentId:2,id:5,title:'Тормозит компьютер',parentNumber:1,level:3},
{parentId:2,id:6,title:'Не работает программа',parentNumber:1,level:3},
{parentId:2,id:7,title:'Не работает сеть',parentNumber:1,level:3},
{parentId:2,id:8,title:'Другое',parentNumber:1,level:3},
{parentId:3,id:9,title:'Не печатоет',parentNumber:1,level:3},
{parentId:3,id:10,title:'Шумит',parentNumber:1,level:3},
{parentId:3,id:11,title:'Закончился картредж',parentNumber:1,level:3},
{parentId:null,id:12,title:'Электрика',parentNumber:2,level:1},
{parentId:12,id:13,title:'Недостаточное освещение',parentNumber:2,level:2},
{parentId:12,id:14,title:'Нет питания в помещении',parentNumber:2,level:2}
  ];


  static menuItems: string[]=[
    'Мои задачи на выполнение',
    'Мои задачи выполнить'
  ]






  static employees: Employee[]=[
    {id:1,tabelNumber:1111,name:'Олег',surname:'Олегович',telephoneNumber:'Олегов',worked:0,post:TestData.posts[0],department:TestData.departments[0]},
{id:2,tabelNumber:2222,name:'Игорь',surname:'Игоревич',telephoneNumber:'Игорев',worked:0,post:TestData.posts[4],department:TestData.departments[19]},
{id:3,tabelNumber:3333,name:'Виктор',surname:'Викторович',telephoneNumber:'Викторов',worked:0,post:TestData.posts[5],department:TestData.departments[21]},
{id:4,tabelNumber:4444,name:'Сергей',surname:'Сергеевич',telephoneNumber:'Сергеев',worked:0,post:TestData.posts[6],department:TestData.departments[21]},
{id:5,tabelNumber:5555,name:'Юрий',surname:'Юрьевич',telephoneNumber:'Юрьев',worked:0,post:TestData.posts[1],department:TestData.departments[1]},
{id:6,tabelNumber:6666,name:'Анна',surname:'Анновна',telephoneNumber:'Аннова',worked:0,post:TestData.posts[2],department:TestData.departments[5]},
{id:7,tabelNumber:7777,name:'Ольга',surname:'Ольговна',telephoneNumber:'Ольгова',worked:0,post:TestData.posts[3],department:TestData.departments[11]},
{id:8,tabelNumber:8888,name:'Ким',surname:'Кимович',telephoneNumber:'Кимов',worked:0,post:TestData.posts[7],department:TestData.departments[11]},
{id:9,tabelNumber:9999,name:'Кус',surname:'Кусович',telephoneNumber:'Кусов',worked:0,post:TestData.posts[5],department:TestData.departments[22]},
{id:10,tabelNumber:1010,name:'Мус',surname:'Мусович',telephoneNumber:'Мусов',worked:0,post:TestData.posts[6],department:TestData.departments[22]},
{id:11,tabelNumber:101,name:'Зус',surname:'Зусович',telephoneNumber:'Зусов',worked:0,post:TestData.posts[1],department:TestData.departments[23]},
{id:12,tabelNumber:1212,name:'Гурус',surname:'Гурусович',telephoneNumber:'Гурусов',worked:0,post:TestData.posts[7],department:TestData.departments[24]},
{id:13,tabelNumber:1313,name:'Жор',surname:'Жорович',telephoneNumber:'Жоров',worked:0,post:TestData.posts[8],department:TestData.departments[28]},
{id:14,tabelNumber:1414,name:'Гор ',surname:'Горов ',telephoneNumber:'Горович',worked:0,post:TestData.posts[7],department:TestData.departments[26]},
{id:15,tabelNumber:1515,name:'Жих',surname:' Хор',telephoneNumber:' Карович',worked:0,post:TestData.posts[2],department:TestData.departments[6]}
];

  static tasks: Task[]=[
{parentId:null,id:275,employeeTasker:TestData.employees[10],taskProblem:TestData.taskProblem[3],dateBegin:new Date('2021-08-12 12:04:49'),employeeExecuter:TestData.employees[7],departmentExecuter:TestData.departments[12],dataFinish:new Date('2021-09-16 14:51:39'),status:1,statusExec:1},
{parentId:null,id:276,employeeTasker:TestData.employees[4],taskProblem:TestData.taskProblem[5],dateBegin:new Date('2021-08-12 12:04:50'),employeeExecuter:null,departmentExecuter:TestData.departments[12],dataFinish:null,status:0,statusExec:0},
{parentId:null,id:277,employeeTasker:TestData.employees[14],taskProblem:TestData.taskProblem[4],dateBegin:new Date('2021-08-12 12:04:52'),employeeExecuter:null,departmentExecuter:TestData.departments[12],dataFinish:null,status:0,statusExec:0},
{parentId:null,id:278,employeeTasker:TestData.employees[2],taskProblem:TestData.taskProblem[4],dateBegin:new Date('2021-08-12 12:04:58'),employeeExecuter:null,departmentExecuter:TestData.departments[27],dataFinish:null,status:0,statusExec:0},
{parentId:null,id:279,employeeTasker:TestData.employees[5],taskProblem:TestData.taskProblem[13],dateBegin:new Date('2021-08-12 12:04:59'),employeeExecuter:null,departmentExecuter:TestData.departments[28],dataFinish:null,status:0,statusExec:0},
{parentId:275,id:280,employeeTasker:TestData.employees[4],taskProblem:TestData.taskProblem[5],dateBegin:new Date('2021-08-12 12:05:16'),employeeExecuter:null,departmentExecuter:TestData.departments[12],dataFinish:null,status:0,statusExec:0},
{parentId:null,id:290,employeeTasker:TestData.employees[4],taskProblem:TestData.taskProblem[5],dateBegin:new Date('2021-11-03 12:06:23'),employeeExecuter:null,departmentExecuter:TestData.departments[12],dataFinish:null,status:0,statusExec:0},
{parentId:null,id:291,employeeTasker:TestData.employees[4],taskProblem:TestData.taskProblem[5],dateBegin:new Date('2021-11-03 12:08:01'),employeeExecuter:null,departmentExecuter:TestData.departments[12],dataFinish:null,status:0,statusExec:0},
{parentId:275,id:292,employeeTasker:TestData.employees[4],taskProblem:TestData.taskProblem[5],dateBegin:new Date('2021-11-03 12:08:08'),employeeExecuter:null,departmentExecuter:TestData.departments[12],dataFinish:null,status:0,statusExec:0},
{parentId:280,id:293,employeeTasker:TestData.employees[10],taskProblem:TestData.taskProblem[3],dateBegin:new Date('2021-11-03 12:12:21'),employeeExecuter:null,departmentExecuter:TestData.departments[27],dataFinish:null,status:0,statusExec:0},
{parentId:null,id:294,employeeTasker:TestData.employees[2],taskProblem:TestData.taskProblem[4],dateBegin:new Date('2021-12-01 14:17:36'),employeeExecuter:null,departmentExecuter:TestData.departments[27],dataFinish:null,status:0,statusExec:0},
{parentId:null,id:295,employeeTasker:TestData.employees[5],taskProblem:TestData.taskProblem[13],dateBegin:new Date('2021-12-02 14:11:24'),employeeExecuter:null,departmentExecuter:TestData.departments[28],dataFinish:null,status:0,statusExec:0},
{parentId:295,id:296,employeeTasker:TestData.employees[5],taskProblem:TestData.taskProblem[13],dateBegin:new Date('2021-12-02 14:11:59'),employeeExecuter:null,departmentExecuter:TestData.departments[28],dataFinish:null,status:0,statusExec:0},
  ];



}
