package ec.gob.mag.rna.predio.domain.constraints;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.common.base.Objects;

public class OneOfIntegerValidator implements ConstraintValidator<OneOfInteger, Object> {

	private int[] values = new int[] {};
	private boolean required;

	@Override
	public void initialize(OneOfInteger constraintAnnotation) {
		this.values = constraintAnnotation.value();
		this.required = constraintAnnotation.required();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (required) {
			return Arrays.stream(values).anyMatch(s -> Objects.equal(value, s));
		} else {
			if (value != null) {
				return Arrays.stream(values).anyMatch(s -> Objects.equal(value, s));
			}
			return true;
		}
	}
}