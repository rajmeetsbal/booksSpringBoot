import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class Jwt implements HttpInterceptor {
    
    constructor(private authService: AuthenticationService) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const jwtToken = sessionStorage.getItem(`${this.authService.token}`);
        const booksSearchApi = 'http://openlibrary.org/search.json';
        if (request.url.search(booksSearchApi) === -1) {
            if (jwtToken) {
                request = request.clone({
                    setHeaders: {
                        Authorization: `Bearer ${jwtToken}`,
                    }
                });
            }
            console.log(request);
        }
        return next.handle(request);
    }
}
