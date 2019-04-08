import { Component } from '@angular/core';
import { AdminService } from './admin.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Admin Panel';

  constructor(private adminService: AdminService) { }

  Logout(): void {
    this.adminService.Logout()
    .subscribe((resp)=>{
      console.log(resp)
      window.location.href='http://localhost:8080';
    })
  }

}
