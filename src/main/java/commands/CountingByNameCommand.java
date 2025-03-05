package commands;

import org.example.Receiver;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountingByNameCommand implements Command {
    Receiver receiver = Receiver.getInstance();

    @Override
    public String getName() {
        return "countingByName";
    }

    @Override
    public void execute(Scanner in, String... args) {
        if (args.length != 0){
            System.out.println("This command does not take any arguments");
            return;
        }
        HashMap<String, Integer> map = receiver.countByName();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Group " + entry.getKey() + " : " + value);
        }
    }
}
