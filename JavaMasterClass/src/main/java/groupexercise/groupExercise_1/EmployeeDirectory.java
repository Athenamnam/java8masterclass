package groupexercise.groupExercise_1;

import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.menu.MainMenuAction;

public class EmployeeDirectory {

    public static void main(String[] args) {
        CommandAction mainMenuAction = new MainMenuAction();
        mainMenuAction.doAction();
    }

}