import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';
import {User} from '../models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService:AuthenticationService, private router:Router)
  {
    // redirect to home if already logged in
    if (this.authService.jwtToken !=null) {
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {
    if (this.authService.jwtToken != null) {
      this.router.navigate(['/home']);
    }
  }


  onLogin(formData: User) {
    console.log(formData)
    this.authService.login(formData)
      .subscribe(resp => {
          console.log(formData)
          let jwtToken = resp.headers.get('authorization');
          this.authService.saveToken(jwtToken);
          this.router.navigateByUrl('/home');
        },
        err => {
        console.log(err)
          this.router.navigateByUrl('/login');

        })
  }

}
