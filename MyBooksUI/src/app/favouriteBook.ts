import { Book } from "./book";

export class FavouriteBook{
    userId : string;
    book : Book;
    
    constructor() {
      this.book = new Book();
      }
}