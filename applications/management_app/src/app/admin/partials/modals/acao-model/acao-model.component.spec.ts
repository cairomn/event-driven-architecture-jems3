import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcaoModelComponent } from './acao-model.component';

describe('AcaoModelComponent', () => {
  let component: AcaoModelComponent;
  let fixture: ComponentFixture<AcaoModelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcaoModelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AcaoModelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
