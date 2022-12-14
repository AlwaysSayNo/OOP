package org.nazar.grynko.server.controller;

import lombok.SneakyThrows;
import org.nazar.grynko.common.request.ParameterizedRequest;
import org.nazar.grynko.common.response.ParameterizedResponse;
import org.nazar.grynko.server.processor.RequestProcessor;
import org.springframework.stereotype.Controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Controller
public class ServerFrontController {

    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final RequestProcessor requestProcessor;

    @SneakyThrows
    public ServerFrontController(Socket socket, RequestProcessor requestProcessor) {
        this.socket = socket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.requestProcessor = requestProcessor;
    }

    public void process() {
        ParameterizedRequest request = receiveRequest();

        ParameterizedResponse response = requestProcessor.getResponse(request);

        sendResponse(response);
    }

    @SneakyThrows
    private ParameterizedRequest receiveRequest() {
        return (ParameterizedRequest) in.readObject();
    }

    @SneakyThrows
    private void sendResponse(ParameterizedResponse response) {
        out.writeObject(response);
    }

}
