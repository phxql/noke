package de.mkammerer.noke.business

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

interface UUIDFactory {
    fun create(): Mono<UUID>
}

@Component
class UUIDFactoryImpl : UUIDFactory {
    override fun create(): Mono<UUID> = Mono.create { sink -> sink.success(UUID.randomUUID()) }
}