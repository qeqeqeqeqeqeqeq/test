package com.odianyun.demo.model;

import java.io.Serializable;

public class Code implements Serializable {
    private static final long serialVersionUID = 3507520671532739522L;
    private String pool;
    private String category;
    private String language;
    private String isdeleted;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
