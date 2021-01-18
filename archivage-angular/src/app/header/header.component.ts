import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  navbarOpen = false;
  public clicked = false;
  _el: any;

  constructor(private router: Router, public authService:AuthenticationService) { }

  ngOnInit(): void {}

  onClick(event): void {
    event.preventDefault();
    event.stopPropagation();
    this.clicked = true;
  }

  
@HostListener('document:click', ['event'])

  private clickedOutside(event): void {
    if (this.clicked) {
      this._el.nativeElement.querySelector('.dropdown-menu').classList.toggle('show');
    }}

  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }


  logout() {
    this.authService.logout();
  }
}
