import { Component, OnInit } from '@angular/core';
import {ClassificationNatureService} from '../../service/classification-nature.service';
import {ClassificationNature} from '../../models/classification-nature';
import {Router} from '@angular/router';

@Component({
  selector: 'app-classification-nature-manage',
  templateUrl: './classification-nature-manage.component.html',
  styleUrls: ['./classification-nature-manage.component.css']
})
export class ClassificationNatureManageComponent implements OnInit {

  classificationNature : ClassificationNature;

  constructor(private classificationNatureService: ClassificationNatureService, private router: Router) { }

  ngOnInit(): void {

  }

  onSubmit(classificationNature) {
    this.classificationNature = classificationNature;
    console.log(this.classificationNature)
    this.classificationNatureService.create(this.classificationNature).subscribe( data => {
      console.log(data);
      this.router.navigateByUrl('/classifcation-nature');
    }, error => {
      console.log(error);
    });
  }




}
