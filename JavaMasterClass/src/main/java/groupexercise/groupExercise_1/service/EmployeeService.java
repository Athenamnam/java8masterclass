package groupexercise.groupExercise_1.service;

import groupexercise.groupExercise_1.enums.SortEnum;
import groupexercise.groupExercise_1.model.Employee;
import groupexercise.groupExercise_1.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeService {

    public static int addEmployee(Employee employee) {
        return EmployeeRepository.addEmployee(employee);
    }

    public static void addEmployeeList(List<Employee> employeesList) {
        EmployeeRepository.addEmployeeList(employeesList);
    }

    public static Optional<Employee> getEmployeeByEmployeeNumber(int employeeNumber) {
        return EmployeeRepository.getEmployees()
                .stream()
                .filter(e -> e.getEmployeeNumber() == employeeNumber)
                .findFirst();
    }

    public static List<Employee> getEmployeeByEmployeeNumber(Integer employeeNumber, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getEmployeeNumber() == (employeeNumber), sortEnum);
    }

    public static List<Employee> getEmployeeByFirstName(String firstName, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getFirstName().equalsIgnoreCase(firstName), sortEnum);
    }

    public static List<Employee> getEmployeeByLastName(String lastName, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getLastName().equalsIgnoreCase(lastName), sortEnum);
    }

    public static List<Employee> getEmployeeByMiddleName(String middleName, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getMiddleName().equalsIgnoreCase(middleName), sortEnum);
    }

    public static List<Employee> getEmployeeByName(String name, SortEnum sortEnum) {
        return getMatchedEmployees(e -> String.format("%s %s %s", e.getFirstName(), e.getMiddleName(), e.getLastName()).toUpperCase().contains(name.toUpperCase()), sortEnum);
    }

    public static List<Employee> getEmployeeByHiringDate(String hiringDate, SortEnum sortEnum) {
        return getMatchedEmployees(e -> e.getHiringDate().equalsIgnoreCase(hiringDate), sortEnum);
    }

    public static List<Employee> getAll(Comparator<Employee> comparator) {
        return EmployeeRepository.getEmployees()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public static Optional<Employee> deleteEmployeeByEmployeeNumber(int employeeNumber) {
        EmployeeRepository.deleteEmployees(employeeNumber);
        return Optional.empty();
    }

    private static List<Employee> getMatchedEmployees(
            Predicate<Employee> employeePredicate, SortEnum sortEnum) {

        return EmployeeRepository.getEmployees()
                .stream()
                .filter(employeePredicate)
                .sorted(sortEnum.getComparator())
                .collect(Collectors.toList());
    }


}
