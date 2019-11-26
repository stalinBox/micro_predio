package ec.gob.mag.rna.predio.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

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

	/*
	@ApiModelProperty(value = " ***", position = 20)
	@JsonInclude(Include.NON_NULL)
	@Column(name = "ubi_superficie")
	@JsonProperty("ubiSuperficie")
	private Float ubiSuperficie;

	@ApiModelProperty(value = " ***", position = 21)
	@JsonInclude(Include.NON_NULL)
	@Column(name = "ubi_longitud_min")
	@JsonProperty("ubiLongitudMin")
	private Float ubiLongitudMin;

	@ApiModelProperty(value = " ***", position = 22)
	@JsonInclude(Include.NON_NULL)
	@Column(name = "ubi_longitud_max")
	@JsonProperty("ubiLongitudMax")
	private Float ubiLongitudMax;


	@ApiModelProperty(value = " ***", position = 32)
	@JsonInclude(Include.NON_NULL)
	@Column(name = "ubi_latitud_min")
	@JsonProperty("ubiLatitudMin")
	private Float ubiLatitudMin;
*/
}
