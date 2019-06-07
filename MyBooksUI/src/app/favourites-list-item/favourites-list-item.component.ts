import { Component, OnInit, Input } from '@angular/core';
import { Book } from '../book';
import { MatDialog } from '@angular/material';
import { DetailsDialogComponent } from '../details-dialog/details-dialog.component';

@Component({
  selector: 'app-favourites-list-item',
  templateUrl: './favourites-list-item.component.html',
  styleUrls: ['./favourites-list-item.component.css']
})
export class FavouritesListItemComponent implements OnInit {
  
  @Input()
  book : Book;

  constructor(private dialog : MatDialog) { }

  ngOnInit() {
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
