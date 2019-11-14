package ec.gob.mag.rna.predio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ec.gob.mag.rna.predio.domain.AreaCultivo;

@Repository("areaCultivoRepository")
public interface AreaCultivoRepository extends CrudRepository<AreaCultivo, Long> {

	List<AreaCultivo> findAll();

	Optional<AreaCultivo> findByAcId(Integer preId);

	@SuppressWarnings("unchecked")
	AreaCultivo save(AreaCultivo areacultivo);

	void deleteByAcId(Integer preId);

}
