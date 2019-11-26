package ec.gob.mag.rna.predio.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ec.gob.mag.rna.predio.domain.Ubicacion;

@Repository("ubicacionRepository")
public interface UbicacionRepository extends CrudRepository<Ubicacion, Long> {

	List<Ubicacion> findAll();

	Optional<Ubicacion> findByUbiId(Long ubiId);

	@SuppressWarnings("unchecked")
	Ubicacion save(Ubicacion agrupacion);

	void deleteByUbiId(Integer ubiId);

}
