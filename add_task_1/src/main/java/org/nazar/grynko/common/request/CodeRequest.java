package org.nazar.grynko.common.request;

import org.nazar.grynko.common.constant.RequestType;

import java.io.Serializable;

public class CodeRequest implements Request, Serializable {

    private final String code;
    private final RequestType type;

    public CodeRequest(String code, RequestType type) {
        this.code = code;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public RequestType getType() {
        return type;
    }
}
