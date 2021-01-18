import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassificationNatureComponent } from './classification-nature.component';

describe('ClassificationNatureComponent', () => {
  let component: ClassificationNatureComponent;
  let fixture: ComponentFixture<ClassificationNatureComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassificationNatureComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassificationNatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
