package groupexercise.groupExercise_1.enums;


import groupexercise.groupExercise_1.model.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static groupexercise.groupExercise_1.constants.MenuConstants.*;

public enum SortEnum {


    SORTEMPLOYEENUMBER(SORT_EMPLOYEE_NUMBER, Comparator.comparingInt(Employee::getEmployeeNumber)),

    SORTFIRSTNAME(SORT_FIRST_NAME, Comparator.comparing(Employee::getFirstName)),

    SORTLASTNAME(SORT_LAST_NAME, Comparator.comparing(Employee::getLastName)),

    SORTHIRINGDATE(SORT_HIRING_DATE, Comparator.comparing(Employee::getHiringDate));

    private String sortName;
    private Comparator<Employee> comparator;

    private static Map<String, SortEnum> mapSorts;

    static {
        mapSorts = Arrays.stream(SortEnum.values()).collect(Collectors.toMap(s -> s.getSortName(), Function.identity()));
    }

    SortEnum(String sortName, Comparator<Employee> comparator) {
        this.sortName = sortName;
        this.comparator = comparator;
    }

    private String getSortName() {
        return sortName;
    }

    public Comparator<Employee> getComparator() {
        return comparator;
    }

    public static SortEnum getSortEnum(String sortName) {
        return mapSorts.get(sortName);
    }


    public static SortEnum defaultSort() {
        return SORTEMPLOYEENUMBER;
    }
}
