from pyomo.environ import ConcreteModel
from pyomo.opt import SolverFactory
from pyomo.opt import SolverManagerFactory
from pyomo.environ import Set, Param, RangeSet, Var, Binary
from pyomo.environ import NonNegativeReals, Objective, ConcreteModel, minimize, Constraint, Expression

from pyomo.opt import SolverFactory
from pyomo.opt import SolverStatus, TerminationCondition

class Model():
    def run(self, inputs):
        model = ConcreteModel()

        model.Locations = Set(initialize = inputs.Locations, ordered = True)
        model.Resources = Set(initialize = inputs.Resources, ordered = True)

        demand = self.preprocess_demand(inputs)
        availability = self.preprocess_availability(inputs)

        model.demand = Param(model.Locations, model.Resources, initialize = demand, default = 0)
        model.availability = Param(model.Locations, model.Resources, initialize = availability, default = 0)
        model.distance = Param(model.Locations, model.Locations, initialize = inputs.distance, default = 0)

        model.x = Var(model.Locations, model.Locations, model.Resources, domain = NonNegativeReals)

        def _obj_rule(model):
            return sum(model.x[i, j, r]*model.distance[i, j] for i in model.Locations for j in model.Locations for r in model.Resources)
        model.obj = Objective(rule=_obj_rule, sense=minimize)

        def _supply_balance(model, i, r):
            return sum(model.x[i, j, r] for j in model.Locations) == model.availability[i, r]
        model.supply_balance_constraint = Constraint(model.Locations, model.Resources, rule = _supply_balance)

        def _demand_balance(model, j, r):
            return sum(model.x[i, j, r] for i in model.Locations) == model.demand[j, r]
        model.demand_balance_constraint = Constraint(model.Locations, model.Resources, rule = _demand_balance)

        opt = SolverFactory('glpk')
        results = opt.solve(model, tee=True)
        results.write()

    def preprocess_demand(self, inputs):
        return inputs.demand
    
    def preprocess_availability(self, inputs):
        return inputs.availabilities

if __name__ == '__main__':
    Locations = set(['North', 'South', 'West', 'East'])
    Resources = set(['Ventilators', 'Doctors', 'Nurses'])

    demand = dict()
    demand[('North', 'Ventilators')] = 5
    demand[('North', 'Doctors')] = 10
    demand[('North', 'Nurses')] = 50
    demand[('South', 'Ventilators')] = 100
    demand[('South', 'Doctors')] = 40
    demand[('South', 'Nurses')] = 100
    demand[('West', 'Ventilators')] = 20
    demand[('West', 'Doctors')] = 100
    demand[('West', 'Nurses')] = 200
    demand[('East', 'Ventilators')] = 5
    demand[('East', 'Doctors')] = 100
    demand[('East', 'Nurses')] = 300

    availabilities = dict()
    availabilities[('North', 'Ventilators')] = 50
    availabilities[('North', 'Doctors')] = 30
    availabilities[('North', 'Nurses')] = 300
    availabilities[('South', 'Ventilators')] = 50
    availabilities[('South', 'Doctors')] = 30
    availabilities[('South', 'Nurses')] = 120
    availabilities[('West', 'Ventilators')] = 21
    availabilities[('West', 'Doctors')] = 100
    availabilities[('West', 'Nurses')] = 200
    availabilities[('East', 'Ventilators')] = 22
    availabilities[('East', 'Doctors')] = 100
    availabilities[('East', 'Nurses')] = 350

    distance = dict()
    distance[('North', 'South')] = 5
    distance[('North', 'West')] = 100
    distance[('North', 'East')] = 300
    distance[('South', 'North')] = 5
    distance[('South', 'West')] = 100
    distance[('South', 'East')] = 300
    distance[('West', 'North')] = 5
    distance[('West', 'South')] = 100
    distance[('West', 'East')] = 300
    distance[('East', 'North')] = 5
    distance[('East', 'South')] = 100
    distance[('East', 'West')] = 300

    class Inputs():
        Locations = set()
        Resources = set()
        demand = dict()
        availabilities = dict()
        distance = dict()

    inputs = Inputs()
    inputs.Locations = Locations
    inputs.Resources = Resources
    inputs.demand = demand
    inputs.availabilities = availabilities
    inputs.distance = distance

    model = Model()
    model.run(inputs)