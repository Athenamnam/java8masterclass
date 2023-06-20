package groupexercise.groupExercise_1.interfaces;

import java.lang.annotation.Repeatable;

@Repeatable(value = Options.class)
public @interface Option {
    String label();

    int key() default -1;
}
