import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SalaListComponent } from './sala-list.component';

describe('SalaListComponent', () => {
  let component: SalaListComponent;
  let fixture: ComponentFixture<SalaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SalaListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SalaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
