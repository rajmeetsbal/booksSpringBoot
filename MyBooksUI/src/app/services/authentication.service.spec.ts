import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


xdescribe('NoteService', () => {

  let authenticationService : AuthenticationService;
  let httpTestingController: HttpTestingController;
  let testURL = 'http://localhost:8765/userservice/api/v1/user';
  let testData = [
    {  
        "email": "rajmeet.@bal.com",
        "name": "Rajmeet",
        "password": "rajmeet",
        "userId": "rajmeet"
    }
  ];
  beforeEach(() => 
  {
  TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers:[AuthenticationService]

  })
  });

  beforeEach(()=>{
    authenticationService = TestBed.get(AuthenticationService);
    httpTestingController = TestBed.get(HttpTestingController);
  });
  afterEach(() =>{
    httpTestingController.verify();
  })

  it('should be created', () => {
    
    expect(authenticationService).toBeTruthy();
  });

  it('#getNotes() should get the proper response from http get request',()=>{
    // authenticationService.getToken().subscribe(res =>{
    //   expect(res).toBe(testData)
    // })
   const httpMockReq =  httpTestingController.expectOne(testURL);
   expect(httpMockReq.request.method).toBe('GET','Request should be get Request');
    httpMockReq.flush(testData);
  })
});
