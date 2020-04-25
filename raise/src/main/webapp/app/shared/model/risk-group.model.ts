export interface IRiskGroup {
  id?: number;
  name?: string;
  category?: number;
}

export class RiskGroup implements IRiskGroup {
  constructor(public id?: number, public name?: string, public category?: number) {}
}
