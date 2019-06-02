
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
// import { Book } from '../book';
import { BookSearchResult } from '../bookSearchResult';
import { BookDetails } from '../bookDetails';

@Injectable({
    providedIn:'root'
})
export class BookService{
    constructor(private http: HttpClient) {   
    }
  
    getBooks(searchString: string,searchBy:string) : Observable<BookSearchResult>{
      if(searchString.indexOf(" ") > -1)
      searchString = searchString.replace(" ","+");
      return this.http.get<BookSearchResult>('http://openlibrary.org/search.json?'+searchBy+'='+searchString);
    }

    getBookDetail(isbnId: string) : Observable<BookDetails>{
        return this.http.get<BookDetails>('https://openlibrary.org/api/books?jscmd=details&format=json&bibkeys=ISBN:'+isbnId);
    }

}