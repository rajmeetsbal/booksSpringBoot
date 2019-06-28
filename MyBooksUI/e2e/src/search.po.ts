import { browser, by, element, ElementFinder, promise } from 'protractor';

export class SearchPage {
  // navigate to search page
  

  // get search input box
  getSearchInputBox(): ElementFinder {
    return element(by.id('searchInput'));
  }
  // get mat list item
  getMatListItemComponent(): ElementFinder {
    return element(by.tagName('mat-list-item'));
  }
  // navigate to  search page
   navigateToSearchPage() {
     return browser.get('/search');
   }
   // get username and password details
   getMockSearchDetail(): any {
     const searchString: any = { srchString: 'rajmeet'};
     return searchString;
   }
   // set search input box values
   addSearchValue(): any {
     const srch: any = this.getMockSearchDetail();
    //  searchBox.sendKeys(srch.srchString);
     this.getSearchInputBox().sendKeys(srch.srchString);
     return Object.keys(srch).map(key => srch[key]);
   }
   // default values of input boxes
  getSearchInputBoxesDefaultValues(): any {
    let inputString;
    inputString = this.getSearchInputBox().getAttribute('value');
    return Promise.all([inputString]).then( (values) => {
      return values;
    });
  }
  // get login component
  getSearchComponent(): ElementFinder {
    return element(by.tagName('app-search'));
  }
  // get login component
  getListItemComponent(): ElementFinder {
    return element(by.tagName('app-book-list-item'));
  }

  
  // get submit button
  getFavButton(): ElementFinder {
    return this.getListItemComponent().element(by.buttonText('Add to favourites'));
  }
  // click submit button
  clickfavButtonButton(): promise.Promise<void> {
    return this.getFavButton().click();
  }

}
