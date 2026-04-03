import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MicrocontroladorAtuadorFormComponent } from './microcontrolador-atuador-form.component';

describe('MicrocontroladorAtuadorFormComponent', () => {
  let component: MicrocontroladorAtuadorFormComponent;
  let fixture: ComponentFixture<MicrocontroladorAtuadorFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MicrocontroladorAtuadorFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MicrocontroladorAtuadorFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
