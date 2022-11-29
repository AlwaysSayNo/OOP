package org.nazar.grynko.client.controller;

import lombok.SneakyThrows;
import org.nazar.grynko.common.constant.RequestType;
import org.nazar.grynko.common.request.ParameterizedRequest;
import org.nazar.grynko.common.response.ParameterizedResponse;
import org.springframework.stereotype.Controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

@Controller
public class ClientFrontController {

    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    @SneakyThrows
    public ClientFrontController(Socket socket) {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    public Map<String, Object> process(String code, RequestType type, Map<String, Object> parameters) {
        ParameterizedRequest request = new ParameterizedRequest(code, type, parameters);

        return process(request);
    }

    public Map<String, Object> process(ParameterizedRequest request) {
        sendRequest(request);

        ParameterizedResponse response = receiveResponse();

        Map<String, Object> result;
        switch (response.getStatus()) {
            case OK: {
                result = response.getParameters();
                break;
            }

            case EXCEPTION: {
                throw new IllegalArgumentException();
            }

            default: {
                throw new IllegalStateException();
            }
        }

        return result;
    }

    @SneakyThrows
    private void sendRequest(ParameterizedRequest request) {
        out.writeObject(request);
    }

    @SneakyThrows
    private ParameterizedResponse receiveResponse() {
        return (ParameterizedResponse) in.readObject();
    }

}
