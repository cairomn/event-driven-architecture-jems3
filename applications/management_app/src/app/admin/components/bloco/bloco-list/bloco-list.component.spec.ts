import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BlocoListComponent } from './bloco-list.component';

describe('BlocoListComponent', () => {
  let component: BlocoListComponent;
  let fixture: ComponentFixture<BlocoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BlocoListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BlocoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
