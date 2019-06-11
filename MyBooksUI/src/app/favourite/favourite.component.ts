import { Component, OnInit } from '@angular/core';
import { Book } from '../book';
import { BookService} from '../services/bookService';

import {} from '@angular/material';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { BookDetails } from '../bookDetails';
import { DetailsDialogComponent } from '../details-dialog/details-dialog.component';
import { AuthenticationService } from '../services/authentication.service';
@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {
  searchString : string;
  searchBy : string;
  isbnId : string;
  book : Book;
  bookList : Array<Book>;
  bookDetails : BookDetails;
  errMessage: string;
  loadingIconIsVisible: boolean;
  panelOpenState : boolean;
  

  constructor(private bookService: BookService, private dialog : MatDialog, private authService: AuthenticationService) {
    this.book = new Book();
    this.bookList = [];
  }

  ngOnInit() {
      document.getElementById("loadSpin").style.display = "block";
      this.bookService.getFavouriteBooks(this.authService.loggedInUser).subscribe(bookListResponse => {
        // console.log("resp using obser :  "+bookListResponse);
        // bookListResponse.docs.forEach(function(item){
        //   if(item)
        // });
        this.bookList = bookListResponse;
        console.log("isbn returned : " + this.bookList[0].isbn);
        console.log("title data returned : " + this.bookList[0].title);
        console.log("author data returned : " + this.bookList[0].author_name);
        document.getElementById("loadSpin").style.display = "none";
      },
      error => {
        this.errMessage = error.message;
      });
    
  }

  // openDialog(): void {
  //   const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
  //     width: '250px',
  //     data: {name: this.name, animal: this.animal}
  //   });

  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log('The dialog was closed');
  //     this.animal = result;
  //   });
  // }

  getBookDetails(){
    console.log(this.isbnId);
      // document.getElementById("loadSpin").style.display = "block";
      this.bookService.getBookDetail(this.isbnId).subscribe(bookListResponse => {
        console.log("resp using obser :  "+bookListResponse);
        this.bookDetails = bookListResponse;
        // document.getElementById("loadSpin").style.display = "none";
      },
      error => {
        this.errMessage = error.message;
      });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DetailsDialogComponent, {
      width: '600px',
      height: '500px',
      data: this.book
      // data: {book: "sdsd"}
      // data: {description: this.bookDetails.details, thumbnail_url: this.bookDetails.thumbnail_url}
      // data: {description: "desc sample", thumbnail_url: "url samle"}
    });

    // dialogRef.afterClosed().subscribe(result => {
    //   console.log('The dialog was closed');
    // });
  }



}
