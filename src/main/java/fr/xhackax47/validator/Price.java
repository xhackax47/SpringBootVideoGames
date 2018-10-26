package fr.xhackax47.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy= PriceValidator.class)
@Target(value= ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {

	String message() default "{validator.videoGame}";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
}