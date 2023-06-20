package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.model.Employee;
import groupexercise.groupExercise_1.interfaces.CommandAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_EMPLOYEE_ADD_SUCCESS;
import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_EMPLOYEE_FULL_NAME_FORMAT;
import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_LINE;
import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_NO_RECORDS_FOUND;
import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_NO_RECORDS_FOUND_FORMAT;
import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_TABLE_BODY_FORMAT;
import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_TABLE_HEADER_EMPLOYEE_NUMBER;
import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_TABLE_HEADER_FORMAT;
import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_TABLE_HEADER_HIRING_DATE;
import static groupexercise.groupExercise_1.constants.MenuConstants.DISPLAY_TABLE_HEADER_NAME;


public class DisplayEmployeeAction
        implements CommandAction {

    private final List<Employee> employees;

    private final Employee newEmployee;

    public DisplayEmployeeAction(List<Employee> employees, Employee newEmployee) {
        this.employees = employees;
        this.newEmployee = newEmployee;
    }

    @Override
    public void doAction() {

        if (Objects.nonNull(newEmployee)) {
            System.out.println(DISPLAY_EMPLOYEE_ADD_SUCCESS + LocalDateTime.now());
            System.out.println("Number: " + newEmployee.getEmployeeNumber());
            System.out.println(
                    "Name: "
                            + newEmployee.getFirstName()
                            + " "
                            + newEmployee.getMiddleName()
                            + " "
                            + newEmployee.getLastName());
            System.out.println("Date Hired: " + newEmployee.getHiringDate() + "\n");
        } else {

            System.out.println(DISPLAY_LINE);
            System.out.printf(
                    DISPLAY_TABLE_HEADER_FORMAT,
                    DISPLAY_TABLE_HEADER_EMPLOYEE_NUMBER,
                    DISPLAY_TABLE_HEADER_NAME,
                    DISPLAY_TABLE_HEADER_HIRING_DATE);
            System.out.println(DISPLAY_LINE);

            if (!CollectionUtils.isEmpty(employees)) {
                employees.forEach(this::displayEmployee);
            } else {
                displayNoRecordsFound();
            }

            System.out.println(DISPLAY_LINE);
        }

    }

    private void displayEmployee(Employee employee) {
        System.out.printf(
                DISPLAY_TABLE_BODY_FORMAT,
                employee.getEmployeeNumber(),
                String.format(
                        DISPLAY_EMPLOYEE_FULL_NAME_FORMAT,
                        employee.getFirstName(),
                        employee.getMiddleName(),
                        employee.getLastName()),
                employee.getHiringDate());
    }

    private void displayNoRecordsFound() {
        System.out.printf(DISPLAY_NO_RECORDS_FOUND_FORMAT, DISPLAY_NO_RECORDS_FOUND, "");
    }
}
