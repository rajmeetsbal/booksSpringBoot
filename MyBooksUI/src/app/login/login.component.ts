import { OnInit, Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { User } from './../User';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

	newUser: User;

	constructor(
		private authService: AuthenticationService,
		private router: Router
	) {
		this.newUser = new User();
	}

	ngOnInit() {
	}

	loginUser() {
		this.authService.loginUser(this.newUser).subscribe(data => {
			if (data['token']) {
				this.authService.setToken(data['token']);
				this.router.navigate(['/search']);
			}
		});
	}
}