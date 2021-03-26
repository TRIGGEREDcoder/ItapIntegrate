import { element, by, ElementFinder } from 'protractor';

export class EntOneComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('cg-ent-one div table .btn-danger'));
  title = element.all(by.css('cg-ent-one div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getText();
  }
}

export class EntOneUpdatePage {
  pageTitle = element(by.id('cg-ent-one-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  fieldOneInput = element(by.id('field_fieldOne'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setFieldOneInput(fieldOne: string): Promise<void> {
    await this.fieldOneInput.sendKeys(fieldOne);
  }

  async getFieldOneInput(): Promise<string> {
    return await this.fieldOneInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class EntOneDeleteDialog {
  private dialogTitle = element(by.id('cg-delete-entOne-heading'));
  private confirmButton = element(by.id('cg-confirm-delete-entOne'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
