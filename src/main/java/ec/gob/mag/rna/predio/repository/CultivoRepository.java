package ec.gob.mag.rna.predio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ec.gob.mag.rna.predio.domain.Cultivo;

@Repository("cultivoRepository")
public interface CultivoRepository extends CrudRepository<Cultivo, Long> {

	List<Cultivo> findByCulEliminadoAndCulEstadoEquals(boolean tmpEliminado, Integer tmpEstado);

	Optional<Cultivo> findByIdAndCulEliminadoAndCulEstadoEquals(Long id, boolean tmpEliminado, Integer tmpEstado);

}
