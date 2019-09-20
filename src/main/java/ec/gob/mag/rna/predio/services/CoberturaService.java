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
import ec.gob.mag.rna.predio.domain.Cobertura;
import ec.gob.mag.rna.predio.domain.Predio;
import ec.gob.mag.rna.predio.exception.PredioNotFoundException;
import ec.gob.mag.rna.predio.repository.AreaCultivoRepository;
import ec.gob.mag.rna.predio.repository.CoberturaRepository;
import ec.gob.mag.rna.predio.repository.PredioRepository;


@Service("coberturaService")
public class CoberturaService {
	@Autowired
	@Qualifier("coberturaRepository")
	private CoberturaRepository coberturaRepository;
	@Autowired
	private MessageSource messageSource;
	/**
	 * @author Paul Cuichan
	 * <p>Funcion findAll para cobertura
	 * </p>
	 * @return Todos los registros de cobertura que estan almacenados en la base de datos BDC
	 */
	public List<Cobertura> findAll(){
		List<Cobertura> coberturas = coberturaRepository.findAll();
		if(coberturas.isEmpty())
			throw new PredioNotFoundException(
					String.format(messageSource.getMessage(
							"error.entity_cero_exist.message",null, LocaleContextHolder.getLocale()),
							this.getClass().getName())
					);
			
			
		for(int contador=0; contador< coberturas.size();contador++) {
			coberturas.get(contador).setPredio(null);
		}
		return coberturas;
	}
	/**
	 * @author Christian Aguilar
	 * <p>Funcion findByCobId
	 * </p>
	 * @param id del cobertura 
	 * @return el cobertura  recuperado de la base de datos BDC
	 */
	public Optional<Cobertura> findByCobId(Long cobId){
		Optional<Cobertura> cobertura = coberturaRepository.findById(cobId);
		if( !cobertura.isPresent()) 
			throw new PredioNotFoundException(
					String.format(messageSource.getMessage(
							"error.entity_not_exist.message",null, LocaleContextHolder.getLocale()),cobId)
					);
		cobertura.get().setPredio(null);
		return cobertura;
	}
	/**
	 * @author Christian Aguilar 		
	 * <p>Funcion save
	 * @param cobertura
	 * </p>
	 * @return El cobertura creado
	 */
	public Cobertura save(Cobertura cobertura) {
		return coberturaRepository.save(cobertura);
	}
	/**
	 * @author Christian Aguilar
	 * <p>Funcion deleteById
	 * </p>
	 * @param CobId
	 * @return 
	 */
	public void deleteById(Long cobId) {
		coberturaRepository.deleteById(cobId);
	}	
}
