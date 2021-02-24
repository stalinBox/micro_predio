package ec.gob.mag.rna.predio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import ec.gob.mag.rna.predio.domain.Cultivo;
import ec.gob.mag.rna.predio.exception.MyNotFoundException;
import ec.gob.mag.rna.predio.repository.CultivoRepository;

@Service("cultivoService")
public class CultivoService {

	@Autowired
	@Qualifier("cultivoRepository")
	private CultivoRepository cultivoRepository;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Metodo para encontrar todos los registros
	 * 
	 * @return Todos los registros de la tabla
	 */
	public List<Cultivo> findAll() {
		List<Cultivo> cultivo = cultivoRepository.findByCulEliminadoAndCulEstadoEquals(false, 11);
		if (cultivo.isEmpty())
			throw new MyNotFoundException(String.format(
					messageSource.getMessage("error.entity_cero_exist.message", null, LocaleContextHolder.getLocale()),
					this.getClass().getName()));
		return cultivo;
	}

	/**
	 * Busca un registro por Id
	 * 
	 * @param id: Identificador del registro
	 * @return entidad: Retorna todos los registros filtrados por el parámetros de
	 *         entrada
	 */
	public Optional<Cultivo> findById(Long id) {
		Optional<Cultivo> cultivo = cultivoRepository.findByIdAndCulEliminadoAndCulEstadoEquals(id, false, 11);
		if (!cultivo.isPresent())
			throw new MyNotFoundException(String.format(
					messageSource.getMessage("error.entity_cero_exist.message", null, LocaleContextHolder.getLocale()),
					id));
		return cultivo;
	}

	/**
	 * Busca un registro por Id
	 * 
	 * @param id: Identificador del registro
	 * @return entidad: Retorna todos los registros filtrados por el parámetros de
	 *         entrada
	 */
	public Cultivo update(Cultivo cultivo) {
		Optional<Cultivo> off = findById(cultivo.getId());
		if (!off.isPresent())
			throw new MyNotFoundException(String.format(
					messageSource.getMessage("error.entity_cero_exist.message", null, LocaleContextHolder.getLocale()),
					off.get().getId()));
		return cultivoRepository.save(cultivo);
	}

	/**
	 * Guarda un registro
	 * 
	 * @param entidad: Contiene todos campos de la entidad para guardar
	 * @return catalogo: La entidad Guardada
	 */
	public Cultivo save(Cultivo cultivo) {
		return cultivoRepository.save(cultivo);
	}
}
