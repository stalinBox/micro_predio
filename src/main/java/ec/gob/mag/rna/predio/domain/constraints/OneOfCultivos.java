package ec.gob.mag.rna.predio.domain.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = OneOfCultivosValidator.class)
public @interface OneOfCultivos {

	String message() default "Solo se aceptan catCodigos del tipo de catalogos: 74. Se sugiere revisar la ficha técnica del servicio ";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean required() default false;

}