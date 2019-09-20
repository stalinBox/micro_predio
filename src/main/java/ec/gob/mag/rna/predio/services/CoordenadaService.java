package ec.gob.mag.rna.predio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ec.gob.mag.rna.predio.domain.AreaCultivo;
import ec.gob.mag.rna.predio.domain.Coordenada;
import ec.gob.mag.rna.predio.domain.Predio;
import ec.gob.mag.rna.predio.exception.PredioNotFoundException;
import ec.gob.mag.rna.predio.repository.AreaCultivoRepository;
import ec.gob.mag.rna.predio.repository.CoordenadaRepository;
import ec.gob.mag.rna.predio.repository.PredioRepository;


@Service("coordenadaService")
public class CoordenadaService {
	@Autowired
	@Qualifier("coordenadaRepository")
	private CoordenadaRepository coordenadaRepository;
	@Autowired
	private MessageSource messageSource;
	/**
	 * @author Christian Aguilar
	 * <p>Funcion findAll para coordenada
	 * </p>
	 * @return Todos los registros de coordenada que estan almacenados en la base de datos BDC
	 */
	public List<Coordenada> findAll(){
		List<Coordenada> coordenadas = coordenadaRepository.findAll();
		if(coordenadas.isEmpty())
			throw new PredioNotFoundException(
					String.format(messageSource.getMessage(
							"error.entity_cero_exist.message",null, LocaleContextHolder.getLocale()),
							this.getClass().getName())
					);
			
			
		for(int contador=0; contador< coordenadas.size();contador++) {
			coordenadas.get(contador).setPredio(null);
		}
		return coordenadas;	
	}
	/**
	 * @author Christian Aguilar
	 * <p>Funcion findByCobId
	 * </p>
	 * @param id del coordenada 
	 * @return el coordenada  recuperado de la base de datos BDC
	 */
	public Optional<Coordenada> findByCobId(Long cobId){
		Optional<Coordenada> coordenada = coordenadaRepository.findById(cobId);
		if( !coordenada.isPresent()) 
			throw new PredioNotFoundException(
					String.format(messageSource.getMessage(
							"error.entity_not_exist.message",null, LocaleContextHolder.getLocale()),cobId)
					);
		coordenada.get().setPredio(null);
		return coordenada;
	}
	/**
	 * @author Christian Aguilar 		
	 * <p>Funcion save
	 * @param coordenada
	 * </p>
	 * @return El coordenada creado
	 */
	public Coordenada save(Coordenada coordenada) {
		return coordenadaRepository.save(coordenada);
	}
	/**
	 * @author Christian Aguilar
	 * <p>Funcion deleteById
	 * </p>
	 * @param CobId
	 * @return 
	 */
	public void deleteById(Long cobId) {
		coordenadaRepository.deleteById(cobId);
	}	
}
