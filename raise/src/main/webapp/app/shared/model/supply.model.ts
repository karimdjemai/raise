import { IRunInstance } from '@/shared/model/run-instance.model';
import { IResource } from '@/shared/model/resource.model';
import { IRegion } from '@/shared/model/region.model';

export interface ISupply {
  id?: number;
  amount?: number;
  geometry?: string;
  runInstance?: IRunInstance;
  resource?: IResource;
  region?: IRegion;
}

export class Supply implements ISupply {
  constructor(
    public id?: number,
    public amount?: number,
    public geometry?: string,
    public runInstance?: IRunInstance,
    public resource?: IResource,
    public region?: IRegion
  ) {}
}
