package groupexercise.groupExercise_1.enums;

import groupexercise.groupExercise_1.model.Employee;
import groupexercise.groupExercise_1.service.EmployeeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static groupexercise.groupExercise_1.constants.MenuConstants.*;

public enum SearchEnum {

    SEARCHEMPLOYEENUMBER(SEARCH_EMPLOYEE_NUMBER, () -> searchByEmployeeNumber()),
    SEARCHNAME(SEARCH_NAME, () -> searchByName()),
    SEARCHFIRSTNAME(SEARCH_FIRST_NAME, () -> searchByFirstName()),
    SEARCHMIDDLENAME(SEARCH_MIDDLE_NAME, () -> searchByMiddleName()),
    SEARCHLASTNAME(SEARCH_LAST_NAME, () -> searchByLastName()),
    SEARCHHIRINGDATE(SEARCH_HIRING_DATE, () -> searchByHiringDate());

    private String searchName;

    private Supplier<Object> obtainInputSupplier;


    private static Map<String, SearchEnum> saerchMap;

    static {
        saerchMap = Arrays.stream(SearchEnum.values()).collect(Collectors.toMap(s -> s.searchName, Function.identity()));
    }

    SearchEnum(String searchName, Supplier<Object> obtainInputSupplier) {
        this.searchName = searchName;
        this.obtainInputSupplier = obtainInputSupplier;
    }

    public static SearchEnum getSearchType(String searchName) {
        return saerchMap.get(searchName);
    }

    public Supplier<Object> getObtainInputSupplier() {
        return obtainInputSupplier;
    }

    private static Integer obtainNumberInput(String message) {

        int employeeNumber = 0;

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print(message);
            employeeNumber = scanner.nextInt();

        } catch (Exception e) {
            System.out.println("Invalid entry. Try again.\n");
        }

        return employeeNumber;
    }

    private static String obtainInput(String message) {
        System.out.print(message);

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static List<Employee> searchByEmployeeNumber() {
        int employeeNumber = obtainNumberInput(ENTER_EMPLOYEE_NUMBER);

        Optional<Employee> employeeOptional =
                EmployeeService.getEmployeeByEmployeeNumber(employeeNumber);

        return employeeOptional.isPresent()
                ? Arrays.asList(employeeOptional.get())
                : Collections.emptyList();
    }

    private static List<Employee> searchByName() {
        String name = obtainInput(ENTER_NAME);

        return EmployeeService.getEmployeeByName(name, SortEnum.defaultSort());
    }

    private static List<Employee> searchByFirstName() {
        String firstName = obtainInput(ENTER_FIRST_NAME);

        return EmployeeService.getEmployeeByFirstName(firstName, SortEnum.defaultSort());
    }

    private static List<Employee> searchByLastName() {
        String lastName = obtainInput(ENTER_LAST_NAME);

        return EmployeeService.getEmployeeByLastName(lastName, SortEnum.defaultSort());
    }

    private static List<Employee> searchByMiddleName() {
        String middleName = obtainInput(ENTER_MIDDLE_NAME);

        return EmployeeService.getEmployeeByMiddleName(middleName, SortEnum.defaultSort());
    }

    private static List<Employee> searchByHiringDate() {
        String hiringDate = "";
        while (true) {
            try {
                hiringDate = obtainInput(ENTER_HIRING_DATE);

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


        return EmployeeService.getEmployeeByHiringDate(hiringDate, SortEnum.defaultSort());
    }
}
