package com.mitchell.claims.model;

/**
 * @author Khaled Ayoubi
 */
public class Claim {
    private String id;
    private String name;

    public Claim(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
