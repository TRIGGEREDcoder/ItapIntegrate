import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EntOneComponentsPage, EntOneDeleteDialog, EntOneUpdatePage } from './ent-one.page-object';

const expect = chai.expect;

describe('EntOne e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let entOneComponentsPage: EntOneComponentsPage;
  let entOneUpdatePage: EntOneUpdatePage;
  let entOneDeleteDialog: EntOneDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EntOnes', async () => {
    await navBarPage.goToEntity('ent-one');
    entOneComponentsPage = new EntOneComponentsPage();
    await browser.wait(ec.visibilityOf(entOneComponentsPage.title), 5000);
    expect(await entOneComponentsPage.getTitle()).to.eq('Ent Ones');
    await browser.wait(ec.or(ec.visibilityOf(entOneComponentsPage.entities), ec.visibilityOf(entOneComponentsPage.noResult)), 1000);
  });

  it('should load create EntOne page', async () => {
    await entOneComponentsPage.clickOnCreateButton();
    entOneUpdatePage = new EntOneUpdatePage();
    expect(await entOneUpdatePage.getPageTitle()).to.eq('Create or edit a Ent One');
    await entOneUpdatePage.cancel();
  });

  it('should create and save EntOnes', async () => {
    const nbButtonsBeforeCreate = await entOneComponentsPage.countDeleteButtons();

    await entOneComponentsPage.clickOnCreateButton();

    await promise.all([entOneUpdatePage.setFieldOneInput('fieldOne')]);

    expect(await entOneUpdatePage.getFieldOneInput()).to.eq('fieldOne', 'Expected FieldOne value to be equals to fieldOne');

    await entOneUpdatePage.save();
    expect(await entOneUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await entOneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last EntOne', async () => {
    const nbButtonsBeforeDelete = await entOneComponentsPage.countDeleteButtons();
    await entOneComponentsPage.clickOnLastDeleteButton();

    entOneDeleteDialog = new EntOneDeleteDialog();
    expect(await entOneDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Ent One?');
    await entOneDeleteDialog.clickOnConfirmButton();

    expect(await entOneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
