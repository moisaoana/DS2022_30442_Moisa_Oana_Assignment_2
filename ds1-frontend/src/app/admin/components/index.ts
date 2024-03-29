import {ViewUserComponent} from "./view-user/view-user.component";
import {UserListComponent} from "./user-list/user-list.component";
import {AddDeviceComponent} from "../containers";
import {DeviceListComponent} from "./device-list/device-list.component";
import {ViewDeviceComponent} from "./view-device/view-device.component";

export const containers = [
  ViewUserComponent,
  UserListComponent,
  AddDeviceComponent,
  DeviceListComponent,
  ViewDeviceComponent
];

export * from './view-user/view-user.component';
export * from './user-list/user-list.component';
export * from './add-device-form/add-device-form.component';
export * from './device-list/device-list.component';
export * from './view-device/view-device.component'

