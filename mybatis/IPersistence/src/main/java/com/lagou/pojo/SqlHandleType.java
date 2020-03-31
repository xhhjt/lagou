package com.lagou.pojo;

public enum SqlHandleType {
    SElECT("select"),
    UPDATE("update"),
    INSERT("insert"),
    DELETE("delete");
    String name;

    SqlHandleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
