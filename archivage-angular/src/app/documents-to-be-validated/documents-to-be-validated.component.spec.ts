import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentsToBeValidatedComponent } from './documents-to-be-validated.component';

describe('DocumentsToBeValidatedComponent', () => {
  let component: DocumentsToBeValidatedComponent;
  let fixture: ComponentFixture<DocumentsToBeValidatedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentsToBeValidatedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentsToBeValidatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
