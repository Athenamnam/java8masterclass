package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.interfaces.CommandAction;

public class Exit implements CommandAction {

    @Override
    public void doAction() {
        System.out.println("GoodBye!");
        System.exit(0);
    }
}
