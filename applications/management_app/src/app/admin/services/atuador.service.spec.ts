import { TestBed } from '@angular/core/testing';

import { AtuadorService } from './atuador.service';

describe('AtuadorService', () => {
  let service: AtuadorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AtuadorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
