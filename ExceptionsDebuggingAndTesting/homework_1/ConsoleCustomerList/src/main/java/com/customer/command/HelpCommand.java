package com.customer.command;

import java.util.Map;

public class HelpCommand implements Command {
    private final CommandExecutor executor;

    public HelpCommand(CommandExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("\n=== Доступные команды ===\n");

        Map<String, Command> commands = executor.getCommands();
        commands.values().forEach(cmd -> {
            System.out.printf("%-10s - %s%n", cmd.getName(), cmd.getDescription());
            System.out.printf("           Пример: %s%n%n", cmd.getExample());
        });
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Показать справку";
    }

    @Override
    public String getExample() {
        return "help";
    }
}