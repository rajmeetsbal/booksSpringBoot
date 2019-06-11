import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import * as jwt_decode from 'jwt-decode';
// import { decode } from '@angular/router/src/url_tree';
import { User } from '../User';

export const TOKEN_NAME: string = 'jwt_token';

@Injectable({
    providedIn:'root'
})
export class AuthenticationService {

	loggedIn:boolean = false;
	token: string;

	constructor(private httpClient: HttpClient) {
		
	}

	// registerUser(newUser: User) {
	// 	return this.httpClient.post('http://localhost:8083/api/v1/register', newUser, { responseType: 'text' });
	// }
	registerUser(newUser: User) {
		return this.httpClient.post('http://localhost:8765/userservice/api/v1/user', newUser, { responseType: 'text' });
	}

	loginUser(newUser) {
		return this.httpClient.post('http://localhost:8765/userservice/api/v1/login', newUser);
	}

	setToken(token: string) {
		return localStorage.setItem(TOKEN_NAME, token);
	}

	getToken() {
		return localStorage.getItem(TOKEN_NAME);
	}

	deleteToken() {
		return localStorage.removeItem(TOKEN_NAME);
	}

	getTokenExpirationDate(token: string): Date {
		const decoded: any = jwt_decode(token);
		if (decoded.exp === undefined) return null;
		const date = new Date(0);
		date.setUTCSeconds(decoded.exp);
		return date;
	}
	
	isTokenExpired(token?: string): boolean {
		if (!token) token = this.getToken();
		if (!token) return true;
		const date = this.getTokenExpirationDate(token);
		if (date === undefined || date === null) return false;
		return !(date.valueOf() > new Date().valueOf());
	}
}

