package com.krzysiek.manager_lotniczy.model;

public record Message(Type type, String title, String message) {
    public enum Type{
        ERROR,
        INFO
    }
}
