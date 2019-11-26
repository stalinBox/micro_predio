package ec.gob.mag.rna.predio.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import ec.gob.mag.rna.predio.domain.AreaCultivo;
import ec.gob.mag.rna.predio.domain.Cobertura;
import ec.gob.mag.rna.predio.domain.Coordenada;
import ec.gob.mag.rna.predio.domain.Predio;
import ec.gob.mag.rna.predio.domain.Ubicacion;
import ec.gob.mag.rna.predio.exception.PredioNotFoundException;
import ec.gob.mag.rna.predio.repository.PredioRepository;
import ec.gob.mag.rna.predio.repository.UbicacionRepository;

@Service("predioService")
public class PredioService {
	@Autowired
	@Qualifier("predioRepository")
	private PredioRepository predioRepository;
	
	@Autowired
	@Qualifier("ubicacionRepository")
	private UbicacionRepository ubicacionRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	
	public void addInfoList(List<Predio> predios)
	{
		predios.stream().map(p->{
			addInfoObj(p);
			return p;
		}).collect(Collectors.toList());
	}
	
	
	public void addInfoObj(Predio predio)
	{
		if(predio.getCoordenadas()!=null || predio.getCoberturas().size()>0)
		{
			predio.getCoordenadas().stream().map(c->{
				if(c.getCordLatitud()==null || c.getCordLongitud()==null)
				{
					c.setOrigenLatLong("Parroquia");
					Optional<Ubicacion> u = ubicacionRepository.findByUbiId(predio.getUbiId());
					c.setOrigenLatLongUbiId(u.get().getUbiId());
					c.setOrigenLatLongUbiNombre(u.get().getUbiNombre());
					c.setCordLongitud(u.get().getUbiLongitud().toString());
					c.setCordLatitud(u.get().getUbiLatitud().toString());
				}
				else
				{
					c.setOrigenLatLong("Predio");
				}
				return c;
			}).collect(Collectors.toList());
		}
		
	}
	
	
	

	/**
	 * Funcion findAll para Predios
	 * 
	 * @return Todos los registros de Predios que estan almacenados en la base de
	 *         datos BDC
	 */
	public List<Predio> findAll() {
		List<Predio> predios = predioRepository.findAll();
		if (predios == null || predios.size() == 0)
			throw new PredioNotFoundException(messageSource.getMessage("error.entity_not_exist_find_array.message",
					null, LocaleContextHolder.getLocale()));
		predios = (List<Predio>) predios.stream().map(predio -> {
			return predio;
		}).collect(Collectors.toList());
		
		addInfoList(predios);
		
		return predios;

	}

	/**
	 * Funcion findByPreId
	 * 
	 * @param id del Predio
	 * @return el Predio recuperado de la base de datos BDC
	 */
	public Optional<Predio> findByPreId(Long preId) {
		Optional<Predio> predio = predioRepository.findById(preId);
		if (!predio.isPresent())
			throw new PredioNotFoundException(String.format(messageSource.getMessage(
					"error.entity_not_exist_find_one.message", null, LocaleContextHolder.getLocale()), preId));
		// "Entity with id "+id+" not exists!");
		
		
		addInfoObj(predio.get());
		
		return predio;
	}

	/**
	 * Funcion findByPetiId
	 * 
	 * @param id del Predio
	 * @return el Predio recuperado de la base de datos BDC
	 */
	public List<Predio> findByPetiId(Long petiId) {
		List<Predio> predios = predioRepository.findByPetiId(petiId);
		if (predios == null || predios.size() == 0)
			throw new PredioNotFoundException(messageSource.getMessage("error.entity_not_exist_find_array.message",
					null, LocaleContextHolder.getLocale()));
		predios = (List<Predio>) predios.stream().map(predio -> {
			predio.setCoberturas(null);
			predio.setAreaCultivos(null);
			return predio;
		}).collect(Collectors.toList());
		
		addInfoList(predios);
		
		return predios;
	}

	public List<Predio> findByOrgId(Long orgId) {
		List<Predio> predios = predioRepository.findByOrgId(orgId);
		if (predios == null || predios.size() == 0)
			throw new PredioNotFoundException(messageSource.getMessage("error.entity_not_exist_find_array.message",
					null, LocaleContextHolder.getLocale()));
		predios = (List<Predio>) predios.stream().map(predio -> {
			predio.setCoberturas(null);
			predio.setAreaCultivos(null);
			return predio;
		}).collect(Collectors.toList());
		
		addInfoList(predios);
		
		return predios;
	}

	public Integer countByPetiId(Long petiId) {
		List<Predio> predios = predioRepository.findByPetiId(petiId);
		if (predios == null || predios.size() == 0)
			return 0;

		return predios.size();
	}

	/**
	 * Funcion save
	 * 
	 * @param predio
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
		
		addInfoList(predios);
		
		return predios;
	}

	/**
	 * Funcion save
	 * 
	 * @param predio
	 * @return El Predio creado
	 */
	public Predio save(Predio predioToSave) {
		for (int i = 0; predioToSave.getCoordenadas() != null && i < predioToSave.getCoordenadas().size(); i++) {
			if (i > 0)
				throw new PredioNotFoundException(
						String.format(messageSource.getMessage("error.limit1_entities.message", null,
								LocaleContextHolder.getLocale()), "Predio") + " Coordenada");
			((Coordenada) predioToSave.getCoordenadas().toArray()[i]).setPredio(predioToSave);

		}

		for (int i = 0; predioToSave.getAreaCultivos() != null && i < predioToSave.getAreaCultivos().size(); i++) {
			((AreaCultivo) predioToSave.getAreaCultivos().toArray()[i]).setPredio(predioToSave);
		}
		for (int i = 0; predioToSave.getCoberturas() != null && i < predioToSave.getCoberturas().size(); i++) {
			((Cobertura) predioToSave.getCoberturas().toArray()[i]).setPredio(predioToSave);
		}

		Predio predioSave = predioRepository.save(predioToSave);
		return predioSave;
	}

	/**
	 * Funcion deleteById
	 * 
	 * @param PreId
	 * @return
	 */
	public void deleteById(Long preId) {
		predioRepository.deleteById(preId);
	}
}
