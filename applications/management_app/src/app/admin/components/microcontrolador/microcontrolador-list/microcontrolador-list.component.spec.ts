import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MicrocontroladorListComponent } from './microcontrolador-list.component';

describe('MicrocontroladorListComponent', () => {
  let component: MicrocontroladorListComponent;
  let fixture: ComponentFixture<MicrocontroladorListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MicrocontroladorListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MicrocontroladorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
