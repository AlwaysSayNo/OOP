package org.nazar.grynko;

import lombok.SneakyThrows;
import org.nazar.grynko.client.ClientMain;
import org.nazar.grynko.server.ServerMain;

public class Main {

    public static void main(String[] args) {

        threadStart();

    }

    @SneakyThrows
    private static void threadStart() {
        Thread serverThread = new Thread(() -> ServerMain.main(new String[]{}));

        Thread clientThread = new Thread(() -> ClientMain.main(new String[]{}));

        serverThread.start();
        clientThread.start();

        serverThread.join();
        clientThread.join();
    }

}
