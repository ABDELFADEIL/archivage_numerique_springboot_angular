import { Component, OnInit } from '@angular/core';
import {ClassificationNature} from '../models/classification-nature';
import {ClassificationNatureService} from '../service/classification-nature.service';
import {Router} from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {UpdateClassificationNatureComponent} from './update-classification-nature/update-classification-nature.component';


@Component({
  selector: 'app-classification-nature',
  templateUrl: './classification-nature.component.html',
  styleUrls: ['./classification-nature.component.css']
})
export class ClassificationNatureComponent implements OnInit {
  classements: ClassificationNature [] = [];
  public page : number = 1;
  public size : number= 12;
  public currentSize : number;
  currentPage : number = 1;
  public totalPages: number;
  public pages: number[];
  public keyword;
  public classificationNature;

  constructor(private  classificationNatureService: ClassificationNatureService, private router: Router, private modalService: NgbModal) {
    this.getAllClassifcationNature();
  }
  public headerTitle:string="Classification Nature";


  ngOnInit(): void {

  }


  getAllClassifcationNature(){
    this.classificationNatureService.getAll().
    subscribe(value => {
      this.classements= value;
    }, error => {

      console.log(error);
    })
  }

  update(classificationNature: ClassificationNature) {
    this.classificationNatureService.update(classificationNature);
  }

  onDelete(id) {
    let conf = confirm("Êtes vous sûr de vouloir supprimer?");
    if(conf){
      this.classificationNatureService.delete(id).subscribe(value => {
        this.getAllClassifcationNature();
        this.router.navigateByUrl('/classifcation-nature');
      }, error => {
        console.log(error)
      });
    }

  }

  chercher(keyWord: any) {
    this.classificationNatureService.getByKeyWord(keyWord).subscribe(value => {
      this.classements= value;
    }, error => {
      console.log(error);
    });
  }


  openFormModal(c) {
    console.log(c);
    this.classificationNature = c;
    const modalRef = this.modalService.open(UpdateClassificationNatureComponent);
    modalRef.componentInstance.classificationNature = c;
    modalRef.result.then((result) => {
    }).catch((error) => {
      console.log(error);
    });
  }

}
