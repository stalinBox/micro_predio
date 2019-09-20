package ec.gob.mag.rna.predio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ec.gob.mag.rna.predio.domain.AreaCultivo;
import ec.gob.mag.rna.predio.domain.Predio;

@Repository("areaCultivoRepository")
public interface AreaCultivoRepository extends CrudRepository<AreaCultivo, Long> {
	
	List<AreaCultivo> findAll();
	
	Optional<AreaCultivo> findByAcId(Integer preId);	

	AreaCultivo save(AreaCultivo areacultivo);

	void deleteByAcId(Integer preId);
	
}
