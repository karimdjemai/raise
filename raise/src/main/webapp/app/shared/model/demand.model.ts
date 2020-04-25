import { IRunInstance } from '@/shared/model/run-instance.model';
import { IResource } from '@/shared/model/resource.model';
import { IRegion } from '@/shared/model/region.model';
import { IRiskGroup } from '@/shared/model/risk-group.model';

export interface IDemand {
  id?: number;
  amount?: number;
  geometry?: string;
  runInstance?: IRunInstance;
  resource?: IResource;
  region?: IRegion;
  riskGroup?: IRiskGroup;
}

export class Demand implements IDemand {
  constructor(
    public id?: number,
    public amount?: number,
    public geometry?: string,
    public runInstance?: IRunInstance,
    public resource?: IResource,
    public region?: IRegion,
    public riskGroup?: IRiskGroup
  ) {}
}
