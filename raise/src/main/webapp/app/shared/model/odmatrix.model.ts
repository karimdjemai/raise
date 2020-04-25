import { IRegion } from '@/shared/model/region.model';

export interface IOdmatrix {
  id?: number;
  cost?: number;
  fromRegion?: IRegion;
  toRegion?: IRegion;
}

export class Odmatrix implements IOdmatrix {
  constructor(public id?: number, public cost?: number, public fromRegion?: IRegion, public toRegion?: IRegion) {}
}
