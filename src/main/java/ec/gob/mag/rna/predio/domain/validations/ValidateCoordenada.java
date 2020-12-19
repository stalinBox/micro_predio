package ec.gob.mag.rna.predio.domain.validations;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//============== LOMBOK =============
@Getter
@Setter
@ToString(of = "cordId")
//@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//========== JPA ======================
@Entity
public class ValidateCoordenada implements Serializable {
	private static final long serialVersionUID = -3124864204002344027L;

	@Id
	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla coordenada", position = 1)
	@Column(name = "cord_id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("cordId")
	@JsonInclude(Include.NON_NULL)
	private Long cordId;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla predio", position = 2)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pre_id")
	@JsonProperty("predio")
	@JsonInclude(Include.NON_NULL)
	private ValidatePredio validatePredio;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla predio_cultivo", position = 3)
	@Column(name = "pc_id")
	@JsonProperty("pcId")
	@JsonInclude(Include.NON_NULL)
	private Long pcId;

	@ApiModelProperty(value = "Coordenada geográfica X del predio", position = 4)
	@Column(name = "cord_x", length = 32)
	@JsonProperty("cordX")
	@JsonInclude(Include.NON_NULL)
	private String cordX;

	@ApiModelProperty(value = "Coordenada geográfica Y del predio", position = 5)
	@Column(name = "cord_y", length = 32)
	@JsonProperty("cordY")
	@JsonInclude(Include.NON_NULL)
	private String cordY;

	@ApiModelProperty(value = "Coordenada geográfica Z del predio", position = 6)
	@Column(name = "cord_z", length = 32)
	@JsonProperty("cordZ")
	@JsonInclude(Include.NON_NULL)
	private String cordZ;

	@ApiModelProperty(value = "Hemisferio Norte/Sur", position = 7)
	@Column(name = "cord_hemisferio", length = 16)
	@JsonProperty("cordHemisferio")
	@JsonInclude(Include.NON_NULL)
	private String cordHemisferio;

	@ApiModelProperty(value = "Zona 17/18", position = 8)
	@Column(name = "cord_zona", length = 16)
	@JsonProperty("cordZona")
	@JsonInclude(Include.NON_NULL)
	private String cordZona;

	@ApiModelProperty(value = "11=activo  12=inactivo", position = 9)
	@Column(name = "cord_estado")
	@JsonProperty("cordEstado")
	@JsonInclude(Include.NON_NULL)
	private Long cordEstado;

	@ApiModelProperty(value = "Este campo almacena los valores f =false para eliminado logico  y t= true para indicar que está activo", position = 10)
	@Column(name = "cord_eliminado", length = 5)
	@JsonProperty("cordEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean cordEliminado;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza el ingreso de información", position = 11)
	@Column(name = "cord_reg_usu", length = 10)
	@JsonProperty("cordRegUsu")
	@JsonInclude(Include.NON_NULL)
	@NotNull
	private Long cordRegUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que el usuario realiza el ingreso de información", position = 12)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "cord_reg_fecha")
	@JsonProperty("cordRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date cordRegFecha;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza la actualización de información")
	@Column(name = "cord_act_usu", length = 10)
	@JsonProperty("cordActUsu")
	@JsonInclude(Include.NON_NULL)
	private Long cordActUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que hizo la actualización el usuario", position = 13)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "cord_act_fecha")
	@JsonProperty("cordActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date cordActFecha;

	@Transient
	@ApiModelProperty(value = "Origen de Latitud y Longitud", position = 14)
	@JsonProperty("origenLatLong")
	@JsonInclude(Include.NON_NULL)
	private String origenLatLong;

	@Transient
	@ApiModelProperty(value = "Origen de Latitud y Longitud Parroquia Id", position = 15)
	@JsonProperty("origenLatLongUbiId")
	@JsonInclude(Include.NON_NULL)
	private Long origenLatLongUbiId;

	@Transient
	@ApiModelProperty(value = "Origen de Latitud y Longitud Parroquia Nombre", position = 16)
	@JsonProperty("origenLatLongUbiNombre")
	@JsonInclude(Include.NON_NULL)
	private String origenLatLongUbiNombre;

	@ApiModelProperty(value = "Latitud geografica", position = 17)
	@Column(name = "cord_latitud", length = 32)
	@JsonProperty("cordLatitud")
	@JsonInclude(Include.NON_NULL)
	private String cordLatitud;

	@ApiModelProperty(value = "Longitud geografica", position = 18)
	@Column(name = "cord_longitud", length = 32)
	@JsonProperty("cordLongitud")
	@JsonInclude(Include.NON_NULL)
	private String cordLongitud;

}
