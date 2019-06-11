import { Component, OnInit } from '@angular/core';
import { BookService} from '../services/bookService';

import {} from '@angular/material';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { BookDetails } from '../bookDetails';
import { DetailsDialogComponent } from '../details-dialog/details-dialog.component';
import { RecommendedBook } from '../recommendedBook';

@Component({
  selector: 'app-recommended',
  templateUrl: './recommended.component.html',
  styleUrls: ['./recommended.component.css']
})
export class RecommendedComponent implements OnInit {

  searchString : string;
  searchBy : string;
  isbnId : string;
  book : RecommendedBook;
  bookList : Array<RecommendedBook>;
  bookDetails : BookDetails;
  errMessage: string;
  loadingIconIsVisible: boolean;
  panelOpenState : boolean;
  

  constructor(private bookService: BookService, private dialog : MatDialog) {
    this.book = new RecommendedBook();
    this.bookList = [];
  }

  ngOnInit() {
    // document.getElementById("loadSpin").style.display = "none";
    //this.getRecommendedBooks();
    this.bookService.getRecommendedBooks().subscribe(bookListResponse => {
      console.log("resp using obser :  "+bookListResponse);
      // bookListResponse.docs.forEach(function(item){
      //   if(item)
      // });
      this.bookList = bookListResponse;
      // console.log("isbn returned : " + this.bookList[0].isbn);
      console.log("title data returned : " + this.bookList[0].bookTitle);
      console.log("author data returned : " + this.bookList[0].bookAuthor);
      console.log("author data returned : " + this.bookList[0].recommendedBy.length);
      document.getElementById("loadSpin").style.display = "none";
    },
    error => {
      console.log("errror :  "+error.message);
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

  getRecommendedBooks(){
    console.log("called getRecommendedBooks");
      // document.getElementById("loadSpin").style.display = "block";
      this.bookService.getRecommendedBooks().subscribe(bookListResponse => {
        console.log("resp using obser :  "+bookListResponse);
        // bookListResponse.docs.forEach(function(item){
        //   if(item)
        // });
        this.bookList = bookListResponse;
        // console.log("isbn returned : " + this.bookList[0].isbn);
        console.log("title data returned : " + this.bookList[0].bookTitle);
        console.log("author data returned : " + this.bookList[0].bookAuthor);
        // document.getElementById("loadSpin").style.display = "none";
      },
      error => {
        this.errMessage = error.message;
      });
    
  }

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
