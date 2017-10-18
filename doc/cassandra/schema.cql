CREATE KEYSPACE noke WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE note (id uuid, title text, markdown text, html text, PRIMARY KEY (id));