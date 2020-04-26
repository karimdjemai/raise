CREATE TABLE "run" (
  "id" serial PRIMARY KEY,
  "name" text,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "resource" (
  "id" serial PRIMARY KEY,
  "name" text,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "demand" (
  "id" serial PRIMARY KEY,
  "run_id" integer,
  "resource_id" integer,
  "value" numeric,
  "region_id" integer,
  "risk_group_id" integer,
  "loc" text,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "supply" (
  "id" serial PRIMARY KEY,
  "run_id" integer,
  "resource_id" integer,
  "value" numeric,
  "region_id" integer,
  "loc" text,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "region" (
  "id" serial PRIMARY KEY,
  "name" text,
  "loc" text
);

CREATE TABLE "allocation" (
  "id" serial PRIMARY KEY,
  "run_id" integer,
  "from_id" integer,
  "to_id" integer,
  "resource_id" integer,
  "value" numeric,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "risk_group" (
  "id" serial PRIMARY KEY,
  "name" text,
  "run_id" integer,
  "category" integer,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "infection_rate" (
  "id" serial PRIMARY KEY,
  "value" numeric,
  "run_id" integer,
  "population" integer,
  "region_id" integer,
  "risk_group_id" integer,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "resource_demand" (
  "id" serial PRIMARY KEY,
  "value" numeric,
  "run_id" integer,
  "resource_id" integer,
  "risk_group_id" integer,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "odmatrix" (
  "id" serial PRIMARY KEY,
  "run_id" integer,
  "distance" numeric,
  "from_id" integer,
  "to_id" integer,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "factor" (
  "id" serial PRIMARY KEY,
  "value" numeric,
  "run_id" integer,
  "region_id" integer,
  "created_at" timestamp,
  "updated_at" timestamp
);

CREATE TABLE "updated_supply" (
  "id" serial PRIMARY KEY,
  "run_id" integer,
  "resource_id" integer,
  "value" numeric,
  "loc" text,
  "region_id" integer,
  "created_at" timestamp,
  "updated_at" timestamp
);

ALTER TABLE "demand" ADD FOREIGN KEY ("region_id") REFERENCES "region" ("id");

ALTER TABLE "supply" ADD FOREIGN KEY ("region_id") REFERENCES "region" ("id");

ALTER TABLE "supply" ADD FOREIGN KEY ("resource_id") REFERENCES "resource" ("id");

ALTER TABLE "demand" ADD FOREIGN KEY ("resource_id") REFERENCES "resource" ("id");

ALTER TABLE "supply" ADD FOREIGN KEY ("run_id") REFERENCES "run" ("id");

ALTER TABLE "allocation" ADD FOREIGN KEY ("run_id") REFERENCES "run" ("id");

ALTER TABLE "demand" ADD FOREIGN KEY ("run_id") REFERENCES "run" ("id");

ALTER TABLE "resource_demand" ADD FOREIGN KEY ("risk_group_id") REFERENCES "risk_group" ("id");

ALTER TABLE "infection_rate" ADD FOREIGN KEY ("risk_group_id") REFERENCES "risk_group" ("id");

ALTER TABLE "infection_rate" ADD FOREIGN KEY ("region_id") REFERENCES "region" ("id");

ALTER TABLE "resource_demand" ADD FOREIGN KEY ("resource_id") REFERENCES "resource" ("id");

ALTER TABLE "updated_supply" ADD FOREIGN KEY ("run_id") REFERENCES "run" ("id");

ALTER TABLE "updated_supply" ADD FOREIGN KEY ("resource_id") REFERENCES "resource" ("id");

ALTER TABLE "updated_supply" ADD FOREIGN KEY ("region_id") REFERENCES "region" ("id");

ALTER TABLE "odmatrix" ADD FOREIGN KEY ("from_id") REFERENCES "region" ("id");

ALTER TABLE "odmatrix" ADD FOREIGN KEY ("to_id") REFERENCES "region" ("id");

ALTER TABLE "factor" ADD FOREIGN KEY ("region_id") REFERENCES "region" ("id");

ALTER TABLE "odmatrix" ADD FOREIGN KEY ("run_id") REFERENCES "run" ("id");

ALTER TABLE "factor" ADD FOREIGN KEY ("run_id") REFERENCES "run" ("id");

ALTER TABLE "risk_group" ADD FOREIGN KEY ("run_id") REFERENCES "run" ("id");

ALTER TABLE "infection_rate" ADD FOREIGN KEY ("run_id") REFERENCES "run" ("id");

ALTER TABLE "resource_demand" ADD FOREIGN KEY ("run_id") REFERENCES "run" ("id");
