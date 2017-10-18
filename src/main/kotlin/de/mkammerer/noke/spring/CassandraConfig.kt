package de.mkammerer.noke.spring

import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories


@Configuration
@EnableCassandraRepositories(basePackages = arrayOf("de.mkammerer.noke.storage.cassandra"))
class CassandraConfig