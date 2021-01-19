import { Injectable } from '@angular/core';
import {ClassificationNature} from '../models/classification-nature';
import {HttpClient} from '@angular/common/http';
import {environment} from '../environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClassificationNatureService {
  public classificationNature: ClassificationNature;

  constructor(private httpClient: HttpClient) { }

  getAll() :Observable<ClassificationNature []>{
   return this.httpClient.get<ClassificationNature []>(environment.apiUrl+"/classificationNature/get-all-classificationNature");
  }

  update(classificationNature:ClassificationNature) {
    return this.httpClient.put(environment.apiUrl+"/classificationNature/update", classificationNature);
  }

  delete(classificationNatureId: number) {
   return this.httpClient.delete(environment.apiUrl+ "/classificationNature/delete?classificationNatureId="+classificationNatureId);
  }

  create(classificationNature: ClassificationNature) {
    return this.httpClient.post(environment.apiUrl+"/classificationNature/add", classificationNature);
  }

  getByKeyWord(keyWord: any) {
    return this.httpClient.get<ClassificationNature []>(environment.apiUrl+"/classificationNature/get-all-classificationNature-keyWord?keyword="+keyWord);
  }
}
