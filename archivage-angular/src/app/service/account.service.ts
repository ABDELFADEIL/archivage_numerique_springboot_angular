import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environment';
import {Account} from '../models/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private httpClient : HttpClient) { }


  createAccount(form: FormData) {
    return this.httpClient.post(environment.apiUrl+"/api/accounts/create-new-account-with-docs", form);
  }

  searchAccountByClientNameOrAccountNumber(client_name: any, account_number: string) {
    return this.httpClient.get<Account[]>(environment.apiUrl+"/api/accounts/get-accounts-by-client-name-account-number?account_number="+account_number+"&client_name="+client_name);
  }

  addDocsToAccount(form: FormData) {
    return this.httpClient.put(environment.apiUrl+"/api/accounts/update-account-docs", form);
  }

  update(account: Account) {
    return this.httpClient.put(environment.apiUrl+"/api/accounts/create-event-account", account);
  }
}
