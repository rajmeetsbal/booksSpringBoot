import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FavouritesListItemComponent } from './favourites-list-item.component';

describe('FavouritesListItemComponent', () => {
  let component: FavouritesListItemComponent;
  let fixture: ComponentFixture<FavouritesListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FavouritesListItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FavouritesListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  xit('should create', () => {
    expect(component).toBeTruthy();
  });
});
