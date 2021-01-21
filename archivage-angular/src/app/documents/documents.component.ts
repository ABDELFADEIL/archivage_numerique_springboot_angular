import { Component, OnInit } from '@angular/core';
import {DocumentService} from '../service/document.service';
import { observe } from "rxjs-observe";
import {Document} from '../models/document';
import {EventType} from '../models/eventType';


@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrls: ['./documents.component.css']
})
export class DocumentsComponent implements OnInit {

  public page : number = 0;
  public size : number= 12;
  public currentSize : number;
  currentPage : number = 1;
  public totalPages: number;
  public pages: number[];
  public filter;
  documentsExists: any[] = [];
  selectedDocuments: any[] = [];
  documents: any[] = [];
  selectedAll = 'All';
  dateBfore:Date;
  dateAfter: Date;
  eventType:EventType = EventType.START_CUSTOMER_RELATIONSHIP

  constructor(private documentService: DocumentService) {
   // this.getAllDocsEventTypeBeforeDate(this.since);
  }

  ngOnInit(): void {
    //this.getAllDocsEventTypeBeforeDate(this.since);
  }


 async getArchivedDocumentsBetweenDateAfterAndDateBefore(){
    console.log(this.dateBfore, this.dateAfter, this.eventType);
    this.documentService.getArchivedDocumentsBetweenDateAfterAndDateBefore(this.dateAfter, this.dateBfore, this.eventType).
    then((value:any) => {
      this.documents = value;
      console.log(value);
      // this.totalPages=value["totalPages"];
      // this.pages= new Array<number>(this.totalPages);
      // this.documents = value;
    }, error => {
      console.log(error);
    })
  }

  onDelete(classification_nature_id: any) {

  }

  update() {
    if (this.documentsExists){
      const formdata = new FormData();
      formdata.append('documents', JSON.stringify(this.documentsExists));
      console.log(formdata)
      this.documentService.updateDFBM(formdata).subscribe(data => {
        console.log(data);
        this.getArchivedDocumentsBetweenDateAfterAndDateBefore();
      }, error => {
        console.log(error);
      })
  }

  }
  getAllDocsPage(page: number) {

  }

  ///

  onCheckboxChange(event, value) {
    const selectedAll = document.querySelectorAll('.selectedAll');
    const selectedBtn = document.querySelectorAll('.selectedBtn');

    if (value=='all') {
      this.documentsExists = [];
      this.selectedDocuments = [];
      if (event.target.checked) {
        this.selectedAll = 'selected';

        this.documents.forEach(d => {
          this.documentsExists.push(d.document_id);
        });
        selectedAll.forEach(function(input) {
          input['checked'] = true;
        });
      }
      else {
        this.selectedAll = 'all';
        this.documentsExists = [];
        this.selectedDocuments = [];
        selectedAll.forEach(function(input) {
          input['checked'] = false;
        });
      }
    } else {

      if (event.target.checked) {
        this.documentsExists.push(value);
      }
      if (!event.target.checked) {
        const index = this.documentsExists.indexOf(value);
        if (index > -1) {
          this.documentsExists.splice(index, 1);
        }
      }
      selectedBtn.forEach((input) => {
        this.selectedAll = 'all';
        input['checked'] = false;
      });
    }
    this.selectedDocuments = this.documentsExists.filter((value, index, array) =>
      !array.filter((v, i) => JSON.stringify(value) === JSON.stringify(v) && i < index).length);

    console.log(this.selectedDocuments);
  }

  deleteAllDocuments() {
    this.documentsExists
    if (this.documentsExists.length == 1) {
      this.documents.splice(1, 1)
    }
  }


}
