import { Book } from "./book";

export class FavouriteBooks{
    userId : string;
    favouritesList : Array<Book>;
    
    constructor() {
      this.favouritesList = [];
      }
}