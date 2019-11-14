package ec.gob.mag.rna.predio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ec.gob.mag.rna.predio.domain.Predio;

@Repository("predioRepository")
public interface PredioRepository extends CrudRepository<Predio, Long> {

	List<Predio> findAll();

	Optional<Predio> findByPreId(Long preId);

	List<Predio> findByUbiId(Long ubiId);

	List<Predio> findByPetiId(Long petiId);

	List<Predio> findByOrgId(Long orgId);

	@SuppressWarnings("unchecked")
	Predio save(Predio predio);

	void deleteByPreId(Integer preId);

}
