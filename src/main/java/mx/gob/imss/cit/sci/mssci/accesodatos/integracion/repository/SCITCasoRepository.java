package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITCaso;


@Repository
public interface SCITCasoRepository extends CrudRepository<SCITCaso, Long>{
	SCITCaso findByIdCaso(Long idCaso);

}
