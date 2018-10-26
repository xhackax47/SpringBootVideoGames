package fr.xhackax47.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import fr.xhackax47.model.VideoGame;

public class PriceValidator implements ConstraintValidator<Price, Integer> {
	
	public void initialize(VideoGame constraintAnnotation) {}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		return value > 0 && value < 70;
	}

}
