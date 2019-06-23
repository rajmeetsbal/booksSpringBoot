import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsOpenerComponent } from './details-opener.component';

describe('DetailsOpenerComponent', () => {
  let component: DetailsOpenerComponent;
  let fixture: ComponentFixture<DetailsOpenerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailsOpenerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsOpenerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });
});
