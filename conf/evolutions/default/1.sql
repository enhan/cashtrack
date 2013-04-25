
# --- !Ups

CREATE SEQUENCE account_id_seq;
CREATE TABLE account(
 id integer NOT NULL default nextval('account_id_seq'),
 name varchar(255),
 initialBalance integer,
 PRIMARY KEY(id)
);

# --- !Downs
DROP TABLE account;
DROP SEQUENCE account_id_seq;

