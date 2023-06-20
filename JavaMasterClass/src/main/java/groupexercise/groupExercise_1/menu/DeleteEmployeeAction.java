package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.model.Employee;
import groupexercise.groupExercise_1.service.EmployeeService;

import java.util.Optional;
import java.util.Scanner;

import static groupexercise.groupExercise_1.constants.MenuConstants.DELETED_EMPLOYEE;
import static groupexercise.groupExercise_1.constants.MenuConstants.EMPLOYEE_NOT_FOUND;

public class DeleteEmployeeAction implements CommandAction {

    @Override
    public void doAction() {

        int employeeNumber = obtainNumberInput();

        Optional<Employee> optionalEmployee =
                new EmployeeService().getEmployeeByEmployeeNumber(employeeNumber);

        if (optionalEmployee.isPresent()) {
            new EmployeeService().deleteEmployeeByEmployeeNumber(employeeNumber);

            Employee deletedEmployee = optionalEmployee.get();
            System.out.println(
                    String.format(
                            DELETED_EMPLOYEE,
                            deletedEmployee.getEmployeeNumber(),
                            deletedEmployee.getFirstName(),
                            deletedEmployee.getMiddleName(),
                            deletedEmployee.getLastName(),
                            deletedEmployee.getHiringDate()));

        } else {
            System.out.println(String.format(EMPLOYEE_NOT_FOUND, employeeNumber));
        }

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

}
