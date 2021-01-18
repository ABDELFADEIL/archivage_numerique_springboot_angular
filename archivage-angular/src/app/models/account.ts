import {Client} from './client';

export class Account {
  account_id:string
  creating_date: Date;
  account_id_type_label: string;
  account_id_type_code:string;
  account_number:number;
  status:string;
  client:Client;
}
