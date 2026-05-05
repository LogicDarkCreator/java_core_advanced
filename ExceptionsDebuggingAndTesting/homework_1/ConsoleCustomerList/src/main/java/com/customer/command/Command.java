package com.customer.command;

public interface Command {
    void execute(String[] args);
    String getName();
    String getDescription();
    String getExample();
}