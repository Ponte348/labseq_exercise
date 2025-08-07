import { TestBed } from '@angular/core/testing';

import { Labseq } from './labseq';

describe('Labseq', () => {
  let service: Labseq;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Labseq);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
