import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AddDeviceComponent, AdminProfileComponent, ViewDevicesComponent, ViewUsersComponent} from "./containers";
import {RegisterComponent} from "../home/containers";

const routes: Routes = [
  {
    path: '',
    component: AdminProfileComponent,
    children: [
      {path: 'view-users', component: ViewUsersComponent},
      {path: 'add-user', component: RegisterComponent},
      {path: 'add-device', component: AddDeviceComponent},
      {path: 'view-devices', component: ViewDevicesComponent}
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
