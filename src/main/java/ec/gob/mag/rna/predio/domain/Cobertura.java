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
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//============== LOMBOK =============
@Getter
@Setter
@ToString(of = "cobId")
//@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//========== JPA ======================

@Entity
@Table(name = "cobertura", schema = "sc_agricola")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ord", scope = Cobertura.class)

public class Cobertura implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla cobertura", position = 1)
	@Id
	@Column(name = "cob_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("cobId")
	private Long cobId;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla predio", position = 2)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pre_id", nullable = false)
	@JsonProperty("predio")
	@JsonInclude(Include.NON_NULL)
	private Predio predio;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla area_cultivo", position = 3)
	@Column(name = "cat_uso_suelo", nullable = false)
	@JsonProperty("catUsoSuelo")
	@JsonInclude(Include.NON_NULL)
	private Long catUsoSuelo;

	@ApiModelProperty(value = "Catalogo del tipo de uso de suelos Barbecho,Bosque Nativo,...", position = 4)
	@Column(name = "cob_cultivable", nullable = false)
	@JsonProperty("cobCultivable")
	@JsonInclude(Include.NON_NULL)
	private Boolean cobCultivable;

	@ApiModelProperty(value = "Superficie en Hectareas", position = 5)
	@Column(name = "cob_superficie", nullable = false)
	@JsonProperty("cobSuperficie")
	@JsonInclude(Include.NON_NULL)
	private Long cobSuperficie;

	@ApiModelProperty(value = "11=activo  12=inactivo", position = 6)
	@Column(name = "cob_estado", nullable = false)
	@JsonProperty("cobEstado")
	@JsonInclude(Include.NON_NULL)
	private Long cobEstado;

	@ApiModelProperty(value = "Este campo almacena los valores f =false para eliminado logico  y t= true para indicar que está activo", position = 7)
	@Column(name = "cob_eliminado", length = 5)
	@JsonProperty("cobEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean cobEliminado;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza el ingreso de información, Campo que almacena el usuario que registra a la persona", position = 8)
	@Column(name = "cob_reg_usu", length = 10)
	@JsonProperty("cobRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Long cobRegUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que el usuario realiza el ingreso de información", position = 9)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "cob_reg_fecha")
	@JsonProperty("cobRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date cobRegFecha;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza la actualización de información", position = 10)
	@Column(name = "cob_act_usu", length = 10)
	@JsonProperty("cobActUsu")
	@JsonInclude(Include.NON_NULL)
	private Long cobActUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que hizo la actualización el usuario", position = 11)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "cob_act_fecha")
	@JsonProperty("cobActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date cobActFecha;

	@PrePersist
	public void prePersist() {
		this.cobEstado = (long) 11;
		this.cobEliminado = false;
		this.cobRegFecha = new Date();
	}

	@PreUpdate
	void preUpdate() {
		this.cobActFecha = new Date();
	}
}
