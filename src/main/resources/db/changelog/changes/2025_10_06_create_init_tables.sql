CREATE TABLE trackerr.habits(
    id SERIAL PRIMARY KEY,
    add_habit timestamp(6),
    duration double precision,
    meta varchar(255),
    name_habit varchar(255),
    update_habit timestamp(6)
);

CREATE TABLE trackerr.cities(
    id SERIAL PRIMARY KEY,
    code varchar(255),
    name varchar(255)
);

CREATE TABLE trackerr.users(
  id SERIAL PRIMARY KEY,
  age int,
  created_at timestamp(6),
  full_name varchar(255),
  name varchar(255),
  updated_at timestamp(6)
);
