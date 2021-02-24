package ec.gob.mag.rna.predio.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "cultivo", schema = "sc_agricola")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ord", scope = Cultivo.class)
public class Cultivo implements Serializable {
	private static final long serialVersionUID = 1722665206845571246L;
	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla area_cultivo", position = 1)
	@Id
	@Column(name = "cul_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("culId")
	private Long id;

	@ApiModelProperty(value = "Nombre de los cultivos", example = "cebada, trigo, etc")
	@Column(name = "cul_nombre")
	@NotEmpty(message = "_error.validation_blank.message")
	@JsonProperty("culNombre")
	@JsonInclude(Include.NON_NULL)
	private String culNombre;
	/*****************************************************
	 * SECCION - CAMPOS POR DEFECTO EN TODAS LAS ENTIDADES
	 *****************************************************/
	@ApiModelProperty(value = "11=activo  12=inactivo", required = true, allowableValues = "11=>activo, 12=>inactivo", example = "11")
	@Column(name = "cul_estado", columnDefinition = "Integer default 11")
	@JsonProperty("culEstado")
	@JsonInclude(Include.NON_NULL)
	private Integer culEstado;

	@ApiModelProperty(value = "Fecha de registro del campo", example = "")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cul_reg_fecha", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@JsonProperty("culRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date culRegFecha;

	@ApiModelProperty(value = "Id de usuario que creó el regristro", example = "")
	@Column(name = "cul_reg_usu", nullable = false)
	@JsonProperty("culRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Long culRegUsu;

	@ApiModelProperty(value = "Fecha en la que hizo la actualización del registro", example = "")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cul_act_fecha")
	@JsonProperty("culActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date culActFecha;

	@ApiModelProperty(value = "Id de usuario que actualizacio del campo", example = "")
	@Column(name = "cul_act_usu")
	@JsonProperty("culActUsu")
	private Long culActUsu;

	@ApiModelProperty(value = "Este campo almacena los valores f =false para eliminado logico  y t= true para indicar que está activo", required = true, allowableValues = "false=>no eliminado lógico, true=> eliminado lógico", example = "")
	@Column(name = "cul_eliminado", columnDefinition = "boolean default false")
	@JsonProperty("culEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean culEliminado;

	@PrePersist
	void prePersist() {
		this.culEstado = 11;
		this.culEliminado = false;
		this.culRegFecha = new Date();
	}

	@PreUpdate
	void preUpdate() {
		this.culActFecha = new Date();
	}
}
