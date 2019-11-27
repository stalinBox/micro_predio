package ec.gob.mag.rna.predio.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//============== LOMBOK =============

@Getter
@Setter
@ToString(of = "ubiId")
@EqualsAndHashCode(of = "ubiId")
@NoArgsConstructor
@AllArgsConstructor
@Builder

//========== JPA ======================

@Entity
@Table(name = "ubicacion", schema = "public")
public class Ubicacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4017650183258693515L;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla agrupacion")
	@Id
	@Column(name = "ubi_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("ubiId")
	private Long ubiId;

	@ApiModelProperty(value = " ***", position = 3)
	@JsonInclude(Include.NON_NULL)
	@Column(name = "cat_id_ubicacion")
	@JsonProperty("catIdUbicacion")
	private Long catIdUbicacion;

	@ApiModelProperty(value = " ***", position = 9)
	@JsonInclude(Include.NON_NULL)
	@Column(name = "ubi_nombre")
	@JsonProperty("ubiNombre")
	private String ubiNombre;

	@ApiModelProperty(value = " ***", position = 12)
	@JsonInclude(Include.NON_NULL)
	@Column(name = "ubi_estado")
	@JsonProperty("ubiEstado")
	private int ubiEstado;

	@ApiModelProperty(value = " ***", position = 18)
	@JsonInclude(Include.NON_NULL)
	@Column(name = "ubi_latitud")
	@JsonProperty("ubiLatitud")
	private Float ubiLatitud;

	@ApiModelProperty(value = " ***", position = 19)
	@JsonInclude(Include.NON_NULL)
	@Column(name = "ubi_longitud")
	@JsonProperty("ubiLongitud")
	private Float ubiLongitud;

}
