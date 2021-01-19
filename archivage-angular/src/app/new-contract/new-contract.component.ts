import {Component, Input, OnInit} from '@angular/core';
import {Document} from '../models/document';
import {Client} from '../models/client';
import {ClassificationNature} from '../models/classification-nature';
import {Router} from '@angular/router';
import {ClassificationNatureService} from '../service/classification-nature.service';
import {ClientService} from '../service/client.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';
import {departs} from '../../environments/departs';
import {ContractService} from '../service/contract.service';
import {ContractDto} from '../models/contractDto';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import {Account} from '../models/account';
import {DocumentService} from '../service/document.service';
import {DocumentDto} from '../models/documentDto';

@Component({
  selector: 'app-new-contract',
  templateUrl: './new-contract.component.html',
  styleUrls: ['./new-contract.component.css']
})
export class NewContractComponent implements OnInit {
 // public files: File [] = [];
  public document: Document;
  public model;
  public modelDep;
  client: Client;
  public classificationNatures: ClassificationNature [] = [];
  public created: boolean;
  public clientId: number;
  final_business_processing_date: any;
  public contract: ContractDto;
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
  contractDto: ContractDto;
  constructor(private contractService: ContractService, private clientService: ClientService,
              private router: Router, private classificationNatureService: ClassificationNatureService, public documentService: DocumentService) { }

  ngOnInit(): void {
      this.client = this.clientService.client;
      this.initializeFormGroup();
      this.getClassificationNature();
  }

 async addContract(){
    const form = this.form.value;
    console.log(form);
    this.contractDto = new ContractDto();
    this.contractDto.customerId = this.client.id;
    this.contractDto.contract_id_type_label = this.getContractIdTypeLabel(form.contract_id_type_code);
    this.contractDto.contract_id_type_code = form.contract_id_type_code;
    console.log(this.contractDto.contract_id_type_code);
    console.log(this.contractDto);
    const contract = await this.contractService.createContract(this.contractDto);
    console.log(contract)
    return contract;
  }

  async addCocuments(contract){
    const documentsDto: DocumentDto[] = await this.documentService.getListDocumentDto(this.documentService.files, this.client);
    console.log(documentsDto)
    documentsDto.forEach(document => {
      document.contextDtoIn.contractId = contract.id;
    })
    console.log(documentsDto);
    let documents:any = null;
    setTimeout(async () =>  {
      documents = await this.documentService.addDocuments(documentsDto);
      console.log(documents)
    }, 1000);
    this.documentService.files = []
    return documents;
  }
  async onSubmit() {
    const contract:any = await this.addContract();
    console.log(contract)
    const documents = await this.addCocuments(contract);
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
     contract_id_type_code: new FormControl('', Validators.required),
    contract_id_type_label: new FormControl(''),
    contract_number: new FormControl(''),
    files: new FormControl('', Validators.required),
    final_business_processing_date: new FormControl(''),
    classification_nature: new FormControl(null, Validators.required)
  });

  initializeFormGroup() {
    this.form.setValue({
      contract_id_type_code: '',
      contract_id_type_label: '',
      contract_number: '',
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

  onAddContract(c: Client) {
    this.client = c;
  }

  private getContractIdTypeLabel(contract_id_type_code) {
    if(contract_id_type_code == 'CAH'){
      return  'Contrats d\'assurance habitation';
    }else if(contract_id_type_code == 'CAV'){
      return  'Assurance vie';
    } else if(contract_id_type_code == 'CAHP'){
      return 'Contrats d\'assurance habitation professionnelle';
    }else if(contract_id_type_code == 'ADI'){
      return 'Assurance décès invalidité ';
    }else if(contract_id_type_code == 'PERP'){
       return 'Assurance automobile';
    }else if(contract_id_type_code == 'APJ'){
       return 'Assurance protection juridique';
    }
    else if(contract_id_type_code == 'AMP'){
      return 'Assurance moyens de paiements ';
    }
    else if(contract_id_type_code == 'AAP'){
        return 'Assurance activité professionnelle';
    }
    else if(contract_id_type_code == 'ACS'){
      return 'Assurance complémentaire santé';
    }
  }
}
