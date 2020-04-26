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

        model.Locations = Set(initialize = inputs.locations, ordered = True)
        model.Resources = Set(initialize = inputs.resources, ordered = True)

        demand = self.preprocess_demand(inputs)
        availability = self.preprocess_availability(inputs)
        shortage = self.calculate_shortage(inputs.resources, inputs.demand, inputs.availabilities)
        demand, availability = self.distribute_shortage(inputs.factor, demand, availabilities, shortage)

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

    def calculate_shortage(self, resources, demands, availabilities):
        shortage = dict()

        for i in demands:
            for r in resources:
                if i[1] == r:
                    if r not in shortage:
                        shortage[r] = 0

                    shortage[r] += demands[i] - availabilities[i]

        return shortage
    
    def distribute_shortage(self, factor, demand, availabilities, shortage):
        for r in shortage:
            if shortage[r] > 0:
                for i in demand:
                    if i[1] == r:
                        demand[i] = round(demand[i] - shortage[r] * factor[i[0]])
            elif shortage[r] < 0:
                for i in availabilities:
                    if i[1] == r:
                        demand[i] = round(demand[i] * shortage[r] * (1 - factor[i[0]]))
            else:
                continue

        total_demand = dict()
        total_availability = dict()

        for i in demand:
            if i[1] not in total_demand:
                total_demand[i[1]] = 0
            
            total_demand[i[1]] += demand[i]

        for i in availabilities:
            if i[1] not in total_availability:
                total_availability[i[1]] = 0
            
            total_availability[i[1]] += availabilities[i]
                
        return (demand, availabilities)
                        
    def preprocess_demand(self, inputs):
        return inputs.demand
    
    def preprocess_availability(self, inputs):
        return inputs.availabilities

if __name__ == '__main__':
    locations = set(['North', 'South', 'West', 'East'])
    resources = set(['Ventilators', 'Doctors', 'Nurses'])

    demand = dict()
    demand[('North', 'Ventilators')] = 20
    demand[('North', 'Doctors')] = 10
    demand[('North', 'Nurses')] = 50
    demand[('South', 'Ventilators')] = 70
    demand[('South', 'Doctors')] = 40
    demand[('South', 'Nurses')] = 100
    demand[('West', 'Ventilators')] = 30
    demand[('West', 'Doctors')] = 100
    demand[('West', 'Nurses')] = 200
    demand[('East', 'Ventilators')] = 40
    demand[('East', 'Doctors')] = 100
    demand[('East', 'Nurses')] = 300

    availabilities = dict()
    availabilities[('North', 'Ventilators')] = 50
    availabilities[('North', 'Doctors')] = 20
    availabilities[('North', 'Nurses')] = 300
    availabilities[('South', 'Ventilators')] = 50
    availabilities[('South', 'Doctors')] = 30
    availabilities[('South', 'Nurses')] = 150
    availabilities[('West', 'Ventilators')] = 21
    availabilities[('West', 'Doctors')] = 100
    availabilities[('West', 'Nurses')] = 100
    availabilities[('East', 'Ventilators')] = 9
    availabilities[('East', 'Doctors')] = 100
    availabilities[('East', 'Nurses')] = 100

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

    factor = dict()
    factor['North'] = 0.22
    factor['South'] = 0.38
    factor['East'] = 0.3
    factor['West'] = 0.1

    class Inputs():
        locations = set()
        resources = set()
        demand = dict()
        availabilities = dict()
        distance = dict()
        factor = dict()

    inputs = Inputs()
    inputs.locations = locations
    inputs.resources = resources
    inputs.demand = demand
    inputs.availabilities = availabilities
    inputs.distance = distance
    inputs.factor = factor

    model = Model()
    model.run(inputs)