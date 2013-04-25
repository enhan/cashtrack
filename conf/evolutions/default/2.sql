
# --- !Ups

CREATE SEQUENCE operation_id_seq;
CREATE TABLE operation(
 id integer NOT NULL default nextval('operation_id_seq'),
 label varchar(255),
 diff integer,
 account integer not null,
 PRIMARY KEY(id),
 FOREIGN KEY(account) REFERENCES account
);

# --- !Downs
DROP TABLE operation;
DROP SEQUENCE operation_id_seq;

