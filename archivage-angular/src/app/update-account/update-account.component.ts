import {Component, Input, OnInit} from '@angular/core';
import {ClassificationNature} from '../models/classification-nature';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {ClassificationNatureService} from '../service/classification-nature.service';
import {Router} from '@angular/router';
import {Account} from '../models/account';
import {AccountService} from '../service/account.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {DocumentService} from '../service/document.service';
import {ContractDto} from '../models/contractDto';
import {AccountDto} from '../models/accountDto';
import {DocumentDto} from '../models/documentDto';

@Component({
  selector: 'app-update-account',
  templateUrl: './update-account.component.html',
  styleUrls: ['./update-account.component.css']
})
export class UpdateAccountComponent implements OnInit {

  @Input()  account: Account;
  @Input()  action: string;

  public status;
  constructor(public activeModal: NgbActiveModal,
              private accountService: AccountService,
              private router : Router,
              public documentService: DocumentService
              ) { }

  ngOnInit(): void {

  }


   updateAccount(){
    console.log(this.account)
    const account:AccountDto = new AccountDto();
    account.account_id_type_code = this.account.account_id_type_code;
    account.account_id_type_label = this.account.account_id_type_label;
    account.customerId = this.account.customer.id;
    account.account_number = this.account.account_number;
     account.id = this.account.id;
    account.status = this.account.status;
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
      this.documentService.files.push(element)
    }
    console.log(this.documentService.files);
  }



  docs: boolean=false;



  deleteAttachment(index) {
    this.documentService.files.splice(index, 1);
  }


  async addCocuments(account){
    console.log(account)
    const documentsDto: DocumentDto[] = await this.documentService.getListDocumentDto(this.documentService.files, account.customer);
    console.log(documentsDto);
    documentsDto.forEach(document => {
      document.contextDtoIn.accountId = account.id;
      document.contextDtoIn.classification_natureId = null;
    })
    console.log(documentsDto);
    let documents:any = null;
    setTimeout(async () =>  {
     this.documentService.addDocuments(documentsDto).then(res => {
       documents = res;
       if (documents != null){
         this.documentService.files = [];
         console.log(documents);
         this.closeModal();
       }
     });
    }, 1000);


  }
}

