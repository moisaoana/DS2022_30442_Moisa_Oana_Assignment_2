import { Component, OnInit } from '@angular/core';
import {Device, User} from "../../../home/types/classes";
import {DeviceService, TokenStorageService, WebsocketService} from "../../../home/services";
import {Observable} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {Warning} from "../../types";

@Component({
  selector: 'app-user-profile',
  template:`
    <div class="background full-height paddings">
      <button mat-raised-button
              color="accent"
              (click)="onLogout()"
              class="logout"
      >Logout</button>
      <h1 class="title">Welcome, {{user.name}}!</h1>

      <div fxLayout="column" fxLayoutGap="40px">
        <app-user-devices-list
            *ngIf="devices$ | async as devices"
            [devices]="devices"
          ></app-user-devices-list>

        <h3 class="warn-color">Warnings:</h3>
        <div fxLayout="column" fxLayoutGap="24px">
          <app-view-warning
            *ngFor="let warning of warnings"
            [warning]="warning"
          ></app-view-warning>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  user: User;
  devices$: Observable<Device[]>;
  warnings: Warning[] = [];

  constructor(private tokenStorageService: TokenStorageService,
              private devicesService: DeviceService,
              private router: Router,
              private route: ActivatedRoute,
              private webSocketService: WebsocketService
              ) { }

  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser()
    if(this.user===null){
      this.router.navigate(['../access-denied'], {relativeTo: this.route});
    }
    this.devices$ = this.devicesService.listByUser(this.user.username);

    let stompClient = this.webSocketService.connect();

    let str = '/user/'+this.user.username+'/reply'
    stompClient.connect({}, frame => {
      stompClient.subscribe(str, data => {
        console.log(data)
        let jsonObj = JSON.parse(data.body);
        let warning = jsonObj as Warning;
        this.warnings.push(warning)
      })

    });
  }

  onLogout(){
    this.tokenStorageService.signOut();
    this.router.navigate(['../login'], {relativeTo: this.route});
  }

}
