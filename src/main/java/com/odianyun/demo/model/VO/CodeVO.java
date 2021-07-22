package com.odianyun.demo.model.VO;

import java.io.Serializable;

public class CodeVO implements Serializable {

    private static final long serialVersionUID = -8636664519440262133L;

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
