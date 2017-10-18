# Noke

Web-based note taking software. The notes can be written in Markdown.

## Building

`./gradlew build`

## Running

1. Start Cassandra
2. Execute [doc/cassandra/schema.cql](doc/cassandra/schema.cql) on the CQL console.
   This creates a keyspace and the tables
3. `./gradlew run` and open [http://localhost:8080](http://localhost:8080)

## Technical details

This project originated as a playground for the new Spring 5 Webflux and
Project Reactor stuff. The whole application is built non-blocking, by
using the Spring Data reactive support for Cassandra.

## License

[LGPLv3](https://tldrlegal.com/license/gnu-lesser-general-public-license-v3-(lgpl-3))