export interface IResource {
  id?: number;
  name?: string;
}

export class Resource implements IResource {
  constructor(public id?: number, public name?: string) {}
}
