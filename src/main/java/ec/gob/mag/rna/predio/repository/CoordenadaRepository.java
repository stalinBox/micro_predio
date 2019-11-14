package ec.gob.mag.rna.predio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ec.gob.mag.rna.predio.domain.Coordenada;

@Repository("coordenadaRepository")
public interface CoordenadaRepository extends CrudRepository<Coordenada, Long> {

	List<Coordenada> findAll();

	Optional<Coordenada> findByCordId(Integer cordId);

	@SuppressWarnings("unchecked")
	Coordenada save(Coordenada coordenada);

	void deleteByCordId(Integer cordId);

}
