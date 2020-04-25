export interface IInfectionRate {
  id?: number;
  amount?: number;
  population?: number;
}

export class InfectionRate implements IInfectionRate {
  constructor(public id?: number, public amount?: number, public population?: number) {}
}
