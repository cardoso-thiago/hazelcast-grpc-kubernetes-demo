package br.com.cardoso.publisher.service

import com.hazelcast.core.HazelcastInstance
import org.springframework.stereotype.Service

@Service
class MessageService(val hazelcastInstancePublisher: HazelcastInstance) {

    fun sendMessage(message: String) = hazelcastInstancePublisher.getTopic<String>("message").publish(message)
}