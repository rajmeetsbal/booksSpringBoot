import { Component, OnInit, Input } from '@angular/core';
import { MatDialog } from '@angular/material';
import { DetailsDialogComponent } from '../details-dialog/details-dialog.component';
import { RecommendedBook } from '../recommendedBook';

@Component({
  selector: 'app-recommended-list-item',
  templateUrl: './recommended-list-item.component.html',
  styleUrls: ['./recommended-list-item.component.css']
})
export class RecommendedListItemComponent implements OnInit {

  @Input()
  book : RecommendedBook;

  constructor(private dialog : MatDialog) { }

  ngOnInit() {
    console.log("this.book in rec  list item : "+this.book)
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
