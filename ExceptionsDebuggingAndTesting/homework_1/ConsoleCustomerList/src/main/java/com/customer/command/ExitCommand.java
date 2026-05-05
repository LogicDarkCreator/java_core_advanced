package com.customer.command;

public class ExitCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("До свидания!");
        System.exit(0);
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Выйти из программы";
    }

    @Override
    public String getExample() {
        return "exit";
    }
}