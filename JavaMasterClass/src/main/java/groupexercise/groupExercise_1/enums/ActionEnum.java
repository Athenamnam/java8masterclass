package groupexercise.groupExercise_1.enums;


import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.interfaces.Option;
import groupexercise.groupExercise_1.menu.*;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static groupexercise.groupExercise_1.constants.MenuConstants.*;

public enum ActionEnum {

    LISTALLEMPLOYEERECORDS(LIST_ALL_EMPLOYEE_RECORDS, ListAllEmployeeAction::new),
    ADDNEWEMPLOYEERECORD(ADD_NEW_EMPLOYEE_RECORD, AddEmployeeAction::new),
    DELETEEMPLOYEERECORDS(DELETE_EMPLOYEE_RECORDS, DeleteEmployeeAction::new),
    SEARCHEMPLOYEERECORDS(SEARCH_EMPLOYEE_RECORDS, SearchEmployeeAction::new),
    READFROMFILE(READ_FROM_FILE, ReadFileEmployeeAction::new),
    EXPORTTOFILE(EXPORT_TO_FILE, ExportFileEmployeeAction::new),
    EXITENUM(EXIT, Exit::new),

    ;

    private static Map<String, CommandAction> mapCommandAction;

    private final String commandName;
    private final Supplier<CommandAction> commandAction;

    static {
        mapCommandAction = Arrays.stream(ActionEnum.values()).collect(Collectors.toMap(s -> s.getCommandName(), k -> k.getCommandAction()));
    }

    ActionEnum(String commandName, Supplier<CommandAction> commandAction) {
        this.commandName = commandName;
        this.commandAction = commandAction;
    }

    private String getCommandName() {
        return commandName;
    }

    private CommandAction getCommandAction() {
        return commandAction.get();
    }

    public static void doCommandAction(Option option) {
        if (option == null) return;
        mapCommandAction.get(option.label()).doAction();
    }

}