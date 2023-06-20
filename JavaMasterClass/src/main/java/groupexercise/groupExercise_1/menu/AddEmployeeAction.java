package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.model.Employee;
import groupexercise.groupExercise_1.model.UserSelection;
import groupexercise.groupExercise_1.service.EmployeeService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import static groupexercise.groupExercise_1.constants.MenuConstants.FIRST_NAME;
import static groupexercise.groupExercise_1.constants.MenuConstants.HIRING_DATE;
import static groupexercise.groupExercise_1.constants.MenuConstants.LAST_NAME;
import static groupexercise.groupExercise_1.constants.MenuConstants.MIDDLE_NAME;

public class AddEmployeeAction implements CommandAction {

    @Override
    public void doAction() {
        int employeeNumber = obtainNumberInput();

        String firstName = obtainInput(FIRST_NAME);
        String lastName = obtainInput(LAST_NAME);
        String middleName = obtainInput(MIDDLE_NAME);
        String hiringDate = null;

        while(true) {
            hiringDate = obtainInput(HIRING_DATE);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                LocalDate date = LocalDate.parse(hiringDate, formatter);
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("Invalid Date. Future date is not allowed.");
                    continue;
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid Date Format. PLease enter date in yyyy-MM-dd format.");
            }
        }

        Employee employee =
                Employee.builder()
                        .employeeNumber(employeeNumber)
                        .firstName(firstName)
                        .lastName(lastName)
                        .middleName(middleName)
                        .hiringDate(hiringDate)
                        .build();

        new EmployeeService().addEmployee(employee);

        new DisplayEmployeeAction(null, employee).doAction();
    }


    private static Integer obtainNumberInput() {

        int employeeNumber = 0;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Employee Number: ");
                employeeNumber = scanner.nextInt();
                break;

            } catch (Exception e) {
                System.out.println("Invalid entry. Try again.\n");
            }
        }

        return employeeNumber;
    }

    private String obtainInput(String message) {
        System.out.print(message);

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}