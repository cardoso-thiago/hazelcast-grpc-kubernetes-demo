package br.com.cardoso.publisher.controller

import br.com.cardoso.publisher.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(val messageService: MessageService) {

    @GetMapping("/message/{message}")
    fun sendMessage(@PathVariable message: String): ResponseEntity<Any> {
        messageService.sendMessage(message)
        return ResponseEntity.ok().build()
    }
}