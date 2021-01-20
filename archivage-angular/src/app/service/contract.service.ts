import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environment';
import {ContractDto} from '../models/contractDto';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  constructor(private httpClient : HttpClient) { }


  async createContract(contractDto: ContractDto) {
    return this.httpClient.post(environment.apiUrl+"/contract/add", contractDto).toPromise();
  }

  searchContractByClientNameOrContractNumber(client_name: string, contract_number: string) {
    return this.httpClient.get<ContractDto[]>(environment.apiUrl+"/contract/get-contracts-by-client-name-contract-number?client_name="+client_name+"&contract_number="+contract_number);
  }

  update(contract: ContractDto) {
    return this.httpClient.put(environment.apiUrl+"/contract/update", contract);
  }

  addDocsToContract(formdata: FormData) {
    return this.httpClient.put(environment.apiUrl+"/contract/update-contract-docs", formdata);

  }
}
