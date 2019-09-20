package ec.gob.mag.rna.predio.domain;

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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ec.gob.mag.rna.predio.util.Util;
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
@ToString(of = "acId")
//@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//========== JPA ======================

@Entity
@Table(name = "area_cultivo", schema = "sc_agricola")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ord", scope = AreaCultivo.class)
public class AreaCultivo implements Serializable {
	private static final long serialVersionUID = 1722665206845571246L;
	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla area_cultivo", position = 1)
	@Id
	@Column(name = "ac_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("acId")
	private Long acId;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla predio", position = 2)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pre_id", nullable = false)
	@JsonProperty("predio")
	@JsonInclude(Include.NON_NULL)
	private Predio predio;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla boleta", position = 3)
	@Column(name = "blt_id", unique = true, nullable = false)
	@JsonProperty("bltId")
	private Long bltId;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla area_cultivo", position = 4)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ac_fecha_siembra")
	@JsonProperty("acFechaDiembra")
	@JsonInclude(Include.NON_NULL)
	private Date acFechaDiembra;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla area_cultivo", position = 4)
	@Column(name = "ac_superficie_cultivo", nullable = false)
	@JsonProperty("acSuperficieCultivo")
	private Long acSuperficieCultivo;

	@ApiModelProperty(value = "Este campo almacena el código del usuario que realiza el ingreso de información", position = 5)
	@Column(name = "ac_reg_usu", nullable = false)
	@JsonProperty("acRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Long acRegUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que el usuario realiza el ingreso de información", position = 6)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "ac_reg_fecha")
	@JsonProperty("acRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date acRegFecha;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza la actualización de información", position = 7)
	@Column(name = "ac_act_usu", length = 10)
	@JsonProperty("acActUsu")
	@JsonInclude(Include.NON_NULL)
	private Long acActUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que hizo la actualización el usuario", position = 8)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "ac_act_fecha")
	@JsonProperty("acActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date acActFecha;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla cultivo")
	@Column(name = "cul_id", nullable = false)
	@JsonProperty("culId")
	@JsonInclude(Include.NON_NULL)
	private Long culId;

	@ApiModelProperty(value = "Catálogo de la unidad de medida del área de cultivo en Hectáreas", position = 9)
	@Column(name = "cat_unidad_medida", nullable = false)
	@JsonProperty("catUnidadMedida")
	@JsonInclude(Include.NON_NULL)
	private Long catUnidadMedida;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla ciclos", position = 10)
	@Column(name = "cic_id", nullable = false)
	@JsonProperty("cicId")
	@JsonInclude(Include.NON_NULL)
	private Long cicId;

	@ApiModelProperty(value = "11=activo  12=inactivo", position = 11)
	@Column(name = "ac_estado", nullable = false)
	@JsonProperty("acEstado")
	@JsonInclude(Include.NON_NULL)
	private Long acEstado;

	@ApiModelProperty(value = "Este campo almacena los valores f =false para eliminado logico  y t= true para indicar que está activo", position = 12)
	@Column(name = "ac_eliminado", length = 5)
	@JsonProperty("acEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean acEliminado;

	@PrePersist
	public void prePersist() {
		this.acRegFecha = Util.dateNow();
		this.acEstado = 11L;
		this.acActFecha = null;
		this.acActUsu = null;
		this.acEliminado = false;
	}
}
