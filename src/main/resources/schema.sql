CREATE TABLE IF NOT EXISTS user_dictionary (
  id          SERIAL PRIMARY KEY,
  user_name   VARCHAR NOT NULL,
  first_name  VARCHAR NOT NULL
  );