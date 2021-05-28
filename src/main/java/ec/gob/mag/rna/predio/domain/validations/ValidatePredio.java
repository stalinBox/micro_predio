package ec.gob.mag.rna.predio.domain.validations;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ec.gob.mag.rna.predio.domain.constraints.CedulaVerificador;
import ec.gob.mag.rna.predio.domain.constraints.OneOfCultivos;
import ec.gob.mag.rna.predio.domain.constraints.OneOfInteger;
import ec.gob.mag.rna.predio.domain.constraints.OneOfString;
import ec.gob.mag.rna.predio.domain.constraints.PredioVerificadorExcluyente;
import ec.gob.mag.rna.predio.domain.constraints.PredioVerificadorIncluyente;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
//========== JPA ======================
@Entity

@PredioVerificadorIncluyente(selected = "catActividadPrincipal", values = { "1119", }, required = { "culIdAgricola" })
@PredioVerificadorIncluyente(selected = "catActividadPrincipal", values = { "1122" }, required = { "culIdForestal" })
@PredioVerificadorIncluyente(selected = "catActividadPrincipal", values = { "1121" }, required = { "catIdPecuario" })

@PredioVerificadorExcluyente(selected = "catActividadPrincipal", values = { "1120" }, required = { "catIdPecuario",
		"culIdAgricola" })

@PredioVerificadorIncluyente(selected = "catIdProArren", values = { "510", "511" }, required = { "preDuenioCedula" })
@PredioVerificadorIncluyente(selected = "catIdProArren", values = { "510", "511" }, required = { "preDuenioNombres" })
@PredioVerificadorIncluyente(selected = "catIdProArren", values = { "510", "511" }, required = { "preDuenioTelefono" })
@PredioVerificadorIncluyente(selected = "catIdProArren", values = { "510", "511" }, required = { "preDuenioCelular" })
@PredioVerificadorIncluyente(selected = "catIdProArren", values = { "510", "511" }, required = { "preDuenioCorreo" })

public class ValidatePredio {

	@Id
	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla predio")
	@JsonProperty("preId")
	private Integer preId;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla ubicacion")
	@JsonProperty("ubiId")
	@JsonInclude(Include.NON_NULL)
	@NotNull
	private Integer ubiId;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla persona_tipo")
	@JsonProperty("petiId")
	@JsonInclude(Include.NON_NULL)
	@NotNull
	private Integer petiId;

