import { TestBed } from '@angular/core/testing';

import { MicrocontroladorService } from './microcontrolador.service';

describe('MicrocontroladorService', () => {
  let service: MicrocontroladorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MicrocontroladorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
