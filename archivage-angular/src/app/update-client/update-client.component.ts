import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {ClientService} from '../service/client.service';
import {ContractDto} from '../models/contractDto';
import {Client} from '../models/client';
import {Router} from '@angular/router';
import {EventType} from '../models/eventType';

@Component({
  selector: 'app-update-client',
  templateUrl: './update-client.component.html',
  styleUrls: ['./update-client.component.css']
})
export class UpdateClientComponent implements OnInit {
  @Input()  client: Client;
  eventTypes: any = EventType;

  constructor(public activeModal: NgbActiveModal, private clientService:ClientService, private router: Router) { }

  ngOnInit(): void {
  }

  closeModal() {
    this.activeModal.close();
  }

  onSubmit() {
    console.log(this.client);
    const client: Client = this.client;
    this.clientService.update(client).subscribe(data => {
      this.closeModal();
      this.router.navigateByUrl('home')
    }, error => {

    })
  }
}
