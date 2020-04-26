import psycopg2
from pyomo.environ import ConcreteModel
from pyomo.opt import SolverFactory
from pyomo.opt import SolverManagerFactory
from pyomo.environ import Set, Param, RangeSet, Var, Binary
from pyomo.environ import NonNegativeReals, Objective, ConcreteModel, minimize, Constraint, Expression

from pyomo.opt import SolverFactory
from pyomo.opt import SolverStatus, TerminationCondition


def read_database_demo():
  supply_list = [("North", "Ventilators", 50), ("North", "Doctors", 20), ("North", "Nurses", 300), ("South", "Ventilators", 50), ("South", "Doctors", 30), ("South", "Nurses", 150), ("West", "Ventilators", 21), ("West", "Doctors", 100), ("West", "Nurses", 100), ("East", "Ventilators", 9), ("East", "Doctors", 100), ("East", "Nurses", 100)]
  demand_list = [("North", "Ventilators", 5), ("North", "Doctors", 10), ("North", "Nurses", 50), ("South", "Ventilators", 100), ("South", "Doctors", 40), ("South", "Nurses", 100), ("West", "Ventilators", 20), ("West", "Doctors", 100), ("West", "Nurses", 200), ("East", "Ventilators", 5), ("East", "Doctors", 100), ("East", "Nurses", 300)]
  distance_list = [('North', 'South', 5), ('North', 'West', 100), ('North', 'East', 300), ('South', 'North', 5), ('South', 'West', 100), ('South', 'East', 300), ('West', 'North', 5), ('West', 'South', 100), ('West', 'East', 300), ('East', 'North', 5), ('East', 'South', 100), ('East', 'West', 300)]
  # infection_rate_list = [("region 1", "risk group 1", 12, 25000), ("region 1", "risk group 2", 82, 30000), ("region 2", "risk group 2", 13, 58000)]
  # resource_demand_list = [("region 1", "risk group 1", 12), ("region 1", "risk group 2", 2), ("region 2", "risk group 2", 15)]
  regions = [("North",), ("South",), ("West",), ("East",)]
  resources = [("Ventilators",), ("Doctors",), ("Nurses",)]

  return [supply_list, demand_list, distance_list, regions, resources]


def read_database():
  try:
      connection = psycopg2.connect(user = "raise",
                                    password = "",
                                    host = "localhost",
                                    port = "5432",
                                    database = "raise")

      cursor = connection.cursor()
      # Print PostgreSQL Connection properties
      print ( connection.get_dsn_parameters(),"\n")

      # Print PostgreSQL version
      cursor.execute("select region.name, resource.name, supply.amount from region, resource, supply where supply.region_id = region.id and demand.resource_id = resource.id;")
      supply_list = cursor.fetchall()

      cursor.execute("select region.name, resource.name, demand.amount from region, resource, demand where demand.region_id = region.id and demand.resource_id = resource.id;")
      demand_list = cursor.fetchall()

      cursor.execute("select region.name, risk_group.name, infection_rate.amount, infection_rate.population from region, risk_group, infection_rate where infection_rate.region_id = region.id and demand.risk_group_id = resource.id;")
      infection_rate_list = cursor.fetchall()

      cursor.execute("select resource.name, risk_group.name, resource_demand.amount from resource, risk_group, resource_demand where resource_demand.resource_id = resource.id and resource_demand.risk_group_id = risk_group.id;")
      resource_demand_list = cursor.fetchall()

      cursor.execute("select name from region;")
      regions = cursor.fetchall()

      cursor.execute("select name from resource;")
      resources = cursor.fetchall()


  except (Exception, psycopg2.Error) as error :
      print ("Error while connecting to PostgreSQL", error)
  finally:
      #closing database connection.
          if(connection):
              cursor.close()
              connection.close()
              print("PostgreSQL connection is closed")
          return [supply_list, demand_list, distance_list, regions, resources]

class Input():
  data = read_database_demo()
  # INPUTS
  supply_list = data[0]
  demand_list = data[1]
  distance_list = data[2]
  regions = data[3]
  resources = data[4]

  infection_rate_list = []
  resource_demand_list = []

  indexed_supply = {}
  for supply in supply_list:
   indexed_supply[(supply[0], supply[1])] = supply[2]

  indexed_demand = {}
  for demand in demand_list:
    indexed_demand[(demand[0], demand[1])] = demand[2]
  
  indexed_distance = {}
  for distance in distance_list:
    indexed_distance[(distance[0], distance[1])] = distance[2]

  indexed_infection_rate = {}
  indexed_population = {}

  indexed_infection_rate = {}
  indexed_population = {}

  for infection_rate in infection_rate_list:
      indexed_infection_rate[(infection_rate[0], infection_rate[1])] = infection_rate[2]
      indexed_population[(infection_rate[0], infection_rate[1])] = infection_rate[3]

  indexed_resource_demand = {}

  for resource_demand in resource_demand_list:
    indexed_resource_demand[(resource_demand[0], resource_demand[1])] = resource_demand[2]

  regions_set = set()
  for region in regions:
    regions_set.add(region[0])

  resources_set = set()
  for resource in resources:
    resources_set.add(resource[0])

class Model():
    def run(self, inputs):
        model = ConcreteModel()

        model.Locations = Set(initialize = inputs.regions_set, ordered = True)
        model.Resources = Set(initialize = inputs.resources_set, ordered = True)

        demand = self.preprocess_demand(inputs)
        availability = self.preprocess_availability(inputs)

        model.demand = Param(model.Locations, model.Resources, initialize = demand, default = 0)
        model.availability = Param(model.Locations, model.Resources, initialize = availability, default = 0)
        model.distance = Param(model.Locations, model.Locations, initialize = inputs.indexed_distance, default = 0)

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
        return inputs.indexed_demand
    
    def preprocess_availability(self, inputs):
        return inputs.indexed_supply


if __name__ == '__main__':
  data = read_database_demo()

  inputs = Input()
  
  model = Model()
  test = model.run(inputs)
