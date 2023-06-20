package groupexercise.groupExercise_1.menu;

import groupexercise.groupExercise_1.enums.SortEnum;
import groupexercise.groupExercise_1.interfaces.CommandAction;
import groupexercise.groupExercise_1.interfaces.Option;
import groupexercise.groupExercise_1.model.UserSelection;

import static groupexercise.groupExercise_1.constants.MenuConstants.*;

@Option(label = SORT_ASCENDING, key = 1)
@Option(label = SORT_DESCENDING, key = 2)
@Option(label = BACK, key = -1)
public class SortEmployeeAction extends ActionAbstract implements CommandAction {

    private UserSelection userSelection;

    public SortEmployeeAction(UserSelection userSelection) {
        this.userSelection = userSelection;
    }

    @Override
    public void doAction() {
        Option option = generateMessageAndAskUserInput();

        if (option.key() == -1) return;

        userSelection.setAction(option.key());
        userSelection.setOrder(option.label().equalsIgnoreCase(SORT_ASCENDING));
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
