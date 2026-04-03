import { TestBed } from '@angular/core/testing';

import { TipoAtuadorService } from './tipo-atuador.service';

describe('TipoAtuadorService', () => {
  let service: TipoAtuadorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoAtuadorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
