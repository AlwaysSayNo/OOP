package org.nazar.grynko.client.config;

import org.nazar.grynko.client.socket.SingleSocketPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.Socket;

@Configuration
@ComponentScan("org.nazar.grynko.client")
@PropertySource("classpath:application.properties")
public class ClientConfig {

    // strange name
    @Bean
    public Socket socket(SingleSocketPool singleSocketPool) {
        return singleSocketPool.getSocket();
    }

}
