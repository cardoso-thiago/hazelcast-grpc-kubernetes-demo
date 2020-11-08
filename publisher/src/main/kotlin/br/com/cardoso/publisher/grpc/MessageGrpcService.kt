package br.com.cardoso.publisher.grpc

import br.com.cardoso.grpc.MessageProtoServiceGrpc.MessageProtoServiceImplBase
import br.com.cardoso.grpc.MessageRequest
import br.com.cardoso.grpc.MessageVoid
import br.com.cardoso.publisher.service.MessageService
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Service

@Service
class MessageGrpcService(private val messageService: MessageService) : MessageProtoServiceImplBase() {
    override fun sendMessage(request: MessageRequest, responseObserver: StreamObserver<MessageVoid>) {
        messageService.sendMessage(request.message)
        responseObserver.onNext(MessageVoid.getDefaultInstance())
        responseObserver.onCompleted()
    }
}