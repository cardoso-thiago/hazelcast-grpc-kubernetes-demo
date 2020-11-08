package br.com.cardoso.consumer.configuration

import com.hazelcast.client.config.ClientConfig
import com.hazelcast.config.Config
import com.hazelcast.config.JoinConfig
import com.hazelcast.core.Hazelcast
import com.hazelcast.topic.MessageListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HazelcastConfiguration {

    @Bean
    fun hazelcastInstanceConsumer() {
        Hazelcast.newHazelcastInstance().getTopic<String>("message").addMessageListener(MessageListener {
            System.err.println(it.messageObject)
        })
    }

    @Bean
    fun hazelcastConfig(): Config {
        val config = Config()
        val joinConfig: JoinConfig = config.networkConfig.join
        joinConfig.multicastConfig.isEnabled = false
        joinConfig.kubernetesConfig.isEnabled = true
        return config
    }

    @Bean
    fun hazelcastClientConfig(): ClientConfig {
        val config = ClientConfig()
        config.networkConfig.kubernetesConfig.isEnabled = true
        return config
    }
}