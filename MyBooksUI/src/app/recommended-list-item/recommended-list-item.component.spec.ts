import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendedListItemComponent } from './recommended-list-item.component';

describe('RecommendedListItemComponent', () => {
  let component: RecommendedListItemComponent;
  let fixture: ComponentFixture<RecommendedListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecommendedListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecommendedListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
