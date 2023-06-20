package groupexercise.groupExercise_1.model;

import groupexercise.groupExercise_1.enums.SortEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSelection {

    private SortEnum sortEnum;

    private Integer action;

    private Boolean order;


}
