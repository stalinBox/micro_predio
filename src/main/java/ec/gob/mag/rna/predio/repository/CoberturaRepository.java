package ec.gob.mag.rna.predio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ec.gob.mag.rna.predio.domain.Cobertura;

@Repository("coberturaRepository")
public interface CoberturaRepository extends CrudRepository<Cobertura, Long> {

	List<Cobertura> findAll();

	Optional<Cobertura> findByCobId(Integer cobId);

	@SuppressWarnings("unchecked")
	Cobertura save(Cobertura cobertura);

	void deleteByCobId(Integer cobId);

}
