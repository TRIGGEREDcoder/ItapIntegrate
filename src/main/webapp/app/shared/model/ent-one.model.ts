export interface IEntOne {
  id?: number;
  fieldOne?: string;
}

export class EntOne implements IEntOne {
  constructor(public id?: number, public fieldOne?: string) {}
}
