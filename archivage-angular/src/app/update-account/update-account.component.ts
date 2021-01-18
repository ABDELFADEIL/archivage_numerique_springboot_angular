import {Component, Input, OnInit} from '@angular/core';
import {ClassificationNature} from '../models/classification-nature';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {ClassificationNatureService} from '../service/classification-nature.service';
import {Router} from '@angular/router';
import {Account} from '../models/account';
import {AccountService} from '../service/account.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.css']
})
export class UpdateAccountComponent implements OnInit {

  @Input()  account: Account;
  @Input()  action: string;

  public files: File [] = [];
  public status;
  constructor(public activeModal: NgbActiveModal, private accountService: AccountService, private router : Router) { }

  ngOnInit(): void {

  }

  onSubmit() {
    console.log(this.account.status)
    // this.account.status = this.status;
    console.log(this.account.status);
    const account:Account = this.account;
    this.accountService.update(account).subscribe(data => {
      this.closeModal();
      this.router.navigateByUrl('accounts')
    }, error => {
    })
  }

  closeModal() {
    this.activeModal.close();
  }

  uploadFile(event) {
    for (let index = 0; index < event.length; index++) {
      const element = event[index];
      this.files.push(element)
    }
    console.log(this.files);
  }



  docs: boolean=false;



  deleteAttachment(index) {
    this.files.splice(index, 1);
  }


  addDocs() {
    const formdata = new FormData();
    formdata.append("account_id", this.account.account_id);
    for (let file of this.files) {
      formdata.append("files", file);
    }
    this.accountService.addDocsToAccount(formdata).subscribe(data => {
      console.log(data);
      this.closeModal();
    }, error => {
      console.log(error);
    });
  }
}

