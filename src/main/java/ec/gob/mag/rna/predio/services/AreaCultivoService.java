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
import ec.gob.mag.rna.predio.domain.Predio;
import ec.gob.mag.rna.predio.exception.PredioNotFoundException;
import ec.gob.mag.rna.predio.repository.AreaCultivoRepository;
import ec.gob.mag.rna.predio.repository.PredioRepository;


@Service("areaCultivoService")
public class AreaCultivoService {
	@Autowired
	@Qualifier("areaCultivoRepository")
	private AreaCultivoRepository areaCultivoRepository;
	@Autowired
	private MessageSource messageSource;
	/**
	 * @author Paul Cuichan
	 * <p>Funcion findAll para areas de cultivo
	 * </p>
	 * @return Todos los registros de areaCultivo que estan almacenados en la base de datos BDC
	 */
	public List<AreaCultivo> findAll(){
		List<AreaCultivo> listAreaCultivos = areaCultivoRepository.findAll();
		if(listAreaCultivos.isEmpty())
			throw new PredioNotFoundException(
					String.format(messageSource.getMessage(
							"error.entity_cero_exist.message",null, LocaleContextHolder.getLocale()),
							this.getClass().getName())
					);
			
			
		for(int contador=0; contador< listAreaCultivos.size();contador++) {
			listAreaCultivos.get(contador).setPredio(null);
		}
		return listAreaCultivos;
	}
	/**
	 * @author Christian Aguilar
	 * <p>Funcion findByAcId
	 * </p>
	 * @param id del AreaCultivo 
	 * @return el AreaCultivo  recuperado de la base de datos BDC
	 */
	public Optional<AreaCultivo> findByAcId(Long acId){
		Optional<AreaCultivo> areaCultivo = areaCultivoRepository.findById(acId);
		if( !areaCultivo.isPresent()) 
			throw new PredioNotFoundException(
					String.format(messageSource.getMessage(
							"error.entity_not_exist.message",null, LocaleContextHolder.getLocale()),acId)
					);
		areaCultivo.get().setPredio(null);
		return areaCultivo;
	}
	/**
	 * @author Christian Aguilar 		
	 * <p>Funcion save
	 * @param areacultivo
	 * </p>
	 * @return El areaCultivo creado
	 */
	public AreaCultivo save(AreaCultivo areaCultivo) {
		return areaCultivoRepository.save(areaCultivo);
	}
	/**
	 * @author Christian Aguilar
	 * <p>Funcion deleteById
	 * </p>
	 * @param PreId
	 * @return 
	 */
	public void deleteById(Long acId) {
		areaCultivoRepository.deleteById(acId);
	}	
}
