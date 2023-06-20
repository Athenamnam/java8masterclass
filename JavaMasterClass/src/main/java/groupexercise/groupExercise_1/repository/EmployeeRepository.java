package groupexercise.groupExercise_1.repository;


import groupexercise.groupExercise_1.model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeRepository {

    private static List<Employee> employees = new ArrayList<>(Arrays.asList(
            Employee.builder().employeeNumber(1).firstName("SHANYL").middleName("MANCAO").lastName("JIMENEZ").hiringDate("2022-06-07").build(),
            Employee.builder().employeeNumber(2).firstName("EARL JUNE").middleName("DELA CUADRA").lastName("OMICTIN").hiringDate("2022-06-08").build(),
            Employee.builder().employeeNumber(3).firstName("JAMES").middleName("").lastName("VILLEZA").hiringDate("2022-06-09").build())
    );

    public static int addEmployee(Employee employee) {
        employees.add(employee);

        return employee.getEmployeeNumber();
    }

    public static void addEmployeeList(List<Employee> employeeList) {
        employees.addAll(employeeList);;
    }

    public static List<Employee> getEmployees() {
        return employees;
    }

    public static void setEmployees(List<Employee> employees) {
        EmployeeRepository.employees = employees;
    }

    public static void deleteEmployees(int employeeNumber) {
        EmployeeRepository.employees.removeIf(s -> s.getEmployeeNumber() == employeeNumber);
    }
}
