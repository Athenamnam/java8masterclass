package groupexercise.groupExercise_1.interfaces;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Options {
    Option[] value() default {};
}
