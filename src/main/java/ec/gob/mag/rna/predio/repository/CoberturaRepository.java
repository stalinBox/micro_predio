package ec.gob.mag.rna.predio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ec.gob.mag.rna.predio.domain.Cobertura;
import ec.gob.mag.rna.predio.domain.Predio;

@Repository("coberturaRepository")
public interface CoberturaRepository extends CrudRepository<Cobertura, Long> {
	
	List<Cobertura> findAll();
	
	Optional<Cobertura> findByCobId(Integer cobId);	

	Cobertura save(Cobertura cobertura);

	void deleteByCobId(Integer cobId);
	
}
