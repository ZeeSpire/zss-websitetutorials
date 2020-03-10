package com.eSkaVision.eSkaVisionTestingSpringBoot.model;

import java.util.Objects;

public class MockitoObject {
    private String name;

    public MockitoObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MockitoObject that = (MockitoObject) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
