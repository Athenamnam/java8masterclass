package groupexercise.groupExercise_1.menu;


import groupexercise.groupExercise_1.enums.ActionEnum;
import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.interfaces.Option;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static groupexercise.groupExercise_1.constants.MenuConstants.*;

@Option(label = LIST_ALL_EMPLOYEE_RECORDS, key = 1)
@Option(label = ADD_NEW_EMPLOYEE_RECORD, key = 2)
@Option(label = DELETE_EMPLOYEE_RECORDS, key = 3)
@Option(label = SEARCH_EMPLOYEE_RECORDS, key = 4)
@Option(label = READ_FROM_FILE, key = 5)
@Option(label = EXPORT_TO_FILE, key = 6)
@Option(label = EXIT, key = -1)
public class MainMenuAction extends ActionAbstract implements CommandAction {

    @Override
    public void doAction() {
        while (true) {
            Option option = generateMessageAndAskUserInput();

            ActionEnum.doCommandAction(option);
        }
    }

    @Override
    String getHeaderMessage() {
        return MAIN_OPTIONS_HEADER;
    }

    @Override
    Option[] getOptions() {
        return this.getClass().getAnnotationsByType(Option.class);
    }

    @Override
    String getFooterMessage() {
        return ENTER_ACTION_TYPE;
    }
}
