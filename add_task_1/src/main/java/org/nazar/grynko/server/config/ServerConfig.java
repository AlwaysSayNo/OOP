package org.nazar.grynko.server.config;

import org.nazar.grynko.server.socket.SingleServerSocketPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.Socket;
import java.net.URL;

@Configuration
@ComponentScan("org.nazar.grynko.server")
@PropertySource("classpath:application.properties")
public class ServerConfig {

    @Bean
    public Socket socket(SingleServerSocketPool singleServerSocket) {
        return singleServerSocket.getSocket();
    }

    // Non Jar way
    @Bean
    public URL sqlFolderURL(){
        return getClass().getClassLoader().getResource("task_1/sql");
    }

}
