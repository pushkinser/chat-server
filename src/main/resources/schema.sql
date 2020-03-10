DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS public.user_dictionary (
  id          SERIAL PRIMARY KEY,
  user_name   VARCHAR NOT NULL,
  first_name  VARCHAR NOT NULL
  );
CREATE TABLE IF NOT EXISTS public.chat (
  id    SERIAL PRIMARY KEY,
  chat_name  VARCHAR NOT NULL
 );
CREATE TABLE IF NOT EXISTS public.chat_to_user (
 id_user int not null,
 id_chat int not null,
 CONSTRAINT "FK_id_user" FOREIGN KEY ("id_user")
    REFERENCES "user_dictionary" ("id"),
 CONSTRAINT "FK_id_chat" FOREIGN KEY ("id_chat")
    REFERENCES "chat" ("id")
 );
CREATE TABLE IF NOT EXISTS public.message (
 id SERIAL PRIMARY KEY,
 id_chat int NOT NULL,
 id_user int NOT NULL,
 message VARCHAR NOT NULL,
 CONSTRAINT "FK_id_user" FOREIGN KEY ("id_user")
     REFERENCES "user_dictionary" ("id"),
  CONSTRAINT "FK_id_chat" FOREIGN KEY ("id_chat")
     REFERENCES "chat" ("id")
);