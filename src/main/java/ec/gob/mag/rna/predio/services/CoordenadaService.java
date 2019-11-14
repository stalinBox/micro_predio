package ec.gob.mag.rna.predio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ec.gob.mag.rna.predio.domain.Coordenada;
import ec.gob.mag.rna.predio.exception.PredioNotFoundException;
import ec.gob.mag.rna.predio.repository.CoordenadaRepository;

@Service("coordenadaService")
public class CoordenadaService {
	@Autowired
	@Qualifier("coordenadaRepository")
	private CoordenadaRepository coordenadaRepository;
	@Autowired
	private MessageSource messageSource;

	/**
	 * Funcion findAll para coordenada
	 * 
	 * @return Todos los registros de coordenada que estan almacenados en la base de
	 *         datos BDC
	 */
	public List<Coordenada> findAll() {
		List<Coordenada> coordenadas = coordenadaRepository.findAll();
		if (coordenadas.isEmpty())
			throw new PredioNotFoundException(String.format(
					messageSource.getMessage("error.entity_cero_exist.message", null, LocaleContextHolder.getLocale()),
					this.getClass().getName()));

		for (int contador = 0; contador < coordenadas.size(); contador++) {
			coordenadas.get(contador).setPredio(null);
		}
		return coordenadas;
	}

	/**
	 * Funcion findByCobId
	 * 
	 * @param id del coordenada
	 * @return el coordenada recuperado de la base de datos BDC
	 */
	public Optional<Coordenada> findByCobId(Long cobId) {
		Optional<Coordenada> coordenada = coordenadaRepository.findById(cobId);
		if (!coordenada.isPresent())
			throw new PredioNotFoundException(String.format(
					messageSource.getMessage("error.entity_not_exist.message", null, LocaleContextHolder.getLocale()),
					cobId));
		coordenada.get().setPredio(null);
		return coordenada;
	}

	/**
	 * Funcion save
	 * 
	 * @param coordenada
	 * @return El coordenada creado
	 */
	public Coordenada save(Coordenada coordenada) {
		return coordenadaRepository.save(coordenada);
	}

	/**
	 * Funcion deleteById
	 * 
	 * @param CobId
	 * @return
	 */
	public void deleteById(Long cobId) {
		coordenadaRepository.deleteById(cobId);
	}
}
