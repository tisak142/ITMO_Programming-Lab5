package commands;

import java.util.Scanner;

public interface Command {

    public void execute(Scanner in, String... args);

    public String getName();
}
