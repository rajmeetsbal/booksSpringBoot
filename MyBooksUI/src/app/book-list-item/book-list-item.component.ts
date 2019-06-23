import { Component, OnInit, Input } from '@angular/core';
import { Book } from '../book';
import { BookDetails } from '../bookDetails';
import { BookService } from '../services/bookService';
import { MatDialog } from '@angular/material';
import { DetailsDialogComponent } from '../details-dialog/details-dialog.component';
import { FavouriteBook } from '../favouriteBook';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-book-list-item',
  templateUrl: './book-list-item.component.html',
  styleUrls: ['./book-list-item.component.css']
})
export class BookListItemComponent implements OnInit {

  @Input()
  book : Book;
  bookDetail : BookDetails;
  // isFavourite : boolean;

  // fb : FavouriteBook;
  errorMessage: string;
  
  constructor(private dialog : MatDialog, private bookService: BookService, private authService: AuthenticationService) {
    //  this.fb = new FavouriteBook();
    //  this.isFavourite = true;
   }

  ngOnInit() {
    
    // this.isFavourite = true;
  }

  addToFavs() {
    // var favButton = document.getElementById("favButton"+this.book.isbn).disabled = "true";
    (<HTMLInputElement> document.getElementById("favButton"+this.book.isbn)).disabled = true;
    // if (this.note.text && this.note.title) {
      // FavouriteBooks fb = new FavouriteBooks();
      // console.log("user " + this.authService.loggedInUser);
      // this.fb.userId = this.authService.loggedInUser;
      // console.log("this.fb.userId "+this.fb.userId); 
      // isFavourite = false;
      if(this.book.isbn){
        this.book.id=this.book.isbn[0];
      }
      // this.fb.book = this.book;
      // console.log("this.fb.book "+this.fb.book);
      this.bookService.addFav(this.authService.loggedInUser,this.book).subscribe(addedBook => {
        
      }, error => {
        this.errorMessage = error.message;
      });
    // } else {
    //     this.errorMessage = 'Title and Text both are required fields';
    // }
  }

  

  openDialog(): void {

    this.bookService.getBookDetail(this.book.isbn[0]).subscribe(bookDetail => {
      console.log("resp using obser :  "+bookDetail);
      console.log(bookDetail[this.book.isbn[0]]);
      Object.keys(bookDetail).forEach(key => {
        // if (bookDetail[key].index === 2) {
            console.log("Found."+bookDetail[key]);
            this.bookDetail = bookDetail[key];
            // console.log("Found."+bookDetail[key].bib_key);
        // }
      });
      // bookListResponse.docs.forEach(function(item){
      //   if(item)
      // });
      // this.bookDetails = bookDetail;
      const dialogRef = this.dialog.open(DetailsDialogComponent, {
        width: '80%',
        height: '80%',
        data: this.bookDetail
        // data: {book: "sdsd"}
        // data: {description: this.bookDetails.details, thumbnail_url: this.bookDetails.thumbnail_url}
        // data: {description: "desc sample", thumbnail_url: "url samle"}
      });
      // console.log("isbn returned : " + this.bookList[0].isbn);
      // console.log("title data returned : " + this.bookList[0].bookTitle);
      // console.log("author data returned : " + this.bookList[0].bookAuthor);
      // console.log("author data returned : " + this.bookList[0].recommendedBy.length);
      // document.getElementById("loadSpin").style.display = "none";
    },
    error => {
      console.log("errror :  "+error.message);
      this.errorMessage = error.message;
    });

    // const dialogRef = this.dialog.open(DetailsDialogComponent, {
    //   width: '450px',
    //   height: '400px',
    //   data: this.book
    //   // data: {book: "sdsd"}
    //   // data: {description: this.bookDetails.details, thumbnail_url: this.bookDetails.thumbnail_url}
    //   // data: {description: "desc sample", thumbnail_url: "url samle"}
    // });

    // dialogRef.afterClosed().subscribe(result => {
    //   console.log('The dialog was closed');
    // });
  }

}
