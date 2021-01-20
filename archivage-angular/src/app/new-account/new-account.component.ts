import { Component, OnInit } from '@angular/core';
import {Document} from '../models/document';
import {Client} from '../models/client';
import {ClassificationNature} from '../models/classification-nature';
import {ContractDto} from '../models/contractDto';
import {ContractService} from '../service/contract.service';
import {ClientService} from '../service/client.service';
import {Router} from '@angular/router';
import {ClassificationNatureService} from '../service/classification-nature.service';
import {AccountService} from '../service/account.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';
import {departs} from '../../environments/departs';
import {Account} from '../models/account';
import {AccountDto} from '../models/accountDto';
import {DocumentDto} from '../models/documentDto';
import {DocumentService} from '../service/document.service';

@Component({
  selector: 'app-new-account',
  templateUrl: './new-account.component.html',
  styleUrls: ['./new-account.component.css']
})
export class NewAccountComponent implements OnInit {

  public files: File [] = [];
  public document: Document;
  public model;
  public modelDep;
  client: Client;
  public classificationNatures: ClassificationNature [] = [];
  public created: boolean;
  public clientId: number;
  final_business_processing_date: any;
  public account: Account;
   client_name: string;
   client_number: number;
  clients: Client [] = [];
  public page : number = 1;
  public size : number= 12;
  public currentSize : number;
  currentPage : number = 1;
  public totalPages: number;
  public pages: number[];
  public keyword;
  chercher: boolean =false;
  create: boolean= false;
  constructor(private accountService: AccountService, private clientService: ClientService,
              private router: Router, private  classificationNatureService: ClassificationNatureService,
              public documentService: DocumentService) { }

  ngOnInit(): void {
    this.client = this.clientService.client;
    this.initializeFormGroup();
    this.getClassificationNature();
  }



  async addAccount(){
    const form = this.form.value;
    console.log(form)
    let accountDto: AccountDto = new AccountDto();
    accountDto.account_id_type_label = this.getAccountLabel(form.account_id_type_code);
    accountDto.account_id_type_code = form.account_id_type_code;
    accountDto.customerId = this.client.id;
    accountDto.account_number = null;
    accountDto.creating_date = new Date();
    accountDto.status = 0;
    const account = await this.accountService.createAccount(accountDto);
    return account;
  }
 async onSubmit(){
    const account: any = await this.addAccount();
    this.account = account;
    const documents = await this.addCocuments(account);
    console.log(documents);
    if (account){
      this.created = true;
    }
  }

  async addCocuments(account){
    console.log(account)
    const documentsDto: DocumentDto[] = await this.documentService.getListDocumentDto(this.documentService.files, this.client);
    console.log(documentsDto);
     documentsDto.forEach(document => {
      document.contextDtoIn.accountId = account.id;
      document.contextDtoIn.classification_natureId = this.form.value.classification_nature.id
    })
    console.log(documentsDto);
    let documents:any = null;
    setTimeout(async () =>  {
      documents = await this.documentService.addDocuments(documentsDto);
      console.log(documents)
    }, 1000);
    this.documentService.files = [];
    return documents;
  }

  getClassificationNature() {
    this.classificationNatureService.getAll().subscribe(value => {
      this.classificationNatures = value;
    }, error => {
      console.log(error);
    })
  }

  form :FormGroup = new FormGroup({
    // client_id: new FormControl(null),
    account_id_type_code: new FormControl('', Validators.required),
   // account_id_type_label: new FormControl(''),
    account_number: new FormControl(''),
    files: new FormControl('', Validators.required),
    final_business_processing_date: new FormControl(''),
    classification_nature: new FormControl(null, Validators.required)
  });

  initializeFormGroup() {
    this.form.setValue({
      account_id_type_code: '',
    //  accountt_id_type_label: '',
      account_number: '',
      files: '',
      final_business_processing_date: '',
      classification_nature: ''
    });
  }




  uploadFile(event) {
    for (let index = 0; index < event.length; index++) {
      const element = event[index];
      this.documentService.files.push(element)
    }
    console.log(this.documentService.files);
  }

  deleteAttachment(index) {
    this.documentService.files.splice(index, 1);
    if (this.files.length === 0){
      this.form.removeControl('files');
      this.form.addControl('files', new FormControl('', Validators.required));
    }
  }

  search = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      map(term => term === '' ? []
        :  this.classificationNatures.filter(v => v.classification_nature_label.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
    )
  formatter = (x: {classification_nature_label: string}) => x.classification_nature_label;

  searchDep = (text$: Observable<string>) =>
    text$.pipe(
      debounceTime(200),
      distinctUntilChanged(),
      map(term => term === '' ? []
        : departs.filter(v => v.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10))
    )



  onChercher(client_name, client_number) {

    this.chercher = true
    if (client_name==''){
      this.client_name = null;
    } else{
      this.client_name = client_name
    }
    if (client_number == ''){
      this.client_number = null;
    }else {
      this.client_number = client_number;
    }

    console.log(client_name)
    console.log(client_number)

    this.clientService.searchClientByNameOrNumberClient(this.client_name, this.client_number).subscribe(data => {
      console.log(data)
      this.clients = data;
      this.clientService.client = this.client;
    }, error => {
      console.log(error)
    })
  }

  onAddAccount(c: Client) {
    this.client = c;
  }

  private getAccountLabel(account_id_type_code) {
    if(account_id_type_code == 'CCP'){
      return 'Compte courant (CCP)';
    }else if(account_id_type_code == 'LIVRET A'){
      return  'LIVRET A';
    } else if(account_id_type_code == 'LIVRET Jeune'){
      return 'LIVRET Jeune';
    }else if(account_id_type_code == 'LEP'){
      return 'Livret d’épargne populaire (LEP)';
    }else if(account_id_type_code == 'PEL'){
      return 'Plan d’épargne logement (PEL)';
    }else if(account_id_type_code == 'PERP'){
      return 'Plan d’épargne retraite (PERP)';
    }else if(account_id_type_code == 'LDDS'){
      return 'Livret de développement durable et solidaire (LDDS)';
    }
  }
}




