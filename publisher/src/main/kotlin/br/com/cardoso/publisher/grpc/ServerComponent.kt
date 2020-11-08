package br.com.cardoso.publisher.grpc

import io.grpc.Server
import io.grpc.ServerBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class ServerComponent(val messageGrpcService: MessageGrpcService) {

    val logger: Logger = LoggerFactory.getLogger(ServerComponent::class.java)

    @PostConstruct
    fun startGrpcServer() {
        val port = 9090
        logger.info("Will try to start gRPC server on port $port")
        val server: Server = ServerBuilder.forPort(port).addService(messageGrpcService).build()
        server.start()
        logger.info("gRPC server started on port $port")
        GlobalScope.launch {
            server.awaitTermination()
        }
    }
}