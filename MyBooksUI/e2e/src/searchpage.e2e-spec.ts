import { SearchPage } from './search.po';

describe('search page', () => {
  let searchPage: SearchPage;

  beforeEach(() => {
    searchPage = new SearchPage();
  });

  it('search page should open', () => {
    searchPage.navigateToSearchPage();
    expect(searchPage.getSearchInputBox())
          .toBeTruthy(`input control for search should exist in login.component.html`);
  });

  // it('xshould login into the system', () => {
  //   searchPage.navigateToSearchPage();
  //   let newLoginValues = searchPage.addSearchValue();
  //   expect(searchPage.getSearchInputBoxesDefaultValues()).toEqual(newLoginValues, 'Should be able to set values for search string');
    // page.clickSubmitButton();
    // page.navigateToSearchPage();
    // page.getCurrentURL().then((url) => {
    //   if (url.indexOf('login') > -1) {
    //     newLoginValues = page.addLoginValues();
    //     page.clickSubmitButton();
    //     page.navigateToSearchPage();
    //     expect(searchPage.getSearchInputBox())
    //       .toBeTruthy(`input control for search should exist in login.component.html`);
    //   } else {
    //     expect(searchPage.getSearchInputBox())
    //       .toBeTruthy(`input control for search should exist in login.component.html`);
    //   }
    // });
  // });
});
