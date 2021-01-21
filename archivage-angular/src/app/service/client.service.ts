import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {environment} from '../environment';
import {Client} from '../models/client';
import {Observable} from 'rxjs/index';
import {DocumentDto} from '../models/documentDto';
import {DocumentService} from './document.service';
import {AuthenticationService} from './authentication.service';
import { mergeMap, switchMap, map } from 'rxjs/operators';
import { from, interval, zip } from 'rxjs';




@Injectable({
  providedIn: 'root'
})
export class ClientService {
  public client: Client;
  jwtToken: string;
  constructor(private httpClient : HttpClient, private documentService: DocumentService, public authenticationSercvice: AuthenticationService) { }



  addCustomer = async (form) => {
    let customer = await this.httpClient.post(environment.apiUrl + "/customer/add-customer", form).toPromise();
    console.log(customer)
    return customer;
  }



  searchClientByNameOrNumberClient(client_name: string, client_number: number) {
    return this.httpClient.get<Client[]>(environment.apiUrl+"/customer/get-customers-by-client-name-number?client_name="+client_name+"&client_number="+client_number);

  }

  update(client: Client) {
   return this.httpClient.put(environment.apiUrl+"/customer/update", client);
  }
}
