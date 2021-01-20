import {Component, Input, OnInit} from '@angular/core';
import {Account} from '../models/account';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {AccountService} from '../service/account.service';
import {Router} from '@angular/router';
import {ContractDto} from '../models/contractDto';
import {ContractService} from '../service/contract.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {DocumentService} from '../service/document.service';
import {DocumentDto} from '../models/documentDto';

@Component({
  selector: 'app-update-contract',
  templateUrl: './update-contract.component.html',
  styleUrls: ['./update-contract.component.css']
})
export class UpdateContractComponent implements OnInit {

  public status;


  @Input()  contract: any;
  @Input()  action: string;
  constructor(public activeModal: NgbActiveModal,
              private contractService: ContractService,
              private router : Router,
              public documentService: DocumentService
              ) { }

  ngOnInit(): void {
    console.log(this.contract);
    console.log(this.action);
  }

  updateContract() {
    console.log(this.contract);
    const contract: ContractDto = new ContractDto();
    contract.contract_id_type_code = this.contract.contract_id_type_code;
    contract.contract_id_type_label = this.contract.contract_id_type_label;
    contract.customerId = this.contract.customer.id;
    contract.contract_number = this.contract.contract_number;
    contract.status = this.contract.status;
    contract.id = this.contract.id;
    this.contractService.update(contract).subscribe(data => {
      this.closeModal();
      this.router.navigateByUrl('contracts')
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

  deleteAttachment(index) {
    this.documentService.files.splice(index, 1);
  }


  async addCocuments(contract){
    console.log(contract)
    const documentsDto: DocumentDto[] = await this.documentService.getListDocumentDto(this.documentService.files, contract.customer);
    console.log(documentsDto);
    documentsDto.forEach(document => {
      document.contextDtoIn.contractId = contract.id;
      document.contextDtoIn.classification_natureId = null;
    })
    console.log(documentsDto);
    let documents:any = null;
    setTimeout(async () =>  {
      documents = await this.documentService.addDocuments(documentsDto).then(res => {
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


