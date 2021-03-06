package ec.gob.mag.rna.predio.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@ToString(of = "preId")
//@EqualsAndHashCode(of="preId")
@NoArgsConstructor
@AllArgsConstructor
@Builder
//========== JPA ======================
@Entity
@Table(name = "predio", schema = "sc_agricola")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ord", scope = Predio.class)

public class Predio implements java.io.Serializable {

	private static final long serialVersionUID = -2631428883075299326L;

	@Id
	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla predio")
	@Column(name = "pre_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("preId")
	private Long preId;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla ubicacion")
	@Column(name = "ubi_id", unique = true, nullable = false)
	@JsonProperty("ubiId")
	@JsonInclude(Include.NON_NULL)
	private Long ubiId;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla persona_tipo")
	@Column(name = "peti_id", unique = true, nullable = false)
	@JsonProperty("petiId")
	@JsonInclude(Include.NON_NULL)
	private Long petiId;

	@ApiModelProperty(value = "Catalogo de tipo de tenencia del predio  Propietario, Arrendatario y Aparcero")
	@Column(name = "cat_id_pro_arren", nullable = false)
	@JsonProperty("catIdProArren")
	@JsonInclude(Include.NON_NULL)
	private Long catIdProArren;

	@ApiModelProperty(value = "Unidad de medida del predio en Hect??reas")
	@Column(name = "cat_unidad_medida", nullable = false)
	@JsonProperty("catUnidadMedida")
	@JsonInclude(Include.NON_NULL)
	private Long catUnidadMedida;

	@ApiModelProperty(value = "Si tiene o no catastro True/False")
	@Column(name = "pre_sin_catastro", unique = true, length = 5)
	@JsonProperty("preSinCatastro")
	@JsonInclude(Include.NON_NULL)
	private Boolean preSinCatastro;

	@ApiModelProperty(value = "Clave catastral Alfanum??rica")
	@Column(name = "pre_clave_catastral", length = 35)
	@JsonProperty("preClaveCatastral")
	@JsonInclude(Include.NON_NULL)
	private String preClaveCatastral;

	@ApiModelProperty(value = "Predio nombre")
	@Column(name = "pre_nombre", length = 256)
	@JsonProperty("preNombre")
	@JsonInclude(Include.NON_NULL)
	private String preNombre;

	@ApiModelProperty(value = "Predio direccion")
	@Column(name = "pre_direccion", length = 256)
	@JsonProperty("preDireccion")
	@JsonInclude(Include.NON_NULL)
	private String preDireccion;

	@ApiModelProperty(value = "Predio Nombre recinto")
	@Column(name = "pre_nom_recinto", length = 64)
	@JsonProperty("preNomRecinto")
	@JsonInclude(Include.NON_NULL)
	private String preNomRecinto;

	@ApiModelProperty(value = "Superficie total en  Hectareas del predio")
	@Column(name = "pre_superficie_total", nullable = false)
	@JsonProperty("preSuperficieTotal")
	@JsonInclude(Include.NON_NULL)
	private Float preSuperficieTotal;

	@ApiModelProperty(value = "Superfice cultivable del predio")
	@Column(name = "pre_superfice_cultivable", nullable = false)
	@JsonProperty("preSuperficeCultivable")
	@JsonInclude(Include.NON_NULL)
	private Float preSuperficeCultivable;

	@ApiModelProperty(value = "Fecha Inicio de  arriendo del predio")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pre_fecha_ini_arriendo")
	@JsonProperty("preFechaIniArriendo")
	@JsonInclude(Include.NON_NULL)
	private Date preFechaIniArriendo;

	@ApiModelProperty(value = "Fecha Final de arriendo  del predio")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pre_fecha_fin_arriendo")
	@JsonProperty("preFechaFinArriendo")
	@JsonInclude(Include.NON_NULL)
	private Date preFechaFinArriendo;

	@ApiModelProperty(value = "Area escritura del predio")
	@Column(name = "sn_area_escritura", nullable = false)
	@JsonProperty("snAreaEscritura")
	@JsonInclude(Include.NON_NULL)
	private Float snAreaEscritura;

	@ApiModelProperty(value = "Tipo predio")
	@Column(name = "sn_tipo_predio", length = 64)
	@JsonProperty("snTipoPredio")
	@JsonInclude(Include.NON_NULL)
	private String snTipoPredio;

	@ApiModelProperty(value = "Codigo provincia")
	@Column(name = "sn_codigo_provincia", nullable = false)
	@JsonProperty("snCodigoProvincia")
	@JsonInclude(Include.NON_NULL)
	private Long snCodigoProvincia;

	@ApiModelProperty(value = "Codigo canton")
	@Column(name = "sn_codigo_canton", length = 4)
	@JsonProperty("snCodigoCanton")
	@JsonInclude(Include.NON_NULL)
	private String snCodigoCanton;

	@ApiModelProperty(value = "Codigo parroquia")
	@Column(name = "sn_codigo_parroquia", length = 6)
	@JsonProperty("snCodigoParroquia")
	@JsonInclude(Include.NON_NULL)
	private String snCodigoParroquia;

	@ApiModelProperty(value = "Codigo poligono")
	@Column(name = "sn_codigo_poligono", length = 9)
	@JsonProperty("snCodigoPoligono")
	@JsonInclude(Include.NON_NULL)
	private String snCodigoPoligono;

	@ApiModelProperty(value = "Codigo predio")
	@Column(name = "sn_codigo_predio", length = 128)
	@JsonProperty("snCodigoPredio")
	@JsonInclude(Include.NON_NULL)
	private String snCodigoPredio;

	@ApiModelProperty(value = "Sector ubicaci??n del predio")
	@Column(name = "sn_sector", length = 80)
	@JsonProperty("snSector")
	@JsonInclude(Include.NON_NULL)
	private String snSector;

	@ApiModelProperty(value = "Clave_Catastral anterior del predio")
	@Column(name = "sn_clave_catastral_anterior", length = 13)
	@JsonProperty("snClaveCatastralAnterior")
	@JsonInclude(Include.NON_NULL)
	private String snClaveCatastralAnterior;

	@ApiModelProperty(value = "En_Conflicto propietarios del predio True/False")
	@Column(name = "sn_en_conflicto_propietarios", length = 5)
	@JsonProperty("snEnConflictoPropietarios")
	@JsonInclude(Include.NON_NULL)
	private Boolean snEnConflictoPropietarios;

	@ApiModelProperty(value = "Territorio comunal  True/False")
	@Column(name = "sn_territorio_comunal", length = 5)
	@JsonProperty("snTerritorioComunal")
	@JsonInclude(Include.NON_NULL)
	private Boolean snTerritorioComunal;

	@ApiModelProperty(value = "Utilidad publica True/False")
	@Column(name = "sn_utilidad_publica", length = 5)
	@JsonProperty("snUtilidadPublica")
	@JsonInclude(Include.NON_NULL)
	private Boolean snUtilidadPublica;

	@ApiModelProperty(value = "Historial de cambios en el predio")
	@Column(name = "pre_log", length = 8190)
	@JsonProperty("preLog")
	@JsonInclude(Include.NON_NULL)
	private String preLog;

	@ApiModelProperty(value = "11=activo  12=inactivo")
	@Column(name = "pre_estado", length = 10)
	@JsonProperty("preEstado")
	@JsonInclude(Include.NON_NULL)
	private Long preEstado;

	@ApiModelProperty(value = "Este campo almacena los valores f =false para eliminado logico  y t= true para indicar que est?? activo")
	@Column(name = "pre_eliminado", length = 5)
	@JsonProperty("preEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean preEliminado;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza el ingreso de informaci??n, Campo que almacena el usuario que registra a la persona")
	@Column(name = "pre_reg_usu", length = 10)
	@JsonProperty("preRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Long preRegUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que el usuario realiza el ingreso de informaci??n")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name = "pre_reg_fecha", length = 10)
	@JsonProperty("preRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date preRegFecha;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza la actualizaci??n de informaci??n")
	@Column(name = "pre_act_usu", length = 10)
	@JsonProperty("preActUsu")
	@JsonInclude(Include.NON_NULL)
	private Long preActUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que hizo la actualizaci??n el usuario")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name = "pre_act_fecha")
	@JsonProperty("preActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date preActFecha;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla boleta")
	@Column(name = "blt_id", length = 10)
	@JsonProperty("bltId")
	@JsonInclude(Include.NON_NULL)
	private Long bltId;

