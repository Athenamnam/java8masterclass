package groupexercise.groupExercise_1.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private int employeeNumber;
    private String firstName;
    private String hiringDate;
    private String lastName;
    private String middleName;
}
