package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.enums.ActionEnum;
import groupexercise.groupExercise_1.interfaces.Option;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import static groupexercise.groupExercise_1.constants.MenuConstants.STRING_FORMATTER;

public abstract class ActionAbstract {

    abstract String getHeaderMessage();

    abstract Option[] getOptions();

    abstract String getFooterMessage();

    Option generateMessageAndAskUserInput() {
        while (true) {
            System.out.println(getHeaderMessage());
            Arrays.stream(getOptions()).forEach(o -> System.out.println(String.format(STRING_FORMATTER, o.key(), o.label())));
            System.out.print("\n" + getFooterMessage());

            Option option = null;

            try {
                Scanner scanner = new Scanner(System.in);
                int selectedOption = scanner.nextInt();

                option = Arrays.stream(getOptions()).filter(s -> s.key() == selectedOption).findFirst().get();

                if (Objects.isNull(option)) {
                    throw new IllegalArgumentException("Selected option is not valid.");
                }

            } catch (Exception e) {
                System.out.println("Invalid entry. Try again.\n");
                continue;
            }

            return option;
        }
    }

    void askInput(Option[] options) {
        Scanner scanner = new Scanner(System.in);

        int selectedOption;
        Option option = null;

        try {
            selectedOption = scanner.nextInt();

            option = Arrays.stream(options).filter(s -> s.key() == selectedOption).findFirst().get();

            if (Objects.isNull(option)) {
                throw new IllegalArgumentException("Selected option is not valid.");
            }

        } catch (Exception e) {
            System.out.println("Invalid entry. Try again.\n");
        }

        ActionEnum.doCommandAction(option);
    }

}
