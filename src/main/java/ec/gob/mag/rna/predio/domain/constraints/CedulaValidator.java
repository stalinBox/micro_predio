package ec.gob.mag.rna.predio.domain.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ec.gob.mag.rna.predio.util.Util;

public class CedulaValidator implements ConstraintValidator<CedulaVerificador, Object> {

	private boolean required;

	@Override
	public void initialize(CedulaVerificador constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Util util = new Util();
		if (required) {
			return util.verificarCedula(value.toString());
		} else {
			if (value != null) {
				return util.verificarCedula(value.toString());
			}
			return true;
		}
	}
}