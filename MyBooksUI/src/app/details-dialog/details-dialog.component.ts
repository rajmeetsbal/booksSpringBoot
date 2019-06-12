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
  title:string;
  bookDetails: BookDetails;
  // isbn : string;
  book: Book;
  errMessage: string;
  constructor(private bookService: BookService,public dialogRef: MatDialogRef<DetailsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: BookDetails) { 
      // this.bookDetails = data;
      console.log("data in dialog is : " + data);
      console.log("data in dialog is : " + data.bib_key);
      // this.isbn = data.title;
      this.bookDetails = data;
      
      console.log("data in dialog is : " + this.bookDetails.details.title);
      console.log("data in dialog is : " + this.bookDetails.thumbnail_url);
      this.title = this.bookDetails.details.title;
      console.log("data in dialog is : " + this.title);
      // this.isbnId = data.isbnId;
    }

  ngOnInit() {
    
    console.log("on init....... " );
    // console.log("this.bookDetails... "+this.bookDetails);
    // let isbnId = this.bookDetails.bib_key;
    // console.log("isbnId... "+isbnId);
    // isbnId = isbnId.substring(5,isbnId.length);
    // console.log("isbnId... "+isbnId);
    // this.bookService.getBookDetail(isbnId).subscribe(bookListResponse => {
    //   console.log("resp using obser :  "+bookListResponse);
    //   this.bookDetails = bookListResponse;
    //   console.log("this.bookDetails : "+this.bookDetails.title);
      
      // document.getElementById("loadSpin").style.display = "none";
    // },
    // error => {
    //   this.errMessage = error.message;
    // });
  }

  closeDialog(){
    this.dialogRef.close();
  }


}
