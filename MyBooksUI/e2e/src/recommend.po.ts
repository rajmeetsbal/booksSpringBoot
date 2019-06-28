import { browser, by, element, ElementFinder, promise } from 'protractor';

export class RecommendedPage {
 // navigate to search page
  navigateToSearchPage() {
    return browser.get('/navigateToRecommendedPage');
  }
  // get login component
  getMatList(): ElementFinder {
    return element(by.tagName('mat-list'));
  }

}
