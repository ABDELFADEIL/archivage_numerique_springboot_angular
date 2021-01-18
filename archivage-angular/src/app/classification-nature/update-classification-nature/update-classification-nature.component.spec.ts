import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateClassificationNatureComponent } from './update-classification-nature.component';

describe('UpdateClassificationNatureComponent', () => {
  let component: UpdateClassificationNatureComponent;
  let fixture: ComponentFixture<UpdateClassificationNatureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateClassificationNatureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateClassificationNatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
