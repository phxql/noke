package de.mkammerer.noke.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.CassandraCqlClusterFactoryBean
import org.springframework.data.cassandra.config.CassandraCqlSessionFactoryBean
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories


@Configuration
@EnableCassandraRepositories(basePackages = arrayOf("de.mkammerer.noke.storage.cassandra"))
class CassandraConfig {
    @Bean
    fun cluster(): CassandraCqlClusterFactoryBean {
        val cluster = CassandraCqlClusterFactoryBean()
        cluster.setContactPoints("localhost")
        return cluster
    }

    @Bean
    fun session(): CassandraCqlSessionFactoryBean {
        val session = CassandraCqlSessionFactoryBean()
        session.setCluster(cluster().`object`)
        session.setKeyspaceName("noke")
        return session
    }
}