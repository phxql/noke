package de.mkammerer.noke.storage.cassandra

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface ReactiveCassandraNoteRepository : ReactiveCrudRepository<NoteEntity, UUID>