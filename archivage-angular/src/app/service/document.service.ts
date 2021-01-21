import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ClassificationNature} from '../models/classification-nature';
import {environment} from '../environment';
import {Document} from '../models/document';
import {DocumentDto} from '../models/documentDto';
import {EventType} from '../models/eventType';
import {Event} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {
  public files: File[] = [];

  constructor(private httpClient: HttpClient) { }

  updateDFBM(documents: FormData) {
    return this.httpClient.put(environment.apiUrl+"/document/update-fbpd", documents);
  }

   async addDocuments(documents: DocumentDto[]) {
    return this.httpClient.post(environment.apiUrl + "/document/add-documents", documents).toPromise();
  }


   async getListDocumentDto(files: File[], client:any){
    let documentDtoList: DocumentDto[] = [];
    let conserv_unit_id: number = null;
    if (files.length > 1){
      conserv_unit_id = Math.floor(Math.random() * (1000000000 - 1 + 1) ) + 1;
    }
    this.files.forEach(file => {
      let documentDto: DocumentDto = new DocumentDto();
      documentDto.file_name = file.name;
      const fileNameArray = file.name.split(".");
      const archive_format = fileNameArray[fileNameArray.length - 1]
      documentDto.archive_format = archive_format;
      this.getBase64(file).then(
        (data: any) => {
          console.log(data)
          documentDto.encodedDoc = data;
        }
      );
      documentDto.contextDtoIn.archiving_reference_date = new Date();
      documentDto.contextDtoIn.classification_natureId = 1;
      documentDto.contextDtoIn.deletion_date = null;
      documentDto.contextDtoIn.eventType = 0;
      documentDto.contextDtoIn.mine_type = "**/"+archive_format;
      documentDto.contextDtoIn.frozen = false;
      documentDto.contextDtoIn.frozen_label = null;
      documentDto.contextDtoIn.hold_status = false;
      documentDto.contextDtoIn.deletion_date = null;
      documentDto.contextDtoIn.conserv_unit_id = conserv_unit_id;
      documentDto.contextDtoIn.customerId = client.id;
      console.log(documentDto)
      documentDtoList.push(documentDto);
    })
    console.log(documentDtoList);
    return documentDtoList;
  }
  getBase64(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  }

  getAllDocs() {
    return this.httpClient.get<Document []>(environment.apiUrl+"/document/all-docs-infos");
  }

  getDocById() :Observable<Document []>{
    return this.httpClient.get<Document []>(environment.apiUrl+"/document/get-doc-by-id?docId");
  }

  updateDocContext( docID,  context) {
    return this.httpClient.put(environment.apiUrl+"/document/update-doc-context-by-doc-id?docID="+docID, context);
  }

  delete(id: any) {
    return this.httpClient.delete(environment.apiUrl+ "/document/delete-one?docID="+id);
  }



  async getArchivedDocumentsBetweenDateAfterAndDateBefore(dateBefore:Date, dateAfter: Date, eventType:EventType) {
    let headerss = new Headers({
      "Content-Type": "application/json",
      "Accept": "application/json"
    });
    /*let headers = new Headers({
      "Content-Type": "application/json",
    });
    return await fetch(environment.apiUrl+"/document/getDocumentsBetweenDateAfterAndDateBefore?dateAfter="+dateAfter+"&dateBefore="+dateBefore+"&eventType="+eventType,{ method: 'GET',
      headers: headers});
*/
    return this.httpClient.get(environment.apiUrl+"/document/getDocumentsBetweenDateAfterAndDateBefore?dateAfter="
      +dateAfter+"&dateBefore="+dateBefore+"&eventType="+eventType, {
      observe: 'body', headers:new HttpHeaders({"Content-Type": "application/json", "Accept": "application/json"})})
      .toPromise();
  }
}
