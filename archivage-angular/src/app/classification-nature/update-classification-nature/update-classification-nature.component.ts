import {Component, Input, OnInit} from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import {ClassificationNature} from '../../models/classification-nature';
import {ClassificationNatureService} from '../../service/classification-nature.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-update-classification-nature',
  templateUrl: './update-classification-nature.component.html',
  styleUrls: ['./update-classification-nature.component.css']
})
export class UpdateClassificationNatureComponent implements OnInit {

  @Input()  classificationNature: ClassificationNature;

  constructor(public activeModal: NgbActiveModal, private classificationNatureService: ClassificationNatureService, private router : Router) { }

  ngOnInit(): void {
  }

  onSubmit(value: any) {
    console.log(this.classificationNature);
    this.classificationNatureService.update(value).subscribe(data => {
      this.closeModal();
      this.router.navigateByUrl('classifcation-nature')
    }, error => {

    })
  }

  closeModal() {
    this.activeModal.close();
  }
}
