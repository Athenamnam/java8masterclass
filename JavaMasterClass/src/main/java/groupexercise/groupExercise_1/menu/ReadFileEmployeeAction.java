package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.model.Employee;
import groupexercise.groupExercise_1.service.EmployeeService;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static groupexercise.groupExercise_1.constants.MenuConstants.FILE_PATH;

public class ReadFileEmployeeAction implements CommandAction {

    @Override
    public void doAction() {

        List<Employee> employees = new ArrayList<>();
        String strPath = null;
        Path path = null;

        while (true) {
            try {
                strPath = obtainInput(FILE_PATH);
                path = Paths.get(strPath);
                System.out.println(path);

                if (!Files.isRegularFile(path)) {
                    System.out.println("here");

                    throw new RuntimeException();
                }


                List<String> fileContents = Files.readAllLines(path);
                System.out.println(fileContents.get(0));
                for (int i = 1; i < fileContents.size(); i++) {
                    String fileContent = fileContents.get(i);
                    String[] line = fileContent.split(",");

                    int employeeNumber = Integer.parseInt(line[0]);
                    String firstName = line[1].trim();
                    String middleName = line[2].trim();
                    String lastName = line[3].trim();
                    String hiringDate = line[4] + line[5];

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
                    LocalDate tempDate = LocalDate.parse(hiringDate.trim(), formatter);

                    if (tempDate.isAfter(LocalDate.now())) {
                        System.out.println("Invalid Date. Future date is not allowed.");
                        continue;
                    }

                    DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                    hiringDate = parser.format(tempDate);

                    Employee employee = Employee.builder().employeeNumber(employeeNumber)
                            .firstName(firstName)
                            .middleName(middleName)
                            .lastName(lastName)
                            .hiringDate(hiringDate)
                            .build();

                    employees.add(employee);
                }

                break;
            } catch (Exception e) {
                System.out.println("Error encountered reading file " + strPath);
            }
        }

        new EmployeeService().addEmployeeList(employees);
        System.out.println("Records successfully imported from file " + strPath);
    }

    private String obtainInput(String message) {
        System.out.print(message);

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}