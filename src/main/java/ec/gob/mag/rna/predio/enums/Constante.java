package ec.gob.mag.rna.predio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Constante {

	actividad_agricola(873, "Actividad Agrícola"), actividad_agropecuaria(874, "Actividad Agropecuaria"),
	actividad_forestal(876, "Actividad Forestal"), actividad_pecuaria(875, "Actividad Pecuaria"),
	ft_propietario(509, "Propietario"), ft_arrendatario(510, "Arrendatario"), ft_aparcero(511, "Aparcero"),
	ft_comunero(531, "Comunero");

	private Integer codigo;
	private String detalle;
}
