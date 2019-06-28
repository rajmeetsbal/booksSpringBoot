import { browser, by, element, ElementFinder, promise } from 'protractor';

export class LoginPage {
  // navigate to login page
  navigateToLogin() {
    return browser.get('/login');
  }
  // get current URL
  getCurrentURL() {
    return browser.getCurrentUrl();
  }
 // navigate to search page
  navigateToSearchPage() {
    return browser.get('/search');
  }
  // navigate to search page
   navigateToRecommendedPage() {
     return browser.get('/recommended');
   }
   // navigate to search page
    navigateToFavsPage() {
      return browser.get('/favourites');
    }
  // get login component
  getloginComponent(): ElementFinder {
    return element(by.tagName('app-login'));
  }
  // get username input box
  getUserNameInputBox(): ElementFinder {
    return element(by.id('userId'));
  }
  // check username input box is exist or not
  isUserNameInputBoxPresent(): promise.Promise<boolean> {
    return this.getUserNameInputBox().isPresent();
  }
  // get password input box
  getPasswordInputBox(): ElementFinder {
    return element(by.id('password'));
  }
  // check password input box is exist or not
  isPasswordInputBoxPresent(): promise.Promise<boolean> {
    return this.getPasswordInputBox().isPresent();
  }
  // get submit button
  getSubmitButton(): ElementFinder {
    return this.getloginComponent().element(by.buttonText('Login'));
  }
  // check submit button is present or not
  isSubmitButtonPresent(): promise.Promise<boolean> {
    return this.getSubmitButton().isPresent();
  }
  // click submit button
  clickSubmitButton(): promise.Promise<void> {
    return this.getSubmitButton().click();
  }
  // default values of input boxes
  getLoginInputBoxesDefaultValues(): any {
    let inputUsername, inputPassword;
    inputUsername = this.getUserNameInputBox().getAttribute('value');
    inputPassword = this.getPasswordInputBox().getAttribute('value');
    return Promise.all([inputUsername, inputPassword]).then( (values) => {
      return values;
    });
  }
  // get username and password details
  getMockLoginDetail(): any {
    const loginDetail: any = { username: 'rajmeet', password : 'rajmeet'};
    return loginDetail;
  }
  // set username and password input box values
  addLoginValues(): any {
    const login: any = this.getMockLoginDetail();
    this.getUserNameInputBox().sendKeys(login.username);
    this.getPasswordInputBox().sendKeys(login.password);
    return Object.keys(login).map(key => login[key]);
  }
  // get username and password details
  getMockSearchDetail(): any {
    const searchString: any = { srchString: 'ayn rand'};
    return searchString;
  }
  // get search input box
  // getSearchInputBox(): ElementFinder {
  //   return element(by.id('searchInput'));
  // }
  // set search input box values
  // addSearchValue(): any {
  //   const srch: any = this.getMockSearchDetail();
  //   console.log("srch"+srch);
  //   console.log("this.getSearchInputBox() "+this.getSearchInputBox());
  //   this.getSearchInputBox().sendKeys(srch.srchString);
  //   return Object.keys(srch).map(key => srch[key]);
  // }

}
