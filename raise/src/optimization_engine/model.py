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

        demand = self.preprocess_demand()
        availability = self.preprocess_availability()

        model.demand = Param(model.Locations, model.Resources, initialize = demand, default = 0)
        model.availability = Param(model.Locations, model.Resources, initialize = availability, default = 0)
        model.distance = Param(model.Locations, model.Locations, initialize = inputs.distance_matrix)

        model.x = Var(model.Locations, model.Locations, model.Resources, domain = NonNegativeReals)

        def _obj_rule(model):
            return sum(model.x[i, j, r]*model.distance[i, j] for i in model.Locations for j in model.Locations for r in model.Resources)
        model.obj = Objective(rule=_obj_rule, sense=minimize)

        def _supply_balance(model, i, r):
            return sum(model.x[i, j, r] for j in model.Locations) == model.availability[i, r]
        model.supply_balance_constraint = Constraint(model.Locations, model.Resource, rule = _supply_balance)

        def _demand_balance(model, j, r):
            return sum(model.x[i, j, r] for i in model.Locations) == model.demand[j, r]
        model.supply_balance_constraint = Constraint(model.Locations, model.Resource, rule = _demand_balance)

        opt = SolverFactory('glpk')
        results = opt.solve(model, tee=True)
        results.write()

    def preprocess_demand(self, inputs):
        1
    
    def preprocess_availability(self, inputs):
        1
