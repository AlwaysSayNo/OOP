package org.nazar.grynko.common.response;

import java.io.Serializable;

public class CodeResponse implements main.java.org.nazar.grynko.common.response.Response, Serializable {

    private final String code;

    public CodeResponse(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
