package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.enums.SortEnum;
import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.interfaces.Option;
import groupexercise.groupExercise_1.model.UserSelection;

import static groupexercise.groupExercise_1.constants.MenuConstants.BACK;
import static groupexercise.groupExercise_1.constants.MenuConstants.CHOOSE_ACTION_HEADER;
import static groupexercise.groupExercise_1.constants.MenuConstants.SELECT_ACTION;
import static groupexercise.groupExercise_1.constants.MenuConstants.SORT_EMPLOYEE_NUMBER;
import static groupexercise.groupExercise_1.constants.MenuConstants.SORT_FIRST_NAME;
import static groupexercise.groupExercise_1.constants.MenuConstants.SORT_HIRING_DATE;
import static groupexercise.groupExercise_1.constants.MenuConstants.SORT_LAST_NAME;

@Option(label = SORT_EMPLOYEE_NUMBER, key = 1)
@Option(label = SORT_FIRST_NAME, key = 2)
@Option(label = SORT_LAST_NAME, key = 3)
@Option(label = SORT_HIRING_DATE, key = 4)
@Option(label = BACK, key = -1)
public class SortMenuAction extends ActionAbstract implements CommandAction {

    private UserSelection userSelection;

    public SortMenuAction(UserSelection userSelection) {
        this.userSelection = userSelection;
    }

    @Override
    public void doAction() {
        while (true) {
            Option option = generateMessageAndAskUserInput();

            if (option == null) {
                continue;
            }

            if (option.key() == -1) return;

            userSelection.setAction(option.key());
            SortEnum sortEnum = SortEnum.getSortEnum(option.label());
            userSelection.setSortEnum(sortEnum);
            return;

        }

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
}
