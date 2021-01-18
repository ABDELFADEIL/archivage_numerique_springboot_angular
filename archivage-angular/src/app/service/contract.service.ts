import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environment';
import {Contract} from '../models/contract';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  constructor(private httpClient : HttpClient) { }


  createContract(form: FormData) {
    return this.httpClient.post(environment.apiUrl+"/api/contracts/new-contract-with-docs", form);
  }

  searchContractByClientNameOrContractNumber(client_name: string, contract_number: string) {
    return this.httpClient.get<Contract[]>(environment.apiUrl+"/api/contracts/get-contracts-by-client-name-number?client_name="+client_name+"&contract_number="+contract_number);
  }

  update(contract: Contract) {
    return this.httpClient.put(environment.apiUrl+"/api/contracts/create-event-contract", contract);
  }

  addDocsToContract(formdata: FormData) {
    return this.httpClient.put(environment.apiUrl+"/api/contracts/update-contract-docs", formdata);

  }
}
