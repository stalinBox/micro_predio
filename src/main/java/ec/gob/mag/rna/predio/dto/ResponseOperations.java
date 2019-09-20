package ec.gob.mag.rna.predio.dto;

import java.util.Date;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseOperations {
	@ApiModelProperty(notes =  "Nombre de la operacion", position=1)
	private String entidad;
	@ApiModelProperty(notes =  "Resultado de la operacion", position=2)
	private Integer id;
}
