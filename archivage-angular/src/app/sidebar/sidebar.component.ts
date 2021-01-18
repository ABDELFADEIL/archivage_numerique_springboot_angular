import {Component, ElementRef, HostListener, OnInit, Renderer2, ViewChild} from '@angular/core';
import {User} from '../models/user';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';
import {Title} from '@angular/platform-browser';
import {filter, map} from 'rxjs/operators';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  title ;
  toggle:string;
  public clicked = false;
  _el: any;
  sidbarOpenClients: boolean = false;
  sidbarOpenContracts: boolean = false;
  sidbarOpenAccounts: boolean = false;
  user: User;
  // titleAfiche:string;
  supTitle: string;
  @ViewChild('tog') toggleButton: ElementRef;
  public sidbarOpenNC: boolean= false;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, public authService:AuthenticationService,
              private renderer: Renderer2, private titleService: Title, private _eref: ElementRef) {
    const appTitle = this.titleService.getTitle();

    this.router
      .events.pipe(
      filter(event =>
        event instanceof NavigationEnd),
      map(() => {
        // this.supTitle = this.activatedRoute;
        const child = this.activatedRoute.firstChild;
        if (child.snapshot.data['title']) {
          return child.snapshot.data['title'];
        }
        return appTitle;
      })

    ).subscribe(ttl => {
      console.log("appTitle "+ttl)

      this.titleService.setTitle(ttl);
      this.title = this.titleService.getTitle();

    });

    renderer.listen('document', 'click', (event) => {
      this.toggle = event.target.className;
      if (!this.toggle.startsWith('dropdown-toggle')){
        this.toggleNavbar();
      }
    });
  }

  onClick(event): void {
    event.preventDefault();
    event.stopPropagation();
    this.clicked = true;



  }

  @HostListener('document:click', ['event'])

  private clickedOutside(event): void {
    if (this.clicked) {
      this._el.nativeElement.querySelector('.dropdown-menu').classList.toggle(['show', 'side']);

    }}


  toggleNavbarClients() {
    this.sidbarOpenClients = !this.sidbarOpenClients;
    this.sidbarOpenContracts = false;
    this.sidbarOpenAccounts = false;
    this.sidbarOpenNC = false;

  }
  toggleNavbar() {
    if (this.sidbarOpenClients){
      this.sidbarOpenClients = false;
    }
    if (this.sidbarOpenContracts) {
      this.sidbarOpenContracts = false;
    }
    if (this.sidbarOpenAccounts){
      this.sidbarOpenAccounts = false;
    }
    if(this.sidbarOpenNC){
      this.sidbarOpenNC = false;
    }
  }

  toggleNavbarContracts() {
    this.sidbarOpenContracts = !this.sidbarOpenContracts;
    this.sidbarOpenClients = false;
    this.sidbarOpenAccounts = false;
    this.sidbarOpenNC = false;

  }
  toggleNavbarAccounts() {
    this.sidbarOpenAccounts = !this.sidbarOpenAccounts;
    this.sidbarOpenContracts = false;
    this.sidbarOpenClients = false;
    this.sidbarOpenNC = false;

  }

  ngOnInit(): void {
  }

  toggleNavbarNC() {
    this.sidbarOpenNC = !this.sidbarOpenNC;
    this.sidbarOpenContracts = false;
    this.sidbarOpenClients = false;
    this.sidbarOpenAccounts = false;
  }
}
