import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassificationNatureManageComponent } from './classification-nature-manage.component';

describe('ClassificationNatureManageComponent', () => {
  let component: ClassificationNatureManageComponent;
  let fixture: ComponentFixture<ClassificationNatureManageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassificationNatureManageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassificationNatureManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
