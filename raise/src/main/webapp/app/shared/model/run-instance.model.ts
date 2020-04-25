export interface IRunInstance {
  id?: number;
  name?: string;
}

export class RunInstance implements IRunInstance {
  constructor(public id?: number, public name?: string) {}
}
