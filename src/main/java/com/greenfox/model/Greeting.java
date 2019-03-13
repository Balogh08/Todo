package com.greenfox.model;

import java.util.concurrent.atomic.AtomicLong;

public class Greeting {
    private long id;
    private static AtomicLong counter = new AtomicLong();
    private String name;

    public Greeting(String name) {
        this.id = counter.incrementAndGet();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
