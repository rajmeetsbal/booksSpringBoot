
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BookSearchResult } from '../bookSearchResult';
import { BookDetails } from '../bookDetails';
import { RecommendedBook } from '../recommendedBook';
import { Book } from '../book';
import { tap } from 'rxjs/operators';
import { FavouriteBooks } from '../favouriteBooks';
import { AuthenticationService} from '../services/authentication.service';

@Injectable({
    providedIn:'root'
})
export class BookService{
    userId : string;

    // notesSubject: BehaviorSubject<Array<Note>>;
    
    constructor(private http: HttpClient, private authService: AuthenticationService) {   

        // this.favSubject = new BehaviorSubject([]);
        this.userId = "rajmeet";
        this.http = http;
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

    
  addFav(userId: string, book:Book): Observable<Book> {
    return this.http.post<Book>('http://localhost:8765/book-favourites/api/v1/favourites/'+userId, book, {
    headers: new HttpHeaders().set('Authorization', `Bearer ${this.authService.getToken()}`)
    }).pipe(
      tap(addedFav => {
          console.log("piping... at bookservice");
    }));
  }
    
}