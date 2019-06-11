import { Component, OnInit, Input } from '@angular/core';
import { Book } from '../book';
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

  fb : FavouriteBook;
  errorMessage: string;
  
  constructor(private dialog : MatDialog, private bookService: BookService, private authService: AuthenticationService) {
     this.fb = new FavouriteBook();
   }

  ngOnInit() {
  }

  addToFavs() {
    // if (this.note.text && this.note.title) {
      // FavouriteBooks fb = new FavouriteBooks();
      // console.log("user " + this.authService.loggedInUser);
      // this.fb.userId = this.authService.loggedInUser;
      // console.log("this.fb.userId "+this.fb.userId);
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
    const dialogRef = this.dialog.open(DetailsDialogComponent, {
      width: '450px',
      height: '400px',
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
