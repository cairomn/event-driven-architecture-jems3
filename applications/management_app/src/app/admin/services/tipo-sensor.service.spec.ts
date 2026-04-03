import { TestBed } from '@angular/core/testing';

import { TipoSensorService } from './tipo-sensor.service';

describe('TipoSensorService', () => {
  let service: TipoSensorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoSensorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
