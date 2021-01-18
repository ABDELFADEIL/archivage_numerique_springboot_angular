import {Component, Input, OnInit} from '@angular/core';
import {Account} from '../models/account';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {AccountService} from '../service/account.service';
import {Router} from '@angular/router';
import {Contract} from '../models/contract';
import {ContractService} from '../service/contract.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-update-contract',
  templateUrl: './update-contract.component.html',
  styleUrls: ['./update-contract.component.css']
})
export class UpdateContractComponent implements OnInit {
  public files: File [] = [];
  public status;


  @Input()  contract: Contract;
  @Input()  action: string;
  private contract_id: string;
  constructor(public activeModal: NgbActiveModal, private contractService: ContractService, private router : Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log(this.contract);
    const contract: Contract = this.contract;
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
      this.files.push(element)
    }
    console.log(this.files);
  }

  deleteAttachment(index) {
    this.files.splice(index, 1);
  }

  addDocs() {
    console.log("addDocs() method ****");
    const formdata = new FormData();
    formdata.append("contract_id", this.contract.contract_id);
    for (let file of this.files) {
      formdata.append("files", file);
    }
    console.log(this.files)
    this.contractService.addDocsToContract(formdata).subscribe(data => {
      console.log(data);
      this.closeModal();
    }, error => {
      console.log(error);
    });
  }


}


