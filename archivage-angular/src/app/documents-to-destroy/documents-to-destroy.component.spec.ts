import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentsToDestroyComponent } from './documents-to-destroy.component';

describe('DocumentsToDestroyComponent', () => {
  let component: DocumentsToDestroyComponent;
  let fixture: ComponentFixture<DocumentsToDestroyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentsToDestroyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentsToDestroyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
