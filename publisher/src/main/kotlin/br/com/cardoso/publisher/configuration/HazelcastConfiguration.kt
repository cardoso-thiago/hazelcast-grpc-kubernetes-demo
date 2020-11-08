package br.com.cardoso.publisher.configuration

import com.hazelcast.config.Config
import com.hazelcast.config.JoinConfig
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class HazelcastConfiguration {

    @Bean
    fun hazelcastInstancePublisher(): HazelcastInstance {
        return Hazelcast.newHazelcastInstance()
    }

    @Bean
    fun hazelcastConfig(): Config {
        val config = Config()
        val joinConfig: JoinConfig = config.networkConfig.join
        joinConfig.multicastConfig.isEnabled = false
        joinConfig.kubernetesConfig.isEnabled = true
        return config
    }
}