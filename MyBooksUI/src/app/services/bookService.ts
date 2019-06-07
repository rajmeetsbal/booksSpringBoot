
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
// import { Book } from '../book';
import { BookSearchResult } from '../bookSearchResult';
import { BookDetails } from '../bookDetails';
import { RecommendedBook } from '../recommendedBook';
import { Book } from '../book';

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

    getRecommendedBooks() : Observable<Array<RecommendedBook>>{
        return this.http.get<Array<RecommendedBook>>('http://localhost:8765/book-recomendations/api/v1/recommended');
    }

    // getFavouriteBooks(userId: string) : Observable<Array<Book>>{
    //     return this.http.get<Array<Book>>('http://localhost:9300/api/v1/favourites/'+userId);
    // }

    getFavouriteBooks(userId: string) : Observable<Array<Book>>{
        return this.http.get<Array<Book>>('http://localhost:8765/book-favourites/api/v1/favourites/'+userId);
    }
    
}