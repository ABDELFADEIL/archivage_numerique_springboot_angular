import { Component, OnInit } from '@angular/core';
import {Contract} from '../models/contract';
import {ContractService} from '../service/contract.service';
import {Router} from '@angular/router';
import {ClientService} from '../service/client.service';
import {Client} from '../models/client';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {Account} from '../models/account';
import {UpdateAccountComponent} from '../update-account/update-account.component';
import {UpdateContractComponent} from '../update-contract/update-contract.component';

@Component({
  selector: 'app-contracts',
  templateUrl: './contracts.component.html',
  styleUrls: ['./contracts.component.css']
})
export class ContractsComponent implements OnInit {
  public title = "Contrats"
  public contract_number: string;
  public chercher: boolean = false;
  public contracts: Contract [] =  [];
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
  private contract: Contract;

  constructor(private contractService: ContractService, private router: Router, private clientService: ClientService, private modalService: NgbModal) { }

  ngOnInit(): void {
  }


  onSubmit(client_name, contract_number){
    console.log(client_name, contract_number);
    this.chercher = true
    if (client_name==''){
      this.client_name = null;
    } else{
      this.client_name = client_name
    }
    if (contract_number == ''){
      this.contract_number = null;
    }else {
      this.contract_number = contract_number;
    }

    console.log(client_name)
    console.log(contract_number)

    this.contractService.searchContractByClientNameOrContractNumber(this.client_name, this.contract_number).subscribe(data => {
      this.contracts = data;
      console.log(this.contracts)
    }, error => {
      console.log(error)
    })

  }

  onAddDocsContract(c: Contract) {

  }

  onUpdateContract(c: Contract) {

  }


  openFormModal(contract:Contract, action) {
    console.log(contract);
    this.contract = contract;
    const modalRef = this.modalService.open(UpdateContractComponent);
    modalRef.componentInstance.contract = contract;
    modalRef.componentInstance.action = action;
    modalRef.result.then((result) => {
    }).catch((error) => {
      console.log(error);
    });
  }
}
