package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.enums.SortEnum;
import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.interfaces.Option;
import groupexercise.groupExercise_1.model.Employee;
import groupexercise.groupExercise_1.model.UserSelection;
import groupexercise.groupExercise_1.service.EmployeeService;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static groupexercise.groupExercise_1.constants.MenuConstants.*;

@Option(label = NOT_ENCODED, key = 1)
@Option(label = ENCODED, key = 2)
@Option(label = BACK, key = -1)
public class ExportFileEmployeeAction extends ActionAbstract implements CommandAction {

    private DateTimeFormatter defaultFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    private DateTimeFormatter exportFormat = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);

    private boolean encodeString;

    @Override
    public void doAction() {

//        UserSelection userSelection = UserSelection.builder().build();
//        CommandAction sortMenuAction = new SortMenuAction(userSelection);
//        sortMenuAction.doAction();
//
//        CommandAction sortEmployeeAction = new SortEmployeeAction(userSelection);
//        sortEmployeeAction.doAction();
//
//        Comparator comparator = userSelection.getSortEnum().getComparator();
//        if (!userSelection.getOrder()) {
//            comparator = comparator.reversed();
//        }

        List<Employee> employeeList = EmployeeService.getAll(SortEnum.defaultSort().getComparator());

        String strPath = null;
        Path path;
        File newFile;

        while (true) {
            Option option = generateMessageAndAskUserInput();

            encodeString = option.label().equalsIgnoreCase(ENCODED);

            try {
                boolean isNewFile = true;

                strPath = obtainInput(FILE_PATH);
                path = Paths.get(strPath);

                if (!Files.exists(path)) {
                    newFile = new File(strPath);
                    newFile.createNewFile();
                } else {
                    newFile = path.toFile();
                    isNewFile = false;
                }


                FileWriter writer = new FileWriter(newFile, true);

                if (isNewFile && !encodeString && !CollectionUtils.isEmpty(employeeList)) {
                    String header =
                            String.format(
                                    DISPLAY_TEXT_BODY_FORMAT,
                                    "Employee Number", "Employee Name", "Date Hired");
                    writer.write(encodeString(header));
                    writer.write(System.getProperty("line.separator"));
                }

                for (Employee employee : employeeList) {

                    LocalDate tempDate = LocalDate.parse(employee.getHiringDate(), defaultFormat);

                    exportFormat.format(tempDate);
                    String employeeString =
                            String.format(
                                    DISPLAY_TEXT_BODY_FORMAT,
                                    employee.getEmployeeNumber(),
                                    String.format(
                                            DISPLAY_EMPLOYEE_FULL_NAME_FORMAT,
                                            employee.getFirstName(),
                                            employee.getMiddleName(),
                                            employee.getLastName()),
                                    exportFormat.format(tempDate));

                    writer.write(encodeString(employeeString));
                    writer.write(System.getProperty("line.separator"));
                }

                writer.close();
                System.out.println("Records exported successfully at " + strPath);

                break;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error encountered reading file " + strPath);
            }
        }


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
        return SELECT_ACTION;
    }

    private String encodeString(String message) {
        return encodeString ? Base64.getEncoder().encodeToString(message.getBytes()) : message;
    }

}