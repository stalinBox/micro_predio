package ec.gob.mag.rna.predio.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import ec.gob.mag.rna.predio.domain.AreaCultivo;
import ec.gob.mag.rna.predio.domain.Cobertura;
import ec.gob.mag.rna.predio.domain.Coordenada;
import ec.gob.mag.rna.predio.domain.Predio;
import ec.gob.mag.rna.predio.exception.PredioNotFoundException;
import ec.gob.mag.rna.predio.repository.PredioRepository;


@Service("predioService")
public class PredioService {
	@Autowired
	@Qualifier("predioRepository")
	private PredioRepository predioRepository;
	@Autowired
	private MessageSource messageSource;
	/**
	 * @author Christian Aguilar
	 * <p>Funcion findAll para Predios
	 * </p>
	 * @return Todos los registros de Predios que estan almacenados en la base de datos BDC
	 */
	public List<Predio> findAll(){
		List<Predio> predios = predioRepository.findAll();
		if( predios==null || predios.size()==0 )
			throw new PredioNotFoundException(
						messageSource.getMessage("error.entity_not_exist_find_array.message",null, LocaleContextHolder.getLocale())
					);
		predios= (List<Predio>) predios.stream().map(predio->{
			//predio.setCoberturas(null);
			//predio.setAreaCultivos(null);
			return predio;
		}).collect(Collectors.toList());
		return predios;
		
	}
	/**
	 * @author Paul Cuichan
	 * <p>Funcion findByPreId
	 * </p>
	 * @param id del Predio 
	 * @return el Predio recuperado de la base de datos BDC
	 */
	public Optional<Predio> findByPreId(Long preId){
		Optional<Predio> predio = predioRepository.findById(preId);
		if( !predio.isPresent()) 
			throw new PredioNotFoundException(
					String.format(messageSource.getMessage("error.entity_not_exist_find_one.message",null, LocaleContextHolder.getLocale()),preId)
					);
		//"Entity with id "+id+" not exists!");
		return predio;
	}
	/**
	 * @author Paul Cuichan
	 * <p>Funcion findByPetiId
	 * </p>
	 * @param id del Predio 
	 * @return el Predio recuperado de la base de datos BDC
	 */
	public List<Predio> findByPetiId(Long petiId){
		List<Predio> predios = predioRepository.findByPetiId(petiId);
		if( predios==null || predios.size()==0 )
			throw new PredioNotFoundException(
						messageSource.getMessage("error.entity_not_exist_find_array.message",null, LocaleContextHolder.getLocale())
					);
		predios= (List<Predio>) predios.stream().map(predio->{
			predio.setCoberturas(null);
			predio.setAreaCultivos(null);
			return predio;
		}).collect(Collectors.toList());
		return predios;
	}
	
	
	public List<Predio> findByOrgId(Long orgId){
		List<Predio> predios = predioRepository.findByOrgId(orgId);
		if( predios==null || predios.size()==0 )
			throw new PredioNotFoundException(
						messageSource.getMessage("error.entity_not_exist_find_array.message",null, LocaleContextHolder.getLocale())
					);
		predios= (List<Predio>) predios.stream().map(predio->{
			predio.setCoberturas(null);
			predio.setAreaCultivos(null);
			return predio;
		}).collect(Collectors.toList());
		return predios;
	}
	
	
	public Integer countByPetiId(Long petiId){
		List<Predio> predios = predioRepository.findByPetiId(petiId);
		if( predios==null || predios.size()==0 )
			return 0;

		return predios.size();
	}
	
	/**
	 * @author Paul Cuichan 		
	 * <p>Funcion save
	 * @param predio
	 * </p>
	 * @return El Predio creado
	 */
	public List<Predio> findByUbiId(Long ubiId) {
		List<Predio> predios = predioRepository.findByUbiId(ubiId);
		if (predios == null || predios.size() == 0)
			throw new PredioNotFoundException(messageSource.getMessage("error.entity_not_exist_find_array.message",
					null, LocaleContextHolder.getLocale()));
		predios = (List<Predio>) predios.stream().map(predio -> {
			predio.setCoberturas(null);
			predio.setAreaCultivos(null);
			return predio;
		}).collect(Collectors.toList());
		return predios;
	}
	
	/**
	 * @author Paul Cuichan 		
	 * <p>Funcion save
	 * @param predio
	 * </p>
	 * @return El Predio creado
	 */
	public Predio save(Predio predioToSave) {
		for(int i = 0;predioToSave.getCoordenadas()!=null && i< predioToSave.getCoordenadas().size(); i++ ){
			if( i > 0 )
				throw new PredioNotFoundException(
						String.format(messageSource.getMessage("error.limit1_entities.message",null, LocaleContextHolder.getLocale()),"Predio")+" Coordenada"
						);
			((Coordenada) predioToSave.getCoordenadas().toArray()[i]).setPredio(predioToSave);
			
		}
		
		for(int i = 0;predioToSave.getAreaCultivos()!=null && i< predioToSave.getAreaCultivos().size(); i++ ){
			((AreaCultivo)predioToSave.getAreaCultivos().toArray()[i]).setPredio(predioToSave);
		}
		for(int i = 0;predioToSave.getCoberturas()!=null && i< predioToSave.getCoberturas().size(); i++ ){
			((Cobertura)predioToSave.getCoberturas().toArray()[i]).setPredio(predioToSave);
		}
		
		Predio predioSave = predioRepository.save(predioToSave);
		return predioSave;
		
		
		//return null;
	}
	/**
	 * @author Christian Aguilar
	 * <p>Funcion deleteById
	 * </p>
	 * @param PreId
	 * @return 
	 */
	public void deleteById(Long preId) {
		predioRepository.deleteById(preId);
	}	
}
