import { TestBed } from '@angular/core/testing';

import { BookService } from './bookService';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';


xdescribe('BookService', () => {

  let bookService : BookService;
  let httpTestingController: HttpTestingController;
  let testURL = 'http://localhost:8765/book-favourites/api/v1/favourites/rajmeet';
  let testData = {
        "author_name": [
          "Erin Moure"
        ],
        "id": 9781550650006,
        "title": "West South West"
      };
  beforeEach(() => 
  {
  TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers:[BookService]

  })
  });

  beforeEach(()=>{
    bookService = TestBed.get(BookService);
    httpTestingController = TestBed.get(HttpTestingController);
  });
  afterEach(() =>{
    httpTestingController.verify();
  })

  it('should be created', () => {
    
    expect(bookService).toBeTruthy();
  });

  it('#getFavouriteBooks() should get the proper response from http get request',()=>{
    // bookService.getFavouriteBooks('rajmeet').subscribe(res =>{
    //   expect(res).toBe(testData);
    // });
   const httpMockReq =  httpTestingController.expectOne(testURL);
   expect(httpMockReq.request.method).toBe('GET','Request should be get Request');
    httpMockReq.flush(testData);
  })
});
