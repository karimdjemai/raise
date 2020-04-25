export interface IRegion {
  id?: number;
  name?: string;
  geometry?: string;
}

export class Region implements IRegion {
  constructor(public id?: number, public name?: string, public geometry?: string) {}
}
