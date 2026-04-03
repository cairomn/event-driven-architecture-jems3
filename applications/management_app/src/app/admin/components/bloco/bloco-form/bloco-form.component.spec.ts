import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlocoFormComponent } from './bloco-form.component';

describe('BlocoFormComponent', () => {
  let component: BlocoFormComponent;
  let fixture: ComponentFixture<BlocoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlocoFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BlocoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
