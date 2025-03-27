package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCICClasificacion;

@Repository
public interface SCICClasificacionRepository extends CrudRepository<SCICClasificacion, Long>{
	SCICClasificacion findByIdClasificacion(Long idClasificacion);

}
