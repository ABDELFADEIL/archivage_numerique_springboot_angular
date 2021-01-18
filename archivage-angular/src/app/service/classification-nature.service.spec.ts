import { TestBed } from '@angular/core/testing';

import { ClassificationNatureService } from './classification-nature.service';

describe('ClassificationNatureService', () => {
  let service: ClassificationNatureService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClassificationNatureService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
