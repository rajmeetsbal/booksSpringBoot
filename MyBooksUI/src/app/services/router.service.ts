import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn:'root'
})
export class RouterService {

  constructor(private router : Router) { }

  routeToLogin(){
    this.router.navigate(['login']);
  }

  routeToDashboard(){
    this.router.navigate(['dashboard']);
  }

  routeToEditView(noteId : number){
    console.log('noteId inside Router',noteId);
    
    this.router.navigate(['dashboard',{
      outlets:{
        noteEditOutlet : ['note',noteId,'edit']
      }
    }]);
  }
}