	@ApiModelProperty(value = "Catalogo de tipo de tenencia del predio: 509=Propietario, 510=Arrendatario, 511=Aparcero, 531=COMUNERO")
	@JsonProperty("catIdProArren")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "_error.validation_blank.message")
	@OneOfInteger(value = { 509, 510, 511, 531 }, domainShow = "[509,510,511,531]")
	private Integer catIdProArren;

	@ApiModelProperty(value = "Unidad de medida del predio en Hectáreas codigo de catalogo: 470")
	@JsonProperty("catUnidadMedida")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "_error.validation_blank.message")
	@OneOfInteger(value = { 470 }, domainShow = "[470]")
	private Integer catUnidadMedida;

	@ApiModelProperty(value = "Clave catastral Alfanumérica")
	@JsonProperty("preClaveCatastral")
	@JsonInclude(Include.NON_NULL)
	private String preClaveCatastral;

	@ApiModelProperty(value = "Predio nombre")
	@JsonProperty("preNombre")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "_error.validation_blank.message")
	@NotEmpty
	private String preNombre;

	@ApiModelProperty(value = "Predio direccion")
	@JsonProperty("preDireccion")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "_error.validation_blank.message")
	@NotEmpty
	private String preDireccion;

	@ApiModelProperty(value = "Superficie total en  Hectareas del predio")
	@JsonProperty("preSuperficieTotal")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "_error.validation_blank.message")
	private Float preSuperficieTotal;

	@ApiModelProperty(value = "Superfice cultivable del predio")
	@JsonProperty("preSuperficeCultivable")
	@JsonInclude(Include.NON_NULL)
	private Float preSuperficeCultivable;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza el ingreso de información, Campo que almacena el usuario que registra a la persona")
	@JsonProperty("preRegUsu")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "_error.validation_blank.message")
	private Integer preRegUsu;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla boleta")
	@JsonProperty("bltId")
	@JsonInclude(Include.NON_NULL)
	private Integer bltId;

	@ApiModelProperty(value = "El productor o propietario vive en el terreno:S,N")
	@JsonProperty("preViveTerreno")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "_error.validation_blank.message")
	@NotEmpty
	@OneOfString(value = { "S", "N" })
	private String preViveTerreno;

	@ApiModelProperty(value = "Categoria actividad Principal")
	@JsonProperty("catActividadPrincipal")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "_error.validation_blank.message")
	@OneOfInteger(required = false, value = { 1119, 1120, 1121, 1122 }, domainShow = "[1119, 1120, 1121, 1122]")
	private Integer catActividadPrincipal;

	@ApiModelProperty(value = "Cultivo id agricola")
	@JsonProperty("culIdAgricola")
	@OneOfCultivos(required = false)
	private Integer culIdAgricola;

	@ApiModelProperty(value = "Id de forestal")
	@JsonProperty("culIdForestal")
	@OneOfInteger(required = false, value = { 102, 103, 104, 135, 146, 175, 18, 186, 193, 211, 248, 273, 40, 63, 80, 82,
			92 }, domainShow = "[102, 103, 104, 135, 146, 175, 18, 186, 193, 211, 248, 273, 40, 63, 80, 82, 92]")
	private Integer culIdForestal;

	@ApiModelProperty(value = "Id pecuario")
	@JsonProperty("catIdPecuario")
	@OneOfInteger(required = false, value = { 1132, 1133, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860,
			861, 862, 863,
			869 }, domainShow = "[1132, 1133, 849, 850, 851, 852, 853, 854, 855, 856, 857, 858, 859, 860, 861, 862, 863, 869]")
	private Integer catIdPecuario;

	@ApiModelProperty(value = "Conoce coordenadas del predio")
	@JsonProperty("preConoceCoord")
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "_error.validation_blank.message")
	@NotEmpty
	@OneOfString(value = { "S", "N" })
	private String preConoceCoord;

	@ApiModelProperty(value = "Cédula del dueño del predio")
	@JsonProperty("preDuenioCedula")
	@JsonInclude(Include.NON_NULL)
	@CedulaVerificador
	private String preDuenioCedula;

	@ApiModelProperty(value = "Nombres del dueño del predio")
	@JsonProperty("preDuenioNombres")
	@JsonInclude(Include.NON_NULL)
	private String preDuenioNombres;

	@ApiModelProperty(value = "Numero de Teléfono del dueño del predio")
	@JsonProperty("preDuenioTelefono")
	@JsonInclude(Include.NON_NULL)
	private String preDuenioTelefono;

	@ApiModelProperty(value = "Numero de Teléfono celular del dueño del predio")
	@JsonProperty("preDuenioCelular")
	@JsonInclude(Include.NON_NULL)
	private String preDuenioCelular;

	@ApiModelProperty(value = "Direccion de correo del dueño del predio")
	@JsonProperty("preDuenioCorreo")
	@JsonInclude(Include.NON_NULL)
	@Email(message = "_error.validation_valid_mail.message")
	private String preDuenioCorreo;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que el usuario realiza el ingreso de información")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonProperty("preRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date preRegFecha;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza la actualización de información")
	@Column(name = "pre_act_usu", length = 10)
	@JsonProperty("preActUsu")
	@JsonInclude(Include.NON_NULL)
	private Integer preActUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que hizo la actualización el usuario")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@JsonProperty("preActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date preActFecha;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla area_cultivo")
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@Fetch(value = FetchMode.JOIN)
	@JsonProperty("coordenadas")
	@JsonInclude(Include.NON_NULL)
	private Set<ValidateCoordenada> coordenadas;

}
