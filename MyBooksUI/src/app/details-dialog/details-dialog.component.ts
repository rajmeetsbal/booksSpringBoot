import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { BookDetails } from '../bookDetails';
import { Book } from '../book';
import { BookService } from '../services/bookService';

@Component({
  selector: 'app-details-dialog',
  templateUrl: './details-dialog.component.html',
  styleUrls: ['./details-dialog.component.css']
})
export class DetailsDialogComponent implements OnInit {
  bookDetails: BookDetails;
  // isbn : string;
  book: Book;
  errMessage: string;
  constructor(private bookService: BookService,public dialogRef: MatDialogRef<DetailsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Book) { 
      // this.bookDetails = data;
      console.log("data in dialog is : " + data.isbn);
      // this.isbn = data.title;
      this.book = data;
      
      // this.isbnId = data.isbnId;
    }

  ngOnInit() {
    this.bookService.getBookDetail(this.book.isbn[0]).subscribe(bookListResponse => {
      console.log("resp using obser :  "+bookListResponse);
      this.bookDetails = bookListResponse;
      console.log("this.bookDetails : "+this.bookDetails);
      // document.getElementById("loadSpin").style.display = "none";
    },
    error => {
      this.errMessage = error.message;
    });
  }

  closeDialog(){
    this.dialogRef.close();
  }


}
