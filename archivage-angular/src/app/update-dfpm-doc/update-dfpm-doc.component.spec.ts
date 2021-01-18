import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateDfpmDocComponent } from './update-dfpm-doc.component';

describe('UpdateDfpmDocComponent', () => {
  let component: UpdateDfpmDocComponent;
  let fixture: ComponentFixture<UpdateDfpmDocComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateDfpmDocComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateDfpmDocComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
