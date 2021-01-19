import {Component, OnInit, ViewChild} from '@angular/core';
import {ClientService} from '../../service/client.service';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';
import {Document} from '../../models/document';
import {ClassificationNatureService} from '../../service/classification-nature.service';
import {ClassificationNature} from '../../models/classification-nature';
import { NgbModal, NgbTypeahead} from '@ng-bootstrap/ng-bootstrap';
import {Observable, Subject, merge} from 'rxjs';
import {debounceTime, distinctUntilChanged, filter, map} from 'rxjs/operators';
import {Client} from '../../models/client';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {departs} from '../../../environments/departs';
import {NewContractComponent} from '../../new-contract/new-contract.component';
import {DocumentDto} from '../../models/documentDto';
import {DocumentService} from '../../service/document.service';
import {CustomerDto} from '../../models/customerDto';



@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent implements OnInit {
//  public files: File [] = [];
  public document: Document;
  public model;
  public modelDep;
  client: Client;
  documentDto: DocumentDto = new DocumentDto();

  @ViewChild('instance', {static: true}) instance: NgbTypeahead;

  public classificationNatures: ClassificationNature [] = [];
  public created: boolean;
  public clientId: number;


  constructor(private clientService: ClientService, private router: Router,
              public documentService: DocumentService)
  {
    this.router.events.subscribe((val) => {
      if(val instanceof NavigationEnd){
        this.initializeFormGroup();
      }
    });
  }

  ngOnInit(): void {
    if (this.clientService.client){
      this.created = true;
      console.log(this.client)
    }
    this.initializeFormGroup();
  }


    form :FormGroup = new FormGroup({
      // client_id: new FormControl(null),
      client_name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      client_first_name: new FormControl('', [Validators.required, Validators.minLength(3)]),
      client_nature_id: new FormControl('', [Validators.required, Validators.minLength(1)]),
      civility_id: new FormControl('', [Validators.required, Validators.minLength(1)]),
      birth_date: new FormControl('', Validators.required),
      birth_dept: new FormControl('', Validators.required),
      siren_number: new FormControl(''),
      siret_number: new FormControl('')
    });

  initializeFormGroup() {
    this.form.setValue({
      client_name: '',
      client_first_name: '',
      client_nature_id: '',
      civility_id: '',
      birth_date: '',
      birth_dept: '',
      siren_number: '',
      siret_number: '',
    });
  }

  async addCustomerAndDocuments() {
    const customer:any = await this.clientService.addCustomer(this.form.value);
    if (customer != 'undefined' || customer != null){
      this.clientService.client = customer;
      this.client = customer;
    }
    // const documentDtoList = await this.getListDocumentDto(customer);
    const documents = await this.addDocument(customer);
    return documents;
  }

  addDocument = async (customer) => {
    const documentDtoList = await this.getListDocumentDto(customer);
    console.log(documentDtoList);
    let documents = null;
    setTimeout(async () =>  {
      documents = await this.documentService.addDocuments(documentDtoList);
      console.log(documents)
      documents = documents;
    }, 1000);
    return documents;
  }

  getListDocumentDto =  async (customer)=> {
    console.log(customer)
    const documentsDto = await this.documentService.getListDocumentDto(this.documentService.files, customer);
    console.log(documentsDto);
    return documentsDto;
  }

  async onSubmit() {
    const response =  await this.addCustomerAndDocuments();
    if (response != null || response != 'undefined'){
      this.created = true;
      this.documentService.files = [];
    }
      console.log(response)

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
    if (this.documentService.files.length === 0){
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


}
