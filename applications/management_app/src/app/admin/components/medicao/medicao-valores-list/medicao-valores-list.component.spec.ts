import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicaoValoresListComponent } from './medicao-valores-list.component';

describe('MedicaoValoresListComponent', () => {
  let component: MedicaoValoresListComponent;
  let fixture: ComponentFixture<MedicaoValoresListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicaoValoresListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicaoValoresListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