	@ApiModelProperty(value = "El productor o propietario vive en el terreno:S,N")
	@Column(name = "pre_vive_terreno", length = 1)
	@JsonProperty("preViveTerreno")
	@JsonInclude(Include.NON_NULL)
	private String preViveTerreno;

	@ApiModelProperty(value = "Numero de Personas que no trabajan")
	@Column(name = "pre_num_personas_no_remuneradas", length = 10)
	@JsonProperty("preNumPersonasNoRemuneradas")
	@JsonInclude(Include.NON_NULL)
	private Long preNumPersonasNoRemuneradas;

	@ApiModelProperty(value = "Numero de Personas que trabajan")
	@Column(name = "pre_num_personas_remuneradas", length = 10)
	@JsonProperty("preNumPersonasRemuneradas")
	@JsonInclude(Include.NON_NULL)
	private Long preNumPersonasRemuneradas;

	@ApiModelProperty(value = "Conoce coordenadas del predio")
	@Column(name = "pre_conoce_coord", length = 1)
	@JsonProperty("preConoceCoord")
	@JsonInclude(Include.NON_NULL)
	private String preConoceCoord;

	@ApiModelProperty(value = "C??dula del due??o del predio")
	@Column(name = "pre_duenio_cedula", length = 30)
	@JsonProperty("preDuenioCedula")
	@JsonInclude(Include.NON_NULL)
	private String preDuenioCedula;

