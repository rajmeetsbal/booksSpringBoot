import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material';
import { BookDetails } from '../bookDetails';
import { BookService } from '../services/bookService';
import { DetailsDialogComponent } from '../details-dialog/details-dialog.component';
import { Book } from '../book';
@Component({
  selector: 'app-details-opener',
  templateUrl: './details-opener.component.html',
  styleUrls: ['./details-opener.component.css']
})
export class DetailsOpenerComponent implements OnInit {

  @Input()
  book : Book;

  isbnId : string;
  bookDetails : BookDetails;
  errMessage : string;
  constructor(private activatedRoute : ActivatedRoute, 
              public dialog: MatDialog,
              public bookService : BookService) { 
    this.activatedRoute.params.subscribe(param => {
      this.book = param.book;
    } );
    // console.log('noteId --Edit',this.noteId);
    
    //Fetch the note from notes List
    // this.bookService.getBookDetail(this.isbnId).subscribe(bookDetailsResponse => {
    //   console.log("resp using obser :  "+bookDetailsResponse);
    //   this.bookDetails = bookDetailsResponse;
    //   document.getElementById("loadSpin").style.display = "none";
    // },
    // error => {
    //   this.errMessage = error.message;
    // });



    // this.dialog.open(DetailsDialogComponent,{
    //   data : this.bookDetails
    // }).afterClosed().subscribe(res =>{
    // })
    
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DetailsDialogComponent, {
      // width: '250px',
      // data: this.book
      data: {book: "sdsd"}
      // data: {description: this.bookDetails.details, thumbnail_url: this.bookDetails.thumbnail_url}
      // data: {description: "desc sample", thumbnail_url: "url samle"}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }

  ngOnInit() {
    
  }

}
