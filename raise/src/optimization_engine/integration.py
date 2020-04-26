import psycopg2
supply_list = [("region 1", "resource 1", 10), ("region 1", "resource 2", 5), ("region 2", "resource 2", 18)]
demand_list = [("region 1", "resource 1", 15), ("region 1", "resource 2", 30), ("region 2", "resource 2", 20)]
infection_rate_list = [("region 1", "risk group 1", 12, 25000), ("region 1", "risk group 2", 82, 30000), ("region 2", "risk group 2", 13, 58000)]
resource_demand_list = [("region 1", "risk group 1", 12), ("region 1", "risk group 2", 2), ("region 2", "risk group 2", 15)]
regions = [("region 1",), ("region 2",), ("region 3",)]
resources = [("resource 1",), ("resource 2",), ("resource 3",)]
def read_database():
  try:
      connection = psycopg2.connect(user = "postgres",
                                    password = "",
                                    host = "localhost",
                                    port = "5432",
                                    database = "data")

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

class Input:
  # INPUTS
  indexed_supply = {}
  for supply in supply_list:
   indexed_supply[(supply[0], supply[1])] = supply[2]

  indexed_demand = {}
  for demand in demand_list:
    indexed_demand[(demand[0], demand[1])] = demand[2]

  indexed_infection_rate = {}
  indexed_population = {}

  for infection_rate in infection_rate_list:
      indexed_infection_rate[(infection_rate[0], infection_rate[1])] = infection_rate[2]
      indexed_population[(infection_rate[0], infection_rate[1])] = infection_rate[3]

  indexed_resource_demand = {}

  for resource_demand in resource_demand_list:
    indexed_resource_demand[(resource_demand[0], resource_demand[1])] = resource_demand[2]

  def get_regions(self):
    regions_set = set()
    for region in regions:
      regions_set.add(region[0])
    return regions_set

  def get_resources(self):
    resources_set = set()
    for resource in resources:
      resources_set.add(resource[0])
    return resources_set

# specify if demand is provided or not
new = Input()
print(new.indexed_demand)
print(new.indexed_supply)
print(new.indexed_infection_rate)
print(new.indexed_population)
print(new.indexed_resource_demand)
print(new.get_regions())
print(new.get_resources())
