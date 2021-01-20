import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environment';
import {Account} from '../models/account';
import {AccountDto} from '../models/accountDto';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient : HttpClient) { }


  async createAccount(accountDto: AccountDto) {
    return this.httpClient.post(environment.apiUrl+"/account/add", accountDto).toPromise();
  }

  searchAccountByClientNameOrAccountNumber(client_name: any, account_number: string) {
    return this.httpClient.get<Account[]>(environment.apiUrl+"/account/get-accounts-by-client-name-account-number?account_number="+account_number+"&client_name="+client_name);
  }


  update(accountDto: AccountDto) {
    return this.httpClient.put(environment.apiUrl+"/account/update", accountDto);
  }
}
