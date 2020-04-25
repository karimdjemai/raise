import { IRunInstance } from '@/shared/model/run-instance.model';
import { IResource } from '@/shared/model/resource.model';
import { IRegion } from '@/shared/model/region.model';
import { IRiskGroup } from '@/shared/model/risk-group.model';

export interface IAllocation {
  id?: number;
  amount?: number;
  cost?: number;
  runInstance?: IRunInstance;
  resource?: IResource;
  region?: IRegion;
  riskGroup?: IRiskGroup;
}

export class Allocation implements IAllocation {
  constructor(
    public id?: number,
    public amount?: number,
    public cost?: number,
    public runInstance?: IRunInstance,
    public resource?: IResource,
    public region?: IRegion,
    public riskGroup?: IRiskGroup
  ) {}
}
