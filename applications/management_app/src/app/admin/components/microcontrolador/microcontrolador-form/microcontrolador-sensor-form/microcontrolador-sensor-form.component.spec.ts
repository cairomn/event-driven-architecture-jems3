import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MicrocontroladorSensorFormComponent } from './microcontrolador-sensor-form.component';

describe('MicrocontroladorSensorFormComponent', () => {
  let component: MicrocontroladorSensorFormComponent;
  let fixture: ComponentFixture<MicrocontroladorSensorFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MicrocontroladorSensorFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MicrocontroladorSensorFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
