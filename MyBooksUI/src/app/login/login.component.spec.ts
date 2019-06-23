import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BookService } from '../services/bookService';
import { RouterService } from '../services/router.service';
import { HttpClient, HttpHandler, HttpClientModule } from '@angular/common/http';
import { MatDialog, MatToolbarModule, MatSidenavModule, MatListModule, MatIconModule, MatExpansionModule, MatFormFieldModule, MatInputModule, MatCardModule, MatDialogModule } from '@angular/material';
import { Overlay } from '@angular/cdk/overlay';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { By } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

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
        HttpClientModule,
        MatDialogModule,
        RouterTestingModule

      ],
      declarations: [ LoginComponent ],
      schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
      providers: [
        BookService, 
        RouterService
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
