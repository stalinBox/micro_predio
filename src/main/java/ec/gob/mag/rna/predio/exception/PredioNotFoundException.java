package ec.gob.mag.rna.predio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PredioNotFoundException extends RuntimeException {
	public PredioNotFoundException(String message) {
		super(message);
	}
}
