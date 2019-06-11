import { Component, OnInit, Input } from '@angular/core';
import { Book } from '../book';
import { BookService } from '../services/bookService';
import { MatDialog } from '@angular/material';
import { DetailsDialogComponent } from '../details-dialog/details-dialog.component';
import { FavouriteBooks } from '../favouriteBooks';

@Component({
  selector: 'app-book-list-item',
  templateUrl: './book-list-item.component.html',
  styleUrls: ['./book-list-item.component.css']
})
export class BookListItemComponent implements OnInit {

  @Input()
  book : Book;

  fb : FavouriteBooks;
  errorMessage: string;
  constructor(private dialog : MatDialog, private bookService: BookService) {
     this.fb = new FavouriteBooks();
   }

  ngOnInit() {
  }

  addToFavs() {
    // if (this.note.text && this.note.title) {
      // FavouriteBooks fb = new FavouriteBooks();
      this.fb.userId = "rajmeet";
      console.log("this.fb.userId "+this.fb.userId);
      this.fb.favouritesList[0] = this.book;
      // this.fb.favouritesList[0] = {"title":this.book.title,"author_name":this.book.author_name[0]};
      this.bookService.addFav(this.fb).subscribe(addedBook => {
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
