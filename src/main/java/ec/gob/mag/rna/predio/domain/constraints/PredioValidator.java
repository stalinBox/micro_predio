package ec.gob.mag.rna.predio.domain.constraints;

import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import static org.springframework.util.StringUtils.isEmpty;

public class PredioValidator implements ConstraintValidator<PredioVerificador, Object> {

	private String selected;
	private String[] required;
	private String message;
	private String[] values;

	@Override
	public void initialize(PredioVerificador constraintAnnotation) {
		this.selected = constraintAnnotation.selected();
		this.required = constraintAnnotation.required();
		this.message = constraintAnnotation.message();
		this.values = constraintAnnotation.values();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		Boolean valid = true;
		try {
			Object checkedValue = BeanUtils.getProperty(object, selected);
			if (Arrays.asList(values).contains(checkedValue)) {

				for (String propName : required) {
					Object requiredValue = BeanUtils.getProperty(object, propName);
					valid = requiredValue != null && !isEmpty(requiredValue);
					if (!valid) {
						context.disableDefaultConstraintViolation();
						context.buildConstraintViolationWithTemplate(message).addPropertyNode(propName)
								.addConstraintViolation();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}
}