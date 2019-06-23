import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { SearchComponent } from './search.component';
import { BookService } from '../services/bookService';
import { HttpClient, HttpHandler, HttpClientModule } from '@angular/common/http';
import { MatDialog, MatToolbarModule, MatSidenavModule, MatListModule, MatIconModule, MatExpansionModule, MatFormFieldModule, MatInputModule, MatCardModule, MatDialogModule } from '@angular/material';
import { Overlay } from '@angular/cdk/overlay';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { By } from '@angular/platform-browser';



describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;

  let bookService : BookService;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        MatToolbarModule,
        MatSidenavModule,
        MatListModule,
        MatIconModule,
        MatExpansionModule,
        FormsModule,
        MatFormFieldModule,
        BrowserAnimationsModule,
        MatInputModule,
        MatCardModule,
        HttpClientModule,
        MatDialogModule
      ],
      declarations: [ SearchComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
      providers: [BookService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // it('should fetch books from server and display in html',()=>{
  //   let spyNotes =  spyOn(bookService,'getBooks').and.returnValue( of(testData) );
  //   fixture.detectChanges();
  //   let debugEle = fixture.debugElement.query(By.css('.note-title'));
  //    expect(debugEle.nativeElement.textContent).toBe(testData[0].title);
  //  })
 


});
