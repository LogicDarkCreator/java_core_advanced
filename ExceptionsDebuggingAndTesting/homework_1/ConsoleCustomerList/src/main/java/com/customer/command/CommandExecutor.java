package com.customer.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Logger queriesLogger = LogManager.getLogger("queries");
    private static final Logger errorsLogger = LogManager.getLogger("errors");

    private final Map<String, Command> commands;

    public CommandExecutor() {
        this.commands = new HashMap<>();
        registerCommands();
    }

    private void registerCommands() {
        register(new AddCommand());
        register(new ListCommand());
        register(new RemoveCommand());
        register(new CountCommand());
        register(new HelpCommand(this));
        register(new ExitCommand());
    }

    public void register(Command command) {
        commands.put(command.getName(), command);
    }

    public boolean execute(String input) {
        if (input == null || input.trim().isEmpty()) {
            return true;
        }

        String[] tokens = input.trim().split("\\s+", 2);
        String commandName = tokens[0].toLowerCase();
        String[] args = tokens.length > 1 ? new String[]{tokens[1]} : new String[0];

        queriesLogger.info("Выполнение команды: {}", input);

        Command command = commands.get(commandName);
        if (command != null) {
            try {
                command.execute(args);
                return true;
            } catch (Exception e) {
                errorsLogger.error("Ошибка при выполнении команды {}: {}", commandName, e.getMessage(), e);
                System.out.println("Ошибка при выполнении команды: " + e.getMessage());
                return true;
            }
        } else {
            System.out.println("Неизвестная команда. Введите 'help' для списка команд.");
            errorsLogger.warn("Неизвестная команда: {}", commandName);
            return true;
        }
    }

    public Map<String, Command> getCommands() {
        return new HashMap<>(commands);
    }
}