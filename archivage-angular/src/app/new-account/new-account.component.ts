import { Component, OnInit } from '@angular/core';
import {Document} from '../models/document';
import {Client} from '../models/client';
import {ClassificationNature} from '../models/classification-nature';
import {Contract} from '../models/contract';
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

  constructor(private accountService: AccountService, private clientService: ClientService, private router: Router, private  classificationNatureService: ClassificationNatureService) { }

  ngOnInit(): void {
    this.client = this.clientService.client;
    this.initializeFormGroup();
    this.getClassificationNature();
  }

  onSubmit(){
    // console.log(f.value)
    const form = this.form.value;
    console.log(form)
    const formdata = new FormData();
    const cn: ClassificationNature = form.classification_nature;
    console.log(cn);
    let account: Account = new Account();
    account.account_id_type_code = form.account_id_type_code;

    if(account.account_id_type_code == 'CCP'){
      account.account_id_type_label = 'Compte courant (CCP)';
    }else if(account.account_id_type_code == 'LIVRET A'){
      account.account_id_type_label = 'LIVRET A';
    } else if(account.account_id_type_code == 'LIVRET Jeune'){
      account.account_id_type_label = 'LIVRET Jeune';
    }else if(account.account_id_type_code == 'LEP'){
      account.account_id_type_label = 'Livret d’épargne populaire (LEP)';
    }else if(account.account_id_type_code == 'PEL'){
      account.account_id_type_label = 'Plan d’épargne logement (PEL)';
    }else if(account.account_id_type_code == 'PERP'){
      account.account_id_type_label = 'Plan d’épargne retraite (PERP)';
    }else if(account.account_id_type_code == 'LDDS'){
      account.account_id_type_label = 'Livret de développement durable et solidaire (LDDS)';
    }

    // contract.client = this.clientService.client;
   // const final_business_processing_date = '';
    console.log(account);
    for (let file of this.files) {
      formdata.append("files", file);
    }
    formdata.append('classificationNature', JSON.stringify(cn));
    //formdata.append('final_business_processing_date', JSON.stringify(final_business_processing_date));
    formdata.append('account', JSON.stringify(account));
    formdata.append('client', JSON.stringify(this.client));
    console.log(form);
    console.log(this.client);
    console.log(account);

    this.accountService.createAccount(formdata).subscribe(data => {
      console.log("onSubmit méthode réussie");
      this.created = true
      console.log(data);
      this.created = true;
      this.client = data[0].context.client;
      this.account = data[0].context.account;
      this.final_business_processing_date =  data[0].context.final_business_processing_date;
      // console.log(this.contract)

    }, error => {
      console.log(error);

    })


  }

  getClassificationNature() {
    this.classificationNatureService.getAll().subscribe(value => {
      this.classificationNatures = value;
    }, error => {
      console.log(error);
    })
  }
  //   public files: File [] = [];
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
      this.files.push(element)
    }
    console.log(this.files);
  }

  deleteAttachment(index) {
    this.files.splice(index, 1);
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
}




