package org.nazar.grynko.client;

import org.nazar.grynko.client.cases.Cases;
import org.nazar.grynko.client.config.ClientConfig;
import org.nazar.grynko.client.controller.ClientFrontController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientMain {

    public static void main(String[] args) {
        System.out.println("Start clientMain");

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ClientConfig.class);

        ClientFrontController controller =
                context.getBean("clientFrontController", ClientFrontController.class);

        doOperation(controller);

        context.close();
    }

    private static void doOperation(ClientFrontController controller) {
        Cases.case3(controller);
    }

}
