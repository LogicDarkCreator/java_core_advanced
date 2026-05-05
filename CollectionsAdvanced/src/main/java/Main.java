import util.UserManager;
import util.UserInputHandler;
import util.CommandHandler;

public class Main {
    private final UserManager userManager;
    private final UserInputHandler inputHandler;
    private final CommandHandler commandHandler;

    public Main() {
        this.userManager = new UserManager();
        this.inputHandler = new UserInputHandler();
        this.commandHandler = new CommandHandler(userManager, inputHandler);
    }

    public void run() {
        commandHandler.handleHelp();

        while (commandHandler.isRunning()) {
            String command = inputHandler.readCommand();
            commandHandler.handleCommand(command);
        }

        inputHandler.close();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
