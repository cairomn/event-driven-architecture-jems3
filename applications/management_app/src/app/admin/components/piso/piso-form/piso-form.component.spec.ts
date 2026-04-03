import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PisoFormComponent } from './piso-form.component';

describe('PisoFormComponent', () => {
  let component: PisoFormComponent;
  let fixture: ComponentFixture<PisoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PisoFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PisoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
