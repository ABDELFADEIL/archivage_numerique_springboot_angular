import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root',
})

export class ClassificationNature {

   classification_nature_id : string;
   classification_nature_label : string;
   classification_nature_code : number;
   duration : number;

    constructor() {
    }
}
