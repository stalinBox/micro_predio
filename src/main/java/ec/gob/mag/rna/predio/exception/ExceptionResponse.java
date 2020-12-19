package ec.gob.mag.rna.predio.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
	private HttpStatus status;
	private Date timestamp;
	private String message;
	private String details;
	private String detailsJson;
}
