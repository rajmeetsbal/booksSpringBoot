import { Book } from "./book";

export class BookSearchResult{
    start : number;
    num_found : number;
    docs : Array<Book>;
    
    constructor() {
      }
}