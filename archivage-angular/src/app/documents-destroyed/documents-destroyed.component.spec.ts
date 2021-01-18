import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentsDestroyedComponent } from './documents-destroyed.component';

describe('DocumentsDestroyedComponent', () => {
  let component: DocumentsDestroyedComponent;
  let fixture: ComponentFixture<DocumentsDestroyedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentsDestroyedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentsDestroyedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
