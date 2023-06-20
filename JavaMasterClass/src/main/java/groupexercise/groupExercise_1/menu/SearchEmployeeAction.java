package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.enums.SearchEnum;
import groupexercise.groupExercise_1.enums.SortEnum;
import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.interfaces.Option;
import groupexercise.groupExercise_1.model.Employee;
import groupexercise.groupExercise_1.model.UserSelection;
import groupexercise.groupExercise_1.service.EmployeeService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static groupexercise.groupExercise_1.constants.MenuConstants.*;

@Option(label = SEARCH_EMPLOYEE_NUMBER, key = 1)
@Option(label = SEARCH_NAME, key = 2)
@Option(label = SEARCH_HIRING_DATE, key = 3)
@Option(label = BACK, key = -1)
public class SearchEmployeeAction extends ActionAbstract implements CommandAction {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void doAction() {
        Option option = generateMessageAndAskUserInput();

        if (option.key() == -1) return;

        SearchEnum searchEnum = SearchEnum.getSearchType(option.label());
        List<Employee> employeeList = (List<Employee>) searchEnum.getObtainInputSupplier().get();

        new DisplayEmployeeAction(employeeList, null).doAction();
    }

    private List<Employee> searchByEmployeeNumber() {
        int employeeNumber = obtainNumberInput();

        Optional<Employee> employeeOptional =
                employeeService.getEmployeeByEmployeeNumber(employeeNumber);

        return employeeOptional.isPresent()
                ? Arrays.asList(employeeOptional.get())
                : Collections.emptyList();
    }

    private List<Employee> searchByFirstName() {
        String firstName = obtainInput(ENTER_FIRST_NAME);

        return employeeService.getEmployeeByFirstName(firstName, SortEnum.defaultSort());
    }

    private List<Employee> searchByLastName() {
        String lastName = obtainInput(ENTER_LAST_NAME);

        return employeeService.getEmployeeByLastName(lastName, SortEnum.defaultSort());
    }

    private List<Employee> searchByMiddleName() {
        String middleName = obtainInput(ENTER_MIDDLE_NAME);

        return employeeService.getEmployeeByMiddleName(middleName, SortEnum.defaultSort());
    }

    private List<Employee> searchByHiringDate() {
        String hiringDate = obtainInput(ENTER_HIRING_DATE);

        return employeeService.getEmployeeByHiringDate(hiringDate, SortEnum.defaultSort());
    }

    private static Integer obtainNumberInput() {

        int employeeNumber = 0;

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Employee Number: ");
            employeeNumber = scanner.nextInt();

        } catch (Exception e) {
            System.out.println("Invalid entry. Try again.\n");
        }

        return employeeNumber;
    }

    private String obtainInput(String message) {
        System.out.print(message);

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    String getHeaderMessage() {
        return CHOOSE_ACTION_HEADER;
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
