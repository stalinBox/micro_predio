package ec.gob.mag.rna.predio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseUpdate {
	private String operacion;
	private Long id;
}
