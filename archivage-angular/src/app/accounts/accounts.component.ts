import { Component, OnInit } from '@angular/core';
import {Contract} from '../models/contract';
import {Client} from '../models/client';
import {ContractService} from '../service/contract.service';
import {Router} from '@angular/router';
import {ClientService} from '../service/client.service';
import {AccountService} from '../service/account.service';
import {Account} from '../models/account';
import {UpdateClassificationNatureComponent} from '../classification-nature/update-classification-nature/update-classification-nature.component';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {UpdateAccountComponent} from '../update-account/update-account.component';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
   public title = "Accounts";
  private supTitle: string;
  public account_number: string;
  public chercher: boolean = false;
  public accounts: Account[] =  [];
  public page : number = 1;
  public size : number= 12;
  public currentSize : number;
  currentPage : number = 1;
  public totalPages: number;
  public pages: number[];
  public keyword;
  public client : Client;
  public client_name;
  public client_number;
  private account: Account;

  constructor(private accountService: AccountService, private router: Router, private clientService: ClientService, private modalService: NgbModal) { }

  ngOnInit(): void {this.supTitle = "comptes"

  }



  onSubmit(client_name, account_number){
    console.log(client_name, account_number);
    this.chercher = true
    if (client_name==''){
      this.client_name = null;
    } else{
      this.client_name = client_name
    }
    if (account_number == ''){
      this.account_number = null;
    }else {
      this.account_number = account_number;
    }

    console.log(client_name)
    console.log(account_number)

    this.accountService.searchAccountByClientNameOrAccountNumber(this.client_name, this.account_number).subscribe(data => {
      this.accounts = data;
      console.log(this.accounts)
    }, error => {
      console.log(error)
    })

  }

  onAddDocsAccount(account: Account) {
   //this.accountService.addDocsToAccount()
    // this.router.navigateByUrl()
  }

  onUpdateAccount(account: Account) {

  }

  openFormModal(account:Account, action) {
    console.log(account);
    this.account = account;
    const modalRef = this.modalService.open(UpdateAccountComponent);
    modalRef.componentInstance.account = account;
    modalRef.componentInstance.action = action;
    modalRef.result.then((result) => {
    }).catch((error) => {
      console.log(error);
    });
  }
}

