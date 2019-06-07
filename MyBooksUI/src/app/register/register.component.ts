import { AuthenticationService } from '../services/authentication.service';
import { OnInit, Component } from '@angular/core';
import { User } from './../User';
import { Router } from '@angular/router';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

	newUser: User;

	constructor(
		private authenticationService: AuthenticationService,
		private router: Router
	) {
		this.newUser = new User();
	}

	ngOnInit() {
	}

	registerUser() {
		this.authenticationService.registerUser(this.newUser).subscribe((data) => {
			this.router.navigate(['/login']);
		});
	}
}