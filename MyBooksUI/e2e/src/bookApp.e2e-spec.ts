import { LoginPage } from './login.po';
import { SearchPage } from './search.po';
import { RecommendedPage } from './recommend.po';

describe('login page', () => {
  let page: LoginPage;
  let searchPage: SearchPage;
  let recommendPage: RecommendedPage;


  beforeEach(() => {
    page = new LoginPage();
    searchPage = new SearchPage();
    recommendPage = new RecommendedPage();
  });

  beforeEach(() => {
    page = new LoginPage();
  });

  it('should get username input box', () => {
    page.navigateToLogin();
    expect(page.isUserNameInputBoxPresent())
    .toBeTruthy(`<input class="username" matInput [formControl]='username'> should exist in login.component.html`);
  });

  it('should get passsword input box', () => {
    page.navigateToLogin();
    expect(page.isPasswordInputBoxPresent())
    .toBeTruthy(`<input class="password" matInput type = 'password' [formControl]='password'>
      should exist in login.component.html`);
  });

  it('should get submit button', () => {
    page.navigateToLogin();
    expect(page.isSubmitButtonPresent()).toBeTruthy(`<button type="submit" mat-button>Submit</button> should
      exist in login.component.html`);
  });

  it('default values of username and password should be empty', () => {
    const emptyLoginValues = ['', ''];
    page.navigateToLogin();
    expect(page.getLoginInputBoxesDefaultValues()).toEqual(emptyLoginValues, 'Default values for username and password should be empty');
  });

  it('should login into the system', () => {
    page.navigateToLogin();
    let newLoginValues = page.addLoginValues();
    expect(page.getLoginInputBoxesDefaultValues()).toEqual(newLoginValues, 'Should be able to set values for username and password');
    page.clickSubmitButton();
    page.navigateToSearchPage();
    page.getCurrentURL().then((url) => {
      if (url.indexOf('login') > -1) {
        newLoginValues = page.addLoginValues();
        page.clickSubmitButton();
        page.navigateToSearchPage();
        expect(searchPage.getSearchInputBox())
          .toBeTruthy(`input control for search should exist in login.component.html`);
      } else {
        expect(searchPage.getSearchInputBox())
          .toBeTruthy(`input control for search should exist in login.component.html`);
      }
      
      console.log("b4 searchPage.addSearchValue();");
      // searchPage.addSearchValue(searchPage.getSearchInputBox());

    });
  });

  it('should be able to search books after successful login', () => {
    page.navigateToLogin();
    let newLoginValues = page.addLoginValues();
    expect(page.getLoginInputBoxesDefaultValues()).toEqual(newLoginValues, 'Should be able to set values for username and password');
    page.clickSubmitButton();
    page.navigateToSearchPage();
    page.getCurrentURL().then((url) => {
      if (url.indexOf('login') > -1) {
        newLoginValues = page.addLoginValues();
        page.clickSubmitButton();
        page.navigateToSearchPage();
        expect(searchPage.getSearchInputBox())
          .toBeTruthy(`input control for search should exist in login.component.html`);
      } else {
        expect(searchPage.getSearchInputBox())
          .toBeTruthy(`input control for search should exist in login.component.html`);
      }

      // console.log("b4 searchPage.addSearchValue();");
      // searchPage.addSearchValue(searchPage.getSearchInputBox());

    });
  });



  it('recommended books are displayed', () => {
    page.navigateToRecommendedPage();
    expect(recommendPage.getMatList())
          .toBeTruthy(`input control for search should exist in login.component.html`);
          
  });

  it('favourite books are displayed', () => {
    page.navigateToFavsPage();
    expect(recommendPage.getMatList())
          .toBeTruthy(`input control for search should exist in login.component.html`);
          
  });

});
