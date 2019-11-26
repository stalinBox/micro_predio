package ec.gob.mag.rna.predio.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import ec.gob.mag.rna.predio.domain.Ubicacion;
import ec.gob.mag.rna.predio.exception.UbicacionNotFoundException;
import ec.gob.mag.rna.predio.repository.UbicacionRepository;

/**
 * Clase UbicacionService.
 *
 * @author PITPPA
 * @version final
 */


@Service("ubicacionService")
public class UbicacionService {
	@Autowired
	@Qualifier("ubicacionRepository")
	private UbicacionRepository ubicacionRepository;
	@Autowired
	private MessageSource messageSource;

	
	/**
	 * Devuelve todas las ubicaciones  
	 *
	 * @return List<Ubicacion>
	 */
	public List<Ubicacion> findAll() {
		List<Ubicacion> ubicaciones = ubicacionRepository.findAll();
		if (ubicaciones.isEmpty())
			throw new UbicacionNotFoundException(String.format(
					messageSource.getMessage("error.entity_cero_exist.message", null, LocaleContextHolder.getLocale()),
					this.getClass().getName()));

		return ubicaciones;
	}

	
	/**
	 * Busca una Ubicación por ubiId
	 *
	 * @param Long ubiId
	 *
	 * @return Ubicación, que cumple la condición.
	 */
	public Ubicacion findByUbiId(Long ubiId) {
		Optional<Ubicacion> catalogo = ubicacionRepository.findByUbiId(ubiId);
		if (!catalogo.isPresent())
			throw new UbicacionNotFoundException(String.format(
					messageSource.getMessage("error.entity_not_exist.message", null, LocaleContextHolder.getLocale()),
					ubiId));
		return catalogo.get();
	}

}
