import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MicrocontroladorFormComponent } from './microcontrolador-form.component';

describe('MicrocontroladorFormComponent', () => {
  let component: MicrocontroladorFormComponent;
  let fixture: ComponentFixture<MicrocontroladorFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MicrocontroladorFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MicrocontroladorFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
