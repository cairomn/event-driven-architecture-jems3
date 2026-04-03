import { ErrorMessages } from './error-messages';

export class FormValidation {
  isValid: boolean;
  messages: Array<ErrorMessages>;

  constructor() {
    this.isValid = true;
    this.messages = new Array<ErrorMessages>();
  }

  public getMessageByField(field: string): string | null {
    let result = this.messages.filter(element => {
      if (element.field == field) {
        return element;
      }
    });

    if (result.length > 0) {
      return result[0].message;
    }

    return null;
  }

  public isFieldWithError(field: string): boolean {
    let result = this.messages.filter(element => {
      if (element.field == field) {
        return element;
      }
    });

    return result.length > 0;
  }
}