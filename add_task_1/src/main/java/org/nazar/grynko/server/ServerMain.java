package org.nazar.grynko.server;

import org.nazar.grynko.server.config.ServerConfig;
import org.nazar.grynko.server.controller.ServerFrontController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerMain {

    public static void main(String[] args) {
        System.out.println("Start server main");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ServerConfig.class);

        ServerFrontController controller =
                context.getBean("serverFrontController", ServerFrontController.class);

        try {
            while (true){
                controller.process();
            }
        }
        catch (Exception e) {
            //System.out.println("ServerMain#Exception: " + e);
            //e.printStackTrace();
        }

        context.close();
    }

}
