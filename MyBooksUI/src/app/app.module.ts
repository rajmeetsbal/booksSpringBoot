import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SearchComponent } from './search/search.component';
import { HttpClientModule } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { MatListModule } from '@angular/material/list';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import {MatDialogModule} from '@angular/material/dialog';
import { DetailsOpenerComponent } from './details-opener/details-opener.component';
import { DetailsDialogComponent } from './details-dialog/details-dialog.component'; 
import {MatExpansionModule} from '@angular/material/expansion';
import { BookListItemComponent } from './book-list-item/book-list-item.component';
import { RecommendedComponent } from './recommended/recommended.component';
import { RecommendedListItemComponent } from './recommended-list-item/recommended-list-item.component';
import { RouterModule, Routes } from '@angular/router';
import { FavouriteComponent } from './favourite/favourite.component';
import { FavouritesListItemComponent } from './favourites-list-item/favourites-list-item.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { MatFormFieldModule } from '@angular/material';
// import { MatFormField, MatFormFieldModule } from '@angular/material';
// import { RouterService } from './services/router.service';
// import { CanActivateRouteGuard } from './can-activate-route.guard';

const routes:Routes = [
  {path:'register', component:RegisterComponent},
  {path:'login', component:LoginComponent},
  {path:'recommended', component:RecommendedComponent},
  {path:'favourites', component:FavouriteComponent},
  {path:'search', component:SearchComponent},
  {path:'',redirectTo:'search',pathMatch:'full'}
]

// const routes:Routes = [
//   {path:'recommended', component:RecommendedComponent},
//   {path:'search', component:DashboardComponent,canActivate:[CanActivateRouteGuard]},
//   {path:'',redirectTo:'login',pathMatch:'full'}
// ]


@NgModule({
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  declarations: [
    AppComponent,
    HeaderComponent,
    SearchComponent,
    DetailsOpenerComponent,
    DetailsDialogComponent,
    BookListItemComponent,
    RecommendedComponent,
    RecommendedListItemComponent,
    FavouriteComponent,
    FavouritesListItemComponent,
    LoginComponent,
    RegisterComponent
  ],
  entryComponents: [
    DetailsOpenerComponent,
    DetailsDialogComponent
  ],
  exports: [
    MatFormFieldModule
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatCardModule,
    FormsModule,
    MatListModule,
    MatProgressSpinnerModule,
    MatDialogModule,
    MatExpansionModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
