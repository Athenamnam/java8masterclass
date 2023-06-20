package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.interfaces.Option;
import groupexercise.groupExercise_1.model.Employee;
import groupexercise.groupExercise_1.model.UserSelection;
import groupexercise.groupExercise_1.service.EmployeeService;

import java.util.Comparator;
import java.util.List;

import static groupexercise.groupExercise_1.constants.MenuConstants.ENTER_ACTION_TYPE;
import static groupexercise.groupExercise_1.constants.MenuConstants.MAIN_OPTIONS_HEADER;

public class ListAllEmployeeAction extends ActionAbstract implements CommandAction {

    @Override
    public void doAction() {
        UserSelection userSelection = UserSelection.builder().build();
        CommandAction sortMenuAction = new SortMenuAction(userSelection);
        sortMenuAction.doAction();

        CommandAction sortEmployeeAction = new SortEmployeeAction(userSelection);
        sortEmployeeAction.doAction();

        Comparator comparator = userSelection.getSortEnum().getComparator();
        if(!userSelection.getOrder()) {
            comparator = comparator.reversed();
        }

        List<Employee> employeeList = EmployeeService.getAll(comparator);

        CommandAction displayEmployeeAction = new DisplayEmployeeAction(employeeList, null);
        displayEmployeeAction.doAction();
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