	@ApiModelProperty(value = "Nombres del due??o del predio")
	@Column(name = "pre_duenio_nombres", length = 100)
	@JsonProperty("preDuenioNombres")
	@JsonInclude(Include.NON_NULL)
	private String preDuenioNombres;

	@ApiModelProperty(value = "Numero de Tel??fono del due??o del predio")
	@Column(name = "pre_duenio_telefono", length = 64)
	@JsonProperty("preDuenioTelefono")
	@JsonInclude(Include.NON_NULL)
	private String preDuenioTelefono;

	@ApiModelProperty(value = "Numero de Tel??fono celular del due??o del predio")
	@Column(name = "pre_duenio_celular", length = 64)
	@JsonProperty("preDuenioCelular")
	@JsonInclude(Include.NON_NULL)
	private String preDuenioCelular;

	@ApiModelProperty(value = "Direccion de correo del due??o del predio")
	@Column(name = "pre_duenio_correo", length = 128)
	@JsonProperty("preDuenioCorreo")
	@JsonInclude(Include.NON_NULL)
	private String preDuenioCorreo;

	@ApiModelProperty(value = "Id de la Organizacion")
	@Column(name = "org_id")
	@JsonProperty("orgId")
	@JsonInclude(Include.NON_NULL)
	private Long orgId;

	@ApiModelProperty(value = "Categoria actividad Principal")
	@Column(name = "cat_actividad_principal")
	@JsonProperty("catActividadPrincipal")
	@JsonInclude(Include.NON_NULL)
	private Long catActividadPrincipal;

	@ApiModelProperty(value = "Cultivo id agricola")
	@Column(name = "cul_id_agricola")
	@JsonProperty("culIdAgricola")
	@JsonInclude(Include.NON_NULL)
	private Long culIdAgricola;

	@ApiModelProperty(value = "Id de forestal")
	@Column(name = "cul_id_forestal")
	@JsonProperty("culIdForestal")
	@JsonInclude(Include.NON_NULL)
	private Long culIdForestal;

	@ApiModelProperty(value = "Id pecuario")
	@Column(name = "cat_id_pecuario")
	@JsonProperty("catIdPecuario")
	@JsonInclude(Include.NON_NULL)
	private Long catIdPecuario;

	@ApiModelProperty(value = "Predio inactivo")
	@Column(name = "pre_inactiva_observa", length = 128)
	@JsonProperty("preInactivaObserva")
	@JsonInclude(Include.NON_NULL)
	private String preInactivaObserva;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla area_cultivo")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "predio", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@Fetch(value = FetchMode.JOIN)
	@JsonProperty("coordenadas")
	@JsonInclude(Include.NON_NULL)
	private Set<Coordenada> coordenadas;

	@Fetch(value = FetchMode.JOIN)
	@ApiModelProperty(value = "Campo areaCultivos Mapeado por predio")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "predio", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JsonProperty("areaCultivos")
	@JsonInclude(Include.NON_NULL)
	private Set<AreaCultivo> areaCultivos;

	@ApiModelProperty(value = "Campo coberturas Mapeado por predio")
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "predio", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@Fetch(value = FetchMode.JOIN)
	@JsonProperty("coberturas")
	@JsonInclude(Include.NON_NULL)
	private Set<Cobertura> coberturas;

	@PrePersist
	public void prePersist() {
		this.preEstado = (long) 11;
		this.preEliminado = false;
		this.preActFecha = new Date();
	}

	@PreUpdate
	void preUpdate() {
		this.preActFecha = new Date();
	}

}
